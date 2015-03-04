package uk.ac.herts.SmartLab.XBee.Status;

public class ModemStatus {
	public static final int HARDWARE_RESET = 0x00;
	public static final int WATCHDOG_TIMER_RESET = 0x01;
	public static final int JOINED_NETWORK = 0x02;
	public static final int DISASSOCIATED = 0x03;

	public static final int CONFIGURATION_ERROR = 0x04;
	public static final int COORDINATOR_REALIGNMENT = 0x05;

	public static final int COORDINATOR_START = 0x06;
	public static final int NETWORK_SECURITY_KEY_WAS_UPDATED = 0x07;

	public static final int NETWORK_WOKE_UP = 0x0B;
	public static final int NETWORK_WENT_TO_SLEEP = 0x0C;

	public static final int VOLTAGE_SPPLY_LIMIT_EXCEEDED = 0x0D;
	public static final int MODEM_CONFIGURATION_CHANGED_WHILE_JOIN_IN_PRIGRESS = 0x11;

	// 0x80+ STACK_ERROR
	public static final int STACK_ERROR = 0x80;

	public static final int SEND_JOIN_ISSIED_WITHOUT_CONNECTING_AP = 0x82;
	public static final int ACCESS_POINT_NOT_FOUND = 0x83;
	public static final int PSK_NOT_FOUND = 0x84;
	public static final int SSID_NOT_FOUND = 0x87;

	public static final int FAILED_TO_JOIN_WITH_SECURITY_ENABLED = 0x88;
	public static final int INVALID_CHANNEL = 0x8A;
	public static final int FAILED_TO_JOIN_ACCESS_POINT = 0x8E;

}
