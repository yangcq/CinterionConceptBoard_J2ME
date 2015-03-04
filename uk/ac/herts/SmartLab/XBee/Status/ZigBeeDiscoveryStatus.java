package uk.ac.herts.SmartLab.XBee.Status;

public class ZigBeeDiscoveryStatus {
	public static final int NO_DISCOVERY_OVERHEAD = 0x00;
	public static final int ADDRESS_DISCOVERY = 0x01;
	public static final int ROUTE_DISCOVERY = 0x02;
	public static final int ADDRESS_AND_ROUTE = 0x03;
	public static final int EXTENED_TIMEOUT_DISCOVERY = 0x40;
}
