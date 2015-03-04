package uk.ac.herts.SmartLab.gemalto;

import java.util.Vector;

import com.cinterion.io.ATCommand;
import com.cinterion.io.ATCommandFailedException;
import com.cinterion.io.ATCommandListener;

public class EHS6 {

	public static final String REGISTRATION_STATUS_NOT_REGISTERED = "0";
	public static final String REGISTRATION_STATUS_HOME_NETWORK = "1";
	public static final String REGISTRATION_STATUS_SEARCHING = "2";
	public static final String REGISTRATION_STATUS_DENIED = "3";
	public static final String REGISTRATION_STATUS_UNKNOWN = "4";
	public static final String REGISTRATION_STATUS_ROAMING = "5";

	private static final String RESPONSE_OK = "OK";

	private ATCommand command;

	private Vector listeners;

	public EHS6() throws Exception {
		listeners = new Vector();
		command = new ATCommand(false);
		init();
	}

	public void AddEventListener(EHS6ResponseListener listener) {
		listeners.addElement(listener);
	}

	public void RemoveEventListener(EHS6ResponseListener listener) {
		listeners.removeElement(listener);
	}

	private void init() throws IllegalStateException, IllegalArgumentException,
			ATCommandFailedException {

		command.send("ATE0\r");
		command.send("AT+CNMI=1,1,2,2\r");
		command.send("AT+CMGF=1\r");
		command.send("AT+COPS=0\r");
		command.send("AT+CREG=2\r");

		command.send("AT^SIND=BATTCHG,0\r");
		command.send("AT^SIND=SIGNAL,1\r");
		command.send("AT^SIND=SERVICE,1\r");
		command.send("AT^SIND=MESSAGE,1\r");
		command.send("AT^SIND=CALL,1\r");
		command.send("AT^SIND=ROAM,1\r");
		command.send("AT^SIND=SMSFULL,1\r");
		command.send("AT^SIND=RSSI,1\r");
		command.send("AT^SIND=AUDIO,1\r");
		command.send("AT^SIND=EONS,1\r");
		command.send("AT^SIND=NITZ,1\r");
		command.send("AT^SIND=SIMTRAY,1\r");
		command.send("AT^SIND=VMWAIT,1\r");
		command.send("AT^SIND=NITZ,1\r");

		command.send("AT+CSCS=GSM\r");

		command.send("AT+CTZR=1\r");
		command.send("AT+CTZU=1\r");

		command.addListener(listener);
	}

	private ATCommandListener listener = new ATCommandListener() {

		public void ATEvent(String arg0) {
			// TODO Auto-generated method stub
			if (arg0.indexOf("+CMTI:") >= 0)
				handleNewSMS(arg0);
			else if (arg0.indexOf("+CIEV:") >= 0)
				handleCIEV(arg0);
			else
				System.out.println(arg0);
		}

		public void CONNChanged(boolean arg0) {
			// TODO Auto-generated method stub

		}

		public void DCDChanged(boolean arg0) {
			// TODO Auto-generated method stub

		}

		public void DSRChanged(boolean arg0) {
			// TODO Auto-generated method stub

		}

		public void RINGChanged(boolean arg0) {
			// TODO Auto-generated method stub

		}

	};

	private void handleCIEV(String arg0) {
		String[] response = Split(arg0, ", \r\n");

		if (response[1].equalsIgnoreCase("rssi"))
			for (int i = 0; i < listeners.size(); i++)
				((EHS6ResponseListener) listeners.elementAt(i))
						.onSignalStrengthIndication(Integer
								.parseInt(response[2]));

	}

