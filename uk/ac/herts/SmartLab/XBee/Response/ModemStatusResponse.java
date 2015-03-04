package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;

public class ModemStatusResponse extends RxBase {
	public ModemStatusResponse(APIFrame frame) {
		super(frame);
	}

	public int GetModemStatus() {
		return this.GetFrameData()[1] & 0xFF;
	}
}