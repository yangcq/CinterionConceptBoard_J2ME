package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Options.OptionsBase;
import uk.ac.herts.SmartLab.XBee.Type.API_IDENTIFIER;

public class XBeeTx64Request extends TxPayloadBase {
	// 0x00
	// FrameID
	// Destination 64
	// Option
	// RFData

	// / <summary>
	// / the ieee 16 bit address is ignored
	// / </summary>
	// / <param name="frameID"></param>
	// / <param name="remoteAddress"></param>
	// / <param name="transmitOptions"></param>
	// / <param name="RFData"></param>
	public XBeeTx64Request(int frameID, Address remoteAddress,
			OptionsBase transmitOptions, byte[] payload) {
		this(frameID, remoteAddress, transmitOptions, payload, 0,
				payload.length);
	}

	// / <summary>
	// / the ieee 16 bit address is ignored
	// / </summary>
	// / <param name="frameID"></param>
	// / <param name="remoteAddress"></param>
	// / <param name="transmitOptions"></param>
	// / <param name="RFData"></param>
	public XBeeTx64Request(int frameID, Address remoteAddress,
			OptionsBase transmitOptions, byte[] payload, int offset, int length) {
		super(9 + payload.length, API_IDENTIFIER.Tx64_Request, frameID);
		this.SetContent(remoteAddress.GetAddressValue(), 2, 8);
		this.SetContent(transmitOptions.GetValue());
		this.SetContent(payload, offset, length);
	}

	public void SetPayload(byte[] data) {
		SetPayload(data, 0, data.length);
	}

	public void SetPayload(byte[] data, int offset, int length) {
		this.SetContent(11, data, offset, length);
		this.SetPosition(11 + length - offset);
	}

	public void SetTransmitOptions(OptionsBase transmitOptions) {
		this.SetContent(10, transmitOptions.GetValue());
	}

	// / <summary>
	// / the ieee 16 bit address is ignored
	// / </summary>
	// / <param name="networkAddress"></param>
	public void SetRemoteAddress(Address remoteAddress) {
		this.SetContent(2, remoteAddress.GetAddressValue(), 2, 8);
	}
}