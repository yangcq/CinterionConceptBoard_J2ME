package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.IOSamples;
import uk.ac.herts.SmartLab.XBee.Device.Address;

public class XBeeRx64IOSampleResponse extends RxIOSampleBase {
	public XBeeRx64IOSampleResponse(APIFrame frame) {
		super(frame);
	}

	public int GetRSSI() {
		return this.GetFrameData()[9] * -1;
	}

	public IOSamples GetIOSamples() {
		return RxIOSampleBase.XBeeSamplesParse(this.GetFrameData(), 11);
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