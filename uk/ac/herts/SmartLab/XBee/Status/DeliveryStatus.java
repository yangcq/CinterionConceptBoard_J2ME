package uk.ac.herts.SmartLab.XBee.Status;

public class DeliveryStatus {
	public static final int SUCCESS = 0x00;
	public static final int MAC_ACK_FAILURE = 0x01;
	public static final int CCA_FAILURE = 0x02;
	public static final int TRANSMISSION_WAS_PURGED = 0x03;
	public static final int PHYSICAL_ERROR_OCCURRED_ON_THE_INTERFACE_WITH_THE_WIFI_TRANSCEIVER = 0x04;
	public static final int INVALID_DESTINATION_ENDPOINT = 0x15;
	public static final int NO_BUFFERS = 0x18;
	public static final int NETWORK_ACK_FAILURE = 0x21;
	public static final int NOT_JOINED_TO_NETWORK = 0x22;
	public static final int SELF_ADDRESSED = 0x23;
	public static final int ADDRESS_NOT_FOUND = 0x24;
	public static final int ROUTE_NOT_FOUND = 0x25;
	public static final int BROADCAST_SOURCE_FAILED_TO_HEAR_A_NEIGBOR_RELAY_THE_MESSAGE = 0x26;
	public static final int INVALID_BINDING_TABLE_INDEX = 0x2B;
	public static final int INVALID_ENDPOINT = 0x2C;
	public static final int ATTEMPTED_BROADCAST_WITH_APS_TRANSMISSION = 0x2D;
	public static final int ATTEMPTED_UNICAST_WITH_APS_TRANSMISSION_BUT_EE_0 = 0x2E;
	public static final int SOFTWARE_ERROR_OCCURRED = 0x31;
	public static final int RESOURCE_ERROR_LACK_OF_FREE_BUFFERS_TIMERS_ETC = 0x32;
	public static final int DATA_PAYLOAD_TOO_LARGE = 0x74;
	public static final int INDIRECT_MESSAGE_UNREQUESTED = 0x75;
	public static final int ATTEMPT_TO_CREATE_A_CLIENT_SOCKET_FAILED = 0x76;
	public static final int KEY_NOT_AUTHORIZED = 0xBB;
}
