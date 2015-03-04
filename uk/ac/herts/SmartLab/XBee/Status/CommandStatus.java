package uk.ac.herts.SmartLab.XBee.Status;

public class CommandStatus {
	public static final int OK = 0x00;
	public static final int ERROR = 0x01;
	public static final int INVALID_COMMAND = 0x02;
	public static final int INVALID_Parameter = 0x03;
	public static final int TRANSMISSION_FAILED = 0x04;
}
