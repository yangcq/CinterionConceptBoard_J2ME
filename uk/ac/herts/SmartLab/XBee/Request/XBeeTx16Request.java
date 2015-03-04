package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Options.OptionsBase;
import uk.ac.herts.SmartLab.XBee.Type.API_IDENTIFIER;

public class XBeeTx16Request extends TxPayloadBase {
	// / <summary>
	// / the ieee 64 bit address is ignored
	// / </summary>
	// / <param name="frameID"></param>
	// / <param name="remoteAddress"></param>
	// / <param name="transmitOptions"></param>
	// / <param name="RFData"></param>
	// / <param name="offset"></param>
	// / <param name="length"></param>
	public XBeeTx16Request(int frameID, Address remoteAddress,
			OptionsBase transmitOptions, byte[] payload) {
		this(frameID, remoteAddress, transmitOptions, payload, 0,
				payload.length);
	}

	// / <summary>
	// / the ieee 64 bit address is ignored
	// / </summary>
	// / <param name="frameID"></param>
	// / <param name="remoteAddress"></param>
	// / <param name="transmitOptions"></param>
	// / <param name="RFData"></param>
	// / <param name="offset"></param>
	// / <param name="length"></param>
	public XBeeTx16Request(int frameID, Address remoteAddress,
			OptionsBase transmitOptions, byte[] payload, int offset, int length) {
		super(3 + payload.length, API_IDENTIFIER.Tx16_Request, frameID);
		this.SetContent((byte) (remoteAddress.GetNetworkAddress() >> 8));
		this.SetContent((byte) remoteAddress.GetNetworkAddress());
		this.SetContent(transmitOptions.GetValue());
		this.SetContent(payload, offset, length);
	}

	public void SetPayload(byte[] data) {
		SetPayload(data, 0, data.length);
	}

	public void SetPayload(byte[] data, int offset, int length) {
		this.SetContent(5, data, offset, length);
		this.SetPosition(5 + length - offset);
	}

	public void SetTransmitOptions(OptionsBase transmitOptions) {
		this.SetContent(4, transmitOptions.GetValue());
	}

	// / <summary>
	// / the ieee 64 bit address is ignored
	// / </summary>
	// / <param name="networkAddress"></param>
	public void SetRemoteAddress(Address remoteAddress) {
		this.SetContent(2, (byte) (remoteAddress.GetNetworkAddress() >> 8));
		this.SetContent(3, (byte) (remoteAddress.GetNetworkAddress()));
	}
}