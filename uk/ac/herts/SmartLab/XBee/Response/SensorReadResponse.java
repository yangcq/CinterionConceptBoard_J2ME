package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.Device.Address;

public class SensorReadResponse extends RxBase {
	public SensorReadResponse(APIFrame frame) {
		super(frame);
	}

	public Address GetRemoteDevice() {
		byte[] cache = new byte[10];
		System.arraycopy(this.GetFrameData(), 1, cache, 0, 10);
		return new Address(cache);
	}

	public int GetReceiveStatus() {
		return this.GetFrameData()[11] & 0xFF;
	}

	public int GetOneWireSensor() {
		return this.GetFrameData()[12] & 0xFF;
	}

	public int GetAD0() {
		return this.GetFrameData()[13] << 8 | this.GetFrameData()[14];
	}

	public int GetAD1() {
		return this.GetFrameData()[15] << 8 | this.GetFrameData()[16];
	}

	public int GetAD2() {
		return this.GetFrameData()[17] << 8 | this.GetFrameData()[18];
	}

	public int GetAD3() {
		return this.GetFrameData()[19] << 8 | this.GetFrameData()[20];
	}

	public int GetThemometer() {
		return this.GetFrameData()[21] << 8 | this.GetFrameData()[22];
	}
}