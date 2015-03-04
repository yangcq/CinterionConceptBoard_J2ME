package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.IOSamples;
import uk.ac.herts.SmartLab.XBee.Device.Address;

public class ZigBeeIOSampleResponse extends RxIOSampleBase {
	public ZigBeeIOSampleResponse(APIFrame frame) {
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

	public IOSamples GetIOSamples() {
		return RxIOSampleBase.ZigBeeSamplesParse(this.GetFrameData(), 12);
	}
}