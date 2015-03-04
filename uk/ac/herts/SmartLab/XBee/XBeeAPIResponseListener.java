package uk.ac.herts.SmartLab.XBee;

import uk.ac.herts.SmartLab.XBee.Response.*;

public interface XBeeAPIResponseListener {
	public void onChecksumError(APIFrame e);

	public void onUndefinedPacket(APIFrame e);

	public void onATCommandResponse(ATCommandResponse e);

	public void onModemStatusResponse(ModemStatusResponse e);

	public void onNodeIdentificationResponse(NodeIdentificationResponse e);

	public void onRemoteCommandResponse(RemoteCommandResponse e);

	public void onXBeeIODataSampleRx16Response(XBeeRx16IOSampleResponse e);

	public void onXBeeIODataSampleRx64Response(XBeeRx64IOSampleResponse e);

	public void onXBeeRx16Indicator(XBeeRx16Response e);

	public void onXBeeRx64Indicator(XBeeRx64Response e);

	public void onXBeeSensorReadResponse(SensorReadResponse e);

	public void onXBeeTransmitStatusResponse(XBeeTxStatusResponse e);

	public void onZigBeeExplicitRxResponse(ZigBeeExplicitRxResponse e);

	public void onZigBeeIODataSampleRXResponse(ZigBeeIOSampleResponse e);

	public void onZigBeeReceivePacketResponse(ZigBeeRxResponse e);

	public void onZigBeeTransmitStatusResponse(ZigBeeTxStatusResponse e);
}
