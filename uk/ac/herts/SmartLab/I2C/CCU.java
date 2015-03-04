package uk.ac.herts.SmartLab.I2C;

import java.io.IOException;

public class CCU {

	private static final int CCU_ADDRESS = 0x69;

	private static final byte REGISTER_GPIO_SWITCH = 0x30;
	private static final byte REGISTER_ADC_CHANNEL = 0x51;
	private static final byte REGISTER_ASC_SPI = 0x52;
	private static final byte REGISTER_BLINK = (byte) 0xFE;
	private static final byte REGISTER_RAR = (byte) 0xFF;

	private byte[] buffer = new byte[2];

	public static final byte GPIO5 = 0x10;
	public static final byte GPIO6 = 0x11;
	public static final byte GPIO8 = 0x12;
	public static final byte GPIO7 = 0x13;
	public static final byte GPIO20 = 0x14;
	public static final byte GPIO21 = 0x15;
	public static final byte GPIO22 = 0x16;
	public static final byte GPIO23 = 0x17;

	public static final byte CONFIG_INPUT = 0x00;
	public static final byte CONFIG_OUTPUT = 0x01;
	public static final byte CONFIG_RELEASE = (byte) 0xFF;

	public static final byte CONFIG_ASC0_VCP_ASC1_RXTX = 0x00;
	public static final byte CONFIG_ASC0_VCP_ASC1_SPI = 0x01;
	public static final byte CONFIG_ASC0_RXTX_ASC1_SPI = 0x02;

	public boolean WriteGPIO(byte GPIO, byte config) {
		buffer[0] = GPIO;
		buffer[1] = config;

		try {
			if (I2CBus.write(CCU_ADDRESS, buffer, 0, 2).getStatus() == I2CPayload.STATUS_OK)
				return true;
		} catch (IOException e) {
		}

		return false;
	}

	public int ReadGPIO() {
		buffer[0] = REGISTER_RAR;
		buffer[1] = REGISTER_GPIO_SWITCH;

		try {
			I2CPayload re = I2CBus.write(CCU_ADDRESS, buffer, 0, 2);
			if (re.getStatus() != I2CPayload.STATUS_OK)
				return -1;

			re = I2CBus.read(CCU_ADDRESS, 1);
			if (re.getStatus() != I2CPayload.STATUS_OK)
				return -1;

			return re.getData()[0];
		} catch (IOException e) {
		}

		return -1;
	}

	public boolean WriteADCChannel(int channel) {
		buffer[0] = REGISTER_ADC_CHANNEL;
		buffer[1] = (byte) channel;

		try {
			if (I2CBus.write(CCU_ADDRESS, buffer, 0, 2).getStatus() == I2CPayload.STATUS_OK)
				return true;

		} catch (IOException e) {
		}

		return false;
	}

	public int ReadADCChannel() {
		buffer[0] = REGISTER_RAR;
		buffer[1] = REGISTER_ADC_CHANNEL;

		try {
			I2CPayload re = I2CBus.write(CCU_ADDRESS, buffer, 0, 2);
			if (re.getStatus() != I2CPayload.STATUS_OK)
				return -1;

			re = I2CBus.read(CCU_ADDRESS, 1);
			if (re.getStatus() != I2CPayload.STATUS_OK)
				return -1;

			return re.getData()[0];
		} catch (IOException e) {
		}

		return -1;
	}

	public boolean WriteASC0ASC1(byte config) {
		buffer[0] = REGISTER_ASC_SPI;
		buffer[1] = config;

		try {
			if (I2CBus.write(CCU_ADDRESS, buffer, 0, 2).getStatus() == I2CPayload.STATUS_OK)
				return true;

		} catch (IOException e) {
		}

		return false;
	}

	public int ReadASC0ASC1() {
		buffer[0] = REGISTER_RAR;
		buffer[1] = REGISTER_ASC_SPI;

		try {
			I2CPayload re = I2CBus.write(CCU_ADDRESS, buffer, 0, 2);
			if (re.getStatus() != I2CPayload.STATUS_OK)
				return -1;

			re = I2CBus.read(CCU_ADDRESS, 1);
			if (re.getStatus() != I2CPayload.STATUS_OK)
				return -1;

			return re.getData()[0];

		} catch (IOException e) {
		}

		return -1;
	}

	public boolean Blink(int time) {
		buffer[0] = REGISTER_BLINK;
		buffer[1] = (byte) time;

		try {
			if (I2CBus.write(CCU_ADDRESS, buffer, 0, 2).getStatus() == I2CPayload.STATUS_OK)
				return true;

		} catch (IOException e) {
		}

		return false;
	}
}
