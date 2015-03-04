package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;

public abstract class TxStatusBase extends RxBase {
	public TxStatusBase(APIFrame frame) {
		super(frame);
	}

	public int GetFrameID() {
		return this.GetFrameData()[1];
	}

	public abstract int GetDeliveryStatus();
}