	private void handleNewSMS(String arg0) {

		int start = arg0.indexOf(",");
		int finish = arg0.indexOf("\r\n", start);
		String id = arg0.substring(start + 1, finish);

		try {
			String[] responses = Split(command.send("AT+CMGR=" + id + "\r"),
					"\r\n");

			if (!responses[responses.length - 1].equalsIgnoreCase(RESPONSE_OK))
				return;

			start = responses[0].indexOf("+CMGR:");

			if (start < 0)
				return;

			String[] headers = Split(responses[0], "\"");

			command.send("AT+CMGD=" + id + "\r");

			for (int i = 0; i < listeners.size(); i++)
				((EHS6ResponseListener) listeners.elementAt(i))
						.onMessageReceived(headers[3], headers[5],
								responses.length == 3 ? responses[1] : "");

		} catch (Exception e) {
		}
	}

	public static String[] Split(String splitStr, String delimiter) {
		StringBuffer token = new StringBuffer();
		Vector tokens = new Vector();
		// split
		char[] chars = splitStr.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (delimiter.indexOf(chars[i]) != -1) {
				// we bumbed into a delimiter
				if (token.length() > 0) {
					tokens.addElement(token.toString());
					token.setLength(0);
				}
			} else {
				token.append(chars[i]);
			}
		}
		// don't forget the "tail"...
		if (token.length() > 0) {
			tokens.addElement(token.toString());
		}
		// convert the vector into an array
		String[] splitArray = new String[tokens.size()];
		for (int i = 0; i < splitArray.length; i++) {
			splitArray[i] = (String) tokens.elementAt(i);
		}
		return splitArray;
	}

	public boolean sendSMS(String number, String message) {
		try {
			if (command.send("AT+CMGS=\"" + number + "\"\r").indexOf(">") >= 0)
				if (command.send(message + "\u001A").indexOf(RESPONSE_OK) >= 0)
					return true;

		} catch (Exception e) {
		}
		return false;
	}

	public String getIMEI() {
		try {
			String[] response = Split(command.send("AT+CGSN\r"), "\r\n");
			if (!response[response.length - 1].equalsIgnoreCase(RESPONSE_OK))
				return null;

			return response[0];

		} catch (Exception e) {
		}
		return null;
	}

	public String getNetworkOperatorName() {
		try {
			String[] response = Split(command.send("AT+COPS?\r"), "\r\n");
			if (!response[response.length - 1].equalsIgnoreCase(RESPONSE_OK))
				return null;

			if (response[0].indexOf("+COPS:") < 0)
				return null;

			return Split(response[0], "\"")[1];

		} catch (Exception e) {
		}
		return null;
	}

	public String getNetworkServiceProviderName() {
		try {
			String[] response = Split(command.send("AT^SIND=eons,2\r"), "\r\n");
			if (!response[response.length - 1].equalsIgnoreCase(RESPONSE_OK))
				return null;

			if (response[0].indexOf("^SIND:") < 0)
				return null;

			return Split(response[0], "\"")[3];

		} catch (Exception e) {
		}
		return null;
	}

	/*
	 * @return rssi value in dBm , 99 not known or not detectable
	 */
	public int getNetworkSignalQuality() {
		try {
			String[] response = Split(command.send("AT+CSQ\r"), "\r\n");
			if (!response[response.length - 1].equalsIgnoreCase(RESPONSE_OK))
				return 99;

			if (response[0].indexOf("+CSQ:") < 0)
				return 99;

			int raw = Integer.parseInt(Split(response[0], " ,")[1]);
			if (raw == 99)
				return raw;

			return (raw << 1) - 113;

		} catch (Exception e) {
		}
		return 99;
	}

	public String getNetworkRegistrationStatus() {
		try {
			String[] response = Split(command.send("AT+CREG?\r"), "\r\n");
			if (!response[response.length - 1].equalsIgnoreCase(RESPONSE_OK))
				return REGISTRATION_STATUS_UNKNOWN;

			if (response[0].indexOf("+CREG:") < 0)
				return REGISTRATION_STATUS_UNKNOWN;

			return Split(response[0], ",")[1];

		} catch (Exception e) {
		}
		return REGISTRATION_STATUS_UNKNOWN;
	}
}
