package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Type.API_IDENTIFIER;
import uk.ac.herts.SmartLab.XBee.Type.ATCommand;

public class ATCommandRequest extends CommandRequestBase {
	// 0x08 or 0x09
	// FrameID
	// AT_Command
	// Parameter_Value

	// / <summary>
	// /
	// / </summary>
	// / <param name="FrameID"></param>
	// / <param name="AT_Command"></param>
	// / <param name="Parameter_Value">this can be null</param>
	public ATCommandRequest(int frameID, ATCommand command, byte[] parameter) {
		this(frameID, command, parameter, 0, parameter == null ? 0
				: parameter.length);
	}

	// / <summary>
	// /
	// / </summary>
	// / <param name="FrameID"></param>
	// / <param name="AT_Command"></param>
	// / <param name="Parameter_Value">this can be null</param>
	public ATCommandRequest(int frameID, ATCommand command, byte[] parameter,
			int offset, int length) {
		super(2 + (parameter == null ? 0 : parameter.length),
				API_IDENTIFIER.AT_Command, frameID);
		this.SetContent(command.GetValue());

		if (parameter != null)
			this.SetContent(parameter, offset, length);
	}

	public void SetAppleChanges(boolean appleChanges) {
		if (appleChanges)
			SetFrameType(API_IDENTIFIER.AT_Command);
		else
			SetFrameType(API_IDENTIFIER.AT_Command_Queue_Parameter_Value);
	}

	public void SetCommand(ATCommand command) {
		this.SetContent(2, command.GetValue());
	}

	public void SetParameter(byte[] parameter) {
		this.SetContent(4, parameter, 0, parameter.length);
	}

	public void SetParameter(byte[] parameter, int offset, int length) {
		this.SetContent(4, parameter, offset, length);
		this.SetPosition(4 + length - offset);
	}
}