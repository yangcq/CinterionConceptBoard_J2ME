package uk.ac.herts.SmartLab.I2C;

import java.io.IOException;

public class SP03 {

	private static final int SPO3_ADDRESS = 0x62;

	private static final byte REGISTER_FOR_COMMAND = 0x00;
	private static final byte REGISTER_FOR_SOFTWARE_REVISION_NUMBER = 0x01;
	private static final byte COMMAND_SPEAK_OUT_THE_BUFFER = 0x40;
	private static final byte COMMAND_NOP = 0x00;

	public static final int CLOCK_RATE = 100;

	public static final byte SPEECH_SPEED_NORMAL = 0x05;
	public static final byte SPEECH_SPEED_FAST = 0x02;
	public static final byte SPEECH_SPEED_SLOW = 0x06;

	public static final byte SPEECH_VOLUME_MAX = 0x00;
	public static final byte SPEECH_VOLUME_MEDIUM = 0x03;
	public static final byte SPEECH_VOLUME_MIN = 0x06;

	public static final byte SPEECH_PITCH_NORMAL = 0x03;

	byte[] buffer = new byte[87];

	public SP03() {
		buffer[0] = REGISTER_FOR_COMMAND;
		buffer[1] = COMMAND_NOP;
	}

	public boolean isSpeaking() {

		buffer[2] = REGISTER_FOR_COMMAND;

		try {
			I2CPayload re = I2CBus.write(SPO3_ADDRESS, buffer, 2, 1);

			if (re.getStatus() != I2CPayload.STATUS_OK)
				return false;

			re = I2CBus.read(SPO3_ADDRESS, 1);

			if (re.getStatus() == I2CPayload.STATUS_OK
					&& re.getData()[0] == 0x40)
				return true;

		} catch (IOException e) {
		}

		return false;
	}

	public void Speak(String message) {
		Speak(message, SPEECH_VOLUME_MAX, SPEECH_SPEED_NORMAL);
	}

	public void Speak(String message, byte volume, byte speed) {

		if (message.length() > 81)
			return;

		buffer[2] = volume;
		buffer[3] = speed;
		buffer[4] = SPEECH_PITCH_NORMAL;

		byte[] value = message.getBytes();
		System.arraycopy(value, 0, buffer, 5, value.length);

		buffer[value.length + 5] = 0x00;

		try {
			I2CBus.write(SPO3_ADDRESS, buffer, 0, value.length + 6);

			buffer[2] = REGISTER_FOR_COMMAND;
			buffer[3] = COMMAND_SPEAK_OUT_THE_BUFFER;

			I2CBus.write(SPO3_ADDRESS, buffer, 2, 2);
		} catch (IOException e) {
		}
	}
}
