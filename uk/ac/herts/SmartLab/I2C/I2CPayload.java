package uk.ac.herts.SmartLab.I2C;


public class I2CPayload {
	protected char status;
	protected int[] data = new int[10];
	protected int length;

	public static final char STATUS_OK = '+';
	public static final char STATUS_NAK = '-';
	public static final char STATUS_ERROR = '!';

	public char getStatus() {
		return status;
	}

	public int[] getData() {
		return data;
	}

	public int getLength() {
		return length;
	}

}
