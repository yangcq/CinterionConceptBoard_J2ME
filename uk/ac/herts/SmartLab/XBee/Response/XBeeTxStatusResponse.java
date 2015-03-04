package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;

public class XBeeTxStatusResponse extends TxStatusBase {
	public XBeeTxStatusResponse(APIFrame frame) {
		super(frame);
	}

	public int GetDeliveryStatus() {
		return this.GetFrameData()[2] & 0xFF;
	}
}