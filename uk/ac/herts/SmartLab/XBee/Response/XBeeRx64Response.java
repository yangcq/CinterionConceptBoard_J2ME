package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.Device.Address;

public class XBeeRx64Response extends RxPayloadBase {
	public XBeeRx64Response(APIFrame frame) {
		super(frame);
	}

	public byte[] GetReceivedData() {
		int length = this.GetReceivedDataLength();

		if (length <= 0)
			return null;

		byte[] cache = new byte[length];
		System.arraycopy(this.GetFrameData(), 11, cache, 0, length);
		return cache;
	}

	public int GetReceivedDataOffset() {
		return 11;
	}

	public byte GetReceivedData(int index) {
		return this.GetFrameData()[11 + index];
	}

	public int GetReceivedDataLength() {
		return this.GetPosition() - 11;
	}

	public int GetRSSI() {
		return this.GetFrameData()[9] * -1;
	}

	public int GetReceiveStatus() {
		return this.GetFrameData()[10] & 0xFF;
	}

	public Address GetRemoteDevice() {
		return new Address(new byte[] { GetFrameData()[1], GetFrameData()[2],
				GetFrameData()[3], GetFrameData()[4], GetFrameData()[5],
				GetFrameData()[6], GetFrameData()[7], GetFrameData()[8], 0x00,
				0x00 });
	}
}