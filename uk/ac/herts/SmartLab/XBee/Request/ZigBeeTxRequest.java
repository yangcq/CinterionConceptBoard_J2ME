package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Options.OptionsBase;
import uk.ac.herts.SmartLab.XBee.Type.API_IDENTIFIER;

public class ZigBeeTxRequest extends TxPayloadBase {
	// 0x10
	// FrameID;
	// RemoteDevice
	// Broadcast_Radius;
	// TransmitOptions;
	// RF_Data;
	public ZigBeeTxRequest(int frameID, Address remoteAddress,
			OptionsBase transmitOptions, byte[] payload) {
		this(frameID, remoteAddress, transmitOptions, payload, 0,
				payload.length);
	}

	public ZigBeeTxRequest(int frameID, Address remoteAddress,
			OptionsBase transmitOptions, byte[] payload, int offset, int length) {
		super(12 + payload.length, API_IDENTIFIER.ZigBee_Transmit_Request,
				frameID);
		this.SetContent(remoteAddress.GetAddressValue());
		this.SetContent((byte) 0x00);
		this.SetContent(transmitOptions.GetValue());
		this.SetContent(payload, offset, length);
	}

	public void SetBroadcastRadius(byte broadcastRadius) {
		this.SetContent(12, broadcastRadius);
	}

	public void SetTransmitOptions(OptionsBase transmitOptions) {
		this.SetContent(13, transmitOptions.GetValue());
	}

	public void SetPayload(byte[] data) {
		SetPayload(data, 0, data.length);
	}

	public void SetPayload(byte[] data, int offset, int length) {
		this.SetContent(14, data, offset, length);
		this.SetPosition(14 + length - offset);
	}

	public void SetRemoteAddress(Address remoteAddress) {
		this.SetContent(2, remoteAddress.GetAddressValue());
	}
}