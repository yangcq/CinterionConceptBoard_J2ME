package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Device.ExplicitAddress;
import uk.ac.herts.SmartLab.XBee.Options.OptionsBase;
import uk.ac.herts.SmartLab.XBee.Type.API_IDENTIFIER;

public class ZigBeeExplicitTxRequest extends TxPayloadBase {
	// 0x11
	// FrameID;
	// ExplicitRemoteDevice
	// Source Endpoint
	// Destination Endpoint
	// Cluster ID
	// Profile ID
	// Broadcast_Radius;
	// TransmitOptions;
	// RF_Data;

	public ZigBeeExplicitTxRequest(int frameID, ExplicitAddress remoteAddress,
			OptionsBase transmitOptions, byte[] payload) {
		this(frameID, remoteAddress, transmitOptions, payload, 0,
				payload.length);
	}

	public ZigBeeExplicitTxRequest(int frameID, ExplicitAddress remoteAddress,
			OptionsBase transmitOptions, byte[] payload, int offset, int length) {
		super(18 + payload.length,
				API_IDENTIFIER.Explicit_Addressing_ZigBee_Command_Frame,
				frameID);
		this.SetContent(remoteAddress.GetAddressValue());
		this.SetContent(remoteAddress.GetExplicitValue());
		this.SetContent((byte) 0x00);
		this.SetContent(transmitOptions.GetValue());
		this.SetContent(payload, offset, length);
	}

	public void SetBroadcastRadius(byte broadcastRadius) {
		this.SetContent(18, broadcastRadius);
	}

	public void SetTransmitOptions(OptionsBase transmitOptions) {
		this.SetContent(19, transmitOptions.GetValue());
	}
	
	public void SetPayload(byte[] data) {
		SetPayload(data, 0, data.length);
	}

	public void SetPayload(byte[] data, int offset, int length) {
		this.SetContent(20, data, offset, length);
		this.SetPosition(20 + length - offset);
	}

	public void SetRemoteAddress(Address remoteAddress) {
		this.SetContent(2, remoteAddress.GetAddressValue());
	}

	public void SetRemoteExplicitAddress(ExplicitAddress remoteAddress) {
		this.SetContent(2, remoteAddress.GetAddressValue());
		this.SetContent(12, remoteAddress.GetExplicitValue());
	}
}