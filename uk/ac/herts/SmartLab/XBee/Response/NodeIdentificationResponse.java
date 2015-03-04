package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.Device.Address;

public class NodeIdentificationResponse extends RxBase {
	private int offset = 0;

	public NodeIdentificationResponse(APIFrame frame) {
		super(frame);
		this.offset = this.GetPosition() - 8;
	}

	public int GetReceiveStatus() {
		return this.GetFrameData()[11] & 0xFF;
	}

	public Address GetRemoteDevice() {
		byte[] cache = new byte[10];
		System.arraycopy(this.GetFrameData(), 14, cache, 0, 8);
		cache[8] = this.GetFrameData()[12];
		cache[9] = this.GetFrameData()[13];
		return new Address(cache);
	}

	public Address GetSenderDevice() {
		byte[] cache = new byte[10];
		System.arraycopy(this.GetFrameData(), 1, cache, 0, 10);
		return new Address(cache);
	}

	public String GetNIString() {
		int length = this.GetPosition() - 31;

		if (length <= 0)
			return "";

		return new String(this.GetFrameData(), 22, length);
	}

	public int GetParentNetworkAddress() {
		return this.GetFrameData()[offset] << 8
				| this.GetFrameData()[offset + 1];
	}

	public int GetDeviceType() {
		return this.GetFrameData()[offset + 2] & 0xFF;
	}

	public int GetSourceEvent() {
		return this.GetFrameData()[offset + 3] & 0xFF;
	}

	public int GetDigiProfileID() {
		return this.GetFrameData()[offset + 4] << 8
				| this.GetFrameData()[offset + 5];
	}

	public int GetManufacturerID() {
		return this.GetFrameData()[offset + 6] << 8
				| this.GetFrameData()[offset + 7];
	}
}