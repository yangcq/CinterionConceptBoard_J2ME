package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.Device.Address;
public class ZigBeeRxResponse extends RxPayloadBase {
	public ZigBeeRxResponse(APIFrame frame) {
		super(frame);
	}

	public byte[] GetReceivedData() {
		int length = this.GetReceivedDataLength();

		if (length <= 0)
			return null;

		byte[] cache = new byte[length];
		System.arraycopy(this.GetFrameData(), 12, cache, 0, length);
		return cache;
	}

	public int GetReceivedDataOffset() {
		return 12;
	}

	public byte GetReceivedData(int index) {
		return this.GetFrameData()[12 + index];
	}

	public int GetReceivedDataLength() {
		return this.GetPosition() - 12;
	}

	public int GetReceiveStatus() {
		return this.GetFrameData()[11] & 0xFF;
	}

	public Address GetRemoteDevice() {
		byte[] cache = new byte[10];
		System.arraycopy(this.GetFrameData(), 1, cache, 0, 10);
		return new Address(cache);
	}

}