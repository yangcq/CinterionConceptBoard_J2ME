package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Type.ATCommand;

public class RemoteCommandResponse extends CommandResponseBase {
	public RemoteCommandResponse(APIFrame frame) {
		super(frame);
	}

	public ATCommand GetRequestCommand() {
		return new ATCommand(new byte[] { this.GetFrameData()[12],
				this.GetFrameData()[13] });
	}

	public int GetCommandStatus() {
		return this.GetFrameData()[14] & 0xFF;
	}

	public Address GetRemoteDevice() {
		byte[] cache = new byte[10];
		System.arraycopy(this.GetFrameData(), 2, cache, 0, 10);
		return new Address(cache);
	}

	public byte[] GetParameter() {
		int length = this.GetParameterLength();

		if (length <= 0)
			return null;

		byte[] cache = new byte[length];
		System.arraycopy(this.GetFrameData(), 15, cache, 0, length);
		return cache;
	}

	public byte GetParameter(int index) {
		return this.GetFrameData()[15 + index];
	}

	public int GetParameterLength() {
		return this.GetPosition() - 15;
	}

	public int GetParameterOffset() {
		return 15;
	}
}