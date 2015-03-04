package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;

public class ZigBeeTxStatusResponse extends TxStatusBase {
	public ZigBeeTxStatusResponse(APIFrame frame) {
		super(frame);
	}

	public int GetDeliveryStatus() {
		return this.GetFrameData()[5] & 0xFF;
	}

	public int GetDestinationAddress16() {
		return this.GetFrameData()[2] << 8 | this.GetFrameData()[3];
	}

	public byte GetTransmitRetryCount() {
		return this.GetFrameData()[4];
	}

	public int GetDiscoveryStatus() {
		return GetFrameData()[6] & 0xFF;
	}
}
