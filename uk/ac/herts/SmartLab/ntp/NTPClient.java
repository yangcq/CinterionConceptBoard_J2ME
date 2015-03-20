package uk.ac.herts.SmartLab.ntp;

import java.io.IOException;
import java.util.Date;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.UDPDatagramConnection;

import com.cinterion.misc.NetExtension;

public class NTPClient {

	private static final String NTP_SERVER = "time-a.nist.gov";

	private static final long NTP_TIMESTAMP_DELTA = 2208988800l;
	private static double[] BASE = new double[] { 16777216, 65536, 256, 1,
			0.00390625, 0.0000152587890625, 0.000000059604644775390625,
			0.00000000023283064365386962890625 };

	public static Date getTime(String hostString, String profileString)
			throws IOException {

		NTPWorkThread workThread = new NTPWorkThread(hostString, profileString);
		workThread.start();

		synchronized (NTP_SERVER) {
			try {
				NTP_SERVER.wait(10000);
			} catch (InterruptedException e) {
			}
		}

		if (!workThread.isOK())
			throw new IOException("Network Timeout");

		double timestamp = 0;
		for (int i = 0; i < 8; i++)
			timestamp += (workThread.getData()[40 + i] & 0xFF) * BASE[i];

		return new Date((long) ((timestamp - NTP_TIMESTAMP_DELTA) * 1000.0));
	}

	public static Date getTime(String profileString) throws IOException {
		return getTime(NTP_SERVER, profileString);
	}

	private static class NTPWorkThread extends Thread {

		private UDPDatagramConnection udpc = null;
		private String hostString;
		private String profileString;

		private boolean isOK;
		final byte[] buffer;

		public NTPWorkThread(String hostString, String profileString) {
			this.hostString = hostString;
			this.profileString = profileString;

			isOK = false;

			buffer = new byte[48];
			buffer[0] = 0x1B;
		}

		public boolean isOK() {
			return this.isOK;
		}

		public byte[] getData() {
			return this.buffer;
		}

		public void stop() {
			try {
				if (udpc != null)
					udpc.close();
			} catch (IOException e) {
			}
		}

		public void run() {
			// TODO Auto-generated method stub
			try {
				String ipString = NetExtension.Hostname(hostString
						+ profileString);

				udpc = (UDPDatagramConnection) Connector.open("datagram://"
						+ ipString + ":123" + profileString);

				// send
				Datagram packet = udpc.newDatagram(buffer, buffer.length);
				udpc.send(packet);

				// receive
				packet = udpc.newDatagram(buffer, buffer.length);
				udpc.receive(packet);

				isOK = true;
				synchronized (NTP_SERVER) {
					NTP_SERVER.notify();
				}

			} catch (Exception e) {
			} finally {
				stop();
			}

		}
	}
}
