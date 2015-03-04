package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Device.ExplicitAddress;

public class ZigBeeExplicitRxResponse extends RxPayloadBase {
	public ZigBeeExplicitRxResponse(APIFrame frame) {
		super(frame);
	}

	public byte[] GetReceivedData() {
		int length = this.GetReceivedDataLength();

		if (length <= 0)
			return null;

		byte[] cache = new byte[length];
		System.arraycopy(this.GetFrameData(), 18, cache, 0, length);
		return cache;
	}

	public int GetReceivedDataOffset() {
		return 18;
	}

	public byte GetReceivedData(int index) {
		return this.GetFrameData()[18 + index];
	}

	public int GetReceivedDataLength() {
		return this.GetPosition() - 18;
	}

	public ExplicitAddress GetExplicitRemoteDevice() {
		return (ExplicitAddress) GetRemoteDevice();
	}

	public Address GetRemoteDevice() {
		byte[] address1 = new byte[10];
		System.arraycopy(this.GetFrameData(), 18, address1, 0, 10);

		byte[] address2 = new byte[6];
		System.arraycopy(this.GetFrameData(), 18, address2, 0, 6);

		return new ExplicitAddress(address1, address2);
	}

	public int GetReceiveStatus() {
		return this.GetFrameData()[17] & 0xFF;
	}
}