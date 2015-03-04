package uk.ac.herts.SmartLab.I2C;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;

import com.cinterion.io.I2cBusConnection;

public class I2CBus {

	private static char START_RESPONSE = '{';
	private static char END_RESPONSE = '}';
	private static char START_REQUEST = '<';
	private static char END_REQUEST = '>';
	private static char ID = '0';

	private static I2cBusConnection cc;

	private static InputStream in;
	private static OutputStream out;

	private static char[] buffer = new char[2];

	private static I2CPayload response = new I2CPayload();

	public static void open(int bus_id, int baudrate) throws IOException {

		cc = (I2cBusConnection) Connector.open("i2c:" + bus_id + ";baudrate="
				+ baudrate);
		in = cc.openInputStream();
		out = cc.openOutputStream();
	}

	public static void close() throws IOException {
		if (in != null)
			in.close();
		if (out != null)
			out.close();
		if (cc != null)
			cc.close();
	}

	/*
	 * @param i2c_address : 7 bit address
	 */
	public static synchronized I2CPayload write(int i2c_address, byte[] data,
			int offset, int length) throws IOException {
		out.write(START_REQUEST);
		out.write(ID);
		out.write(Integer.toHexString(i2c_address << 1).getBytes());
		for (int i = offset; i < offset + length; i++) {
			if ((data[i] & 0xFF) <= 0x0F)
				out.write(ID);
			out.write(Integer.toHexString(data[i] & 0xFF).getBytes());
		}
		out.write(END_REQUEST);
		out.flush();

		readResponse();
		return response;
	}

	public static synchronized I2CPayload read(int i2c_address, int length)
			throws IOException {
		out.write(START_REQUEST);
		out.write(ID);
		out.write(Integer.toHexString((i2c_address << 1) | 0x01).getBytes());

		int msb = length >> 8;
		if (msb <= 0x0F)
			out.write(ID);
		out.write(Integer.toHexString(msb).getBytes());

		int lsb = length & 0xFF;
		if (lsb <= 0x0F)
			out.write(ID);
		out.write(Integer.toHexString(lsb).getBytes());

		out.write(END_REQUEST);
		out.flush();

		readResponse();
		return response;
	}

	private static void readResponse() throws IOException {
		while (in.read() != START_RESPONSE)
			continue;

		in.read();
		response.status = (char) in.read();

		StringBuffer str = new StringBuffer();
		char ch = (char) in.read();
		while (ch != END_RESPONSE) {
			str.append(ch);
			ch = (char) in.read();
		}

		response.length = str.length() / 2;

		if (response.length == 0)
			return;

		if (response.length > response.data.length)
			response.data = new int[response.length];

		for (int i = 0; i < str.length(); i += 2) {
			buffer[0] = str.charAt(i);
			buffer[1] = str.charAt(i + 1);
			response.data[i >> 1] = Integer.parseInt(new String(buffer), 16);
		}
	}

}
