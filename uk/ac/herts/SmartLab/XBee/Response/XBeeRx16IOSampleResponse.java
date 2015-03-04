package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.IOSamples;
import uk.ac.herts.SmartLab.XBee.Device.Address;

public class XBeeRx16IOSampleResponse extends RxIOSampleBase {
	public XBeeRx16IOSampleResponse(APIFrame frame) {
		super(frame);
	}

	public int GetRSSI() {
		return GetFrameData()[3] * -1;
	}

	public IOSamples GetIOSamples() {
		return RxIOSampleBase.XBeeSamplesParse(this.GetFrameData(), 5);
	}

	public int GetReceiveStatus() {
		return this.GetFrameData()[4] & 0xFF;
	}

	public Address GetRemoteDevice() {
		return new Address(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x00, GetFrameData()[1], GetFrameData()[2] });
	}
}