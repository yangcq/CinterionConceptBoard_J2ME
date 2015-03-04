package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.Device.Address;

public class XBeeRx16Response extends RxPayloadBase {
	public XBeeRx16Response(APIFrame frame) {
		super(frame);
	}

	public byte[] GetReceivedData() {
		int length = this.GetReceivedDataLength();

		if (length <= 0)
			return null;

		byte[] cache = new byte[length];
		System.arraycopy(this.GetFrameData(), 5, cache, 0, length);
		return cache;
	}

	public int GetReceivedDataOffset() {
		return 5;
	}

	public byte GetReceivedData(int index) {
		return this.GetFrameData()[5 + index];
	}

	public int GetReceivedDataLength() {
		return this.GetPosition() - 5;
	}

	public int GetRSSI() {
		return this.GetFrameData()[3] * -1;
	}

	public int GetReceiveStatus() {
		return this.GetFrameData()[4] & 0xFF;
	}

	public Address GetRemoteDevice() {
		return new Address(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x00, GetFrameData()[1], GetFrameData()[2] });
	}
}