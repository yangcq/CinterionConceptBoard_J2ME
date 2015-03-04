package uk.ac.herts.SmartLab.XBee.Response;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.Type.ATCommand;

public class ATCommandResponse extends CommandResponseBase {
	public ATCommandResponse(APIFrame frame) {
		super(frame);
	}

	public ATCommand GetRequestCommand() {
		return new ATCommand(new byte[] { this.GetFrameData()[2],
				this.GetFrameData()[3] });
	}

	public int GetCommandStatus() {
		return this.GetFrameData()[4] & 0xFF;
	}

	// / <summary>
	// / if parameter not presented, null will be returned.
	// / </summary>
	// / <returns></returns>

	public byte[] GetParameter() {
		int length = this.GetParameterLength();

		if (length <= 0)
			return null;

		byte[] cache = new byte[length];
		System.arraycopy(this.GetFrameData(), 5, cache, 0, length);
		return cache;
	}

	public byte GetParameter(int index) {
		return this.GetFrameData()[5 + index];
	}

	public int GetParameterLength() {
		return this.GetPosition() - 5;
	}

	public int GetParameterOffset() {
		return 5;
	}
}