package uk.ac.herts.SmartLab.XBee.Type;

public class API_IDENTIFIER {
	public static final int Tx64_Request = 0x00;
	public static final int Tx16_Request = 0x01;
	public static final int AT_Command = 0x08;
	public static final int AT_Command_Queue_Parameter_Value = 0x09;
	public static final int ZigBee_Transmit_Request = 0x10;
	public static final int Explicit_Addressing_ZigBee_Command_Frame = 0x11;
	public static final int Remote_Command_Request = 0x17;
	public static final int Create_Source_Route = 0x21;
	public static final int Register_Joining_Device = 0x24;
	public static final int Rx64_Receive_Packet = 0x80;
	public static final int Rx16_Receive_Packet = 0x81;
	public static final int Rx64_IO_Data_Sample_Rx_Indicator = 0x82;
	public static final int Rx16_IO_Data_Sample_Rx_Indicator = 0x83;
	public static final int AT_Command_Response = 0x88;
	public static final int XBee_Transmit_Status = 0x89;
	public static final int Modem_Status = 0x8A;
	public static final int ZigBee_Transmit_Status = 0x8B;
	public static final int ZigBee_Receive_Packet = 0x90;
	public static final int ZigBee_Explicit_Rx_Indicator = 0x91;
	public static final int ZigBee_IO_Data_Sample_Rx_Indicator = 0x92;
	public static final int XBee_Sensor_Read_Indicato = 0x94;
	public static final int Node_Identification_Indicator = 0x95;
	public static final int Remote_Command_Response = 0x97;
	public static final int Over_the_Air_Firmware_Update_Status = 0xA0;
	public static final int Route_Record_Indicator = 0xA1;
	public static final int Device_Authenticated_Indicator = 0xA2;
	public static final int Many_to_One_Route_Request_Indicator = 0xA3;
}