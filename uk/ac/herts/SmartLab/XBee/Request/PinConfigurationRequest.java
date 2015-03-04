package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Device.Pin;
import uk.ac.herts.SmartLab.XBee.Type.ATCommand;

public class PinConfigurationRequest extends ATCommandRequest {
	public PinConfigurationRequest(int frameID, Pin pin, int function) {
		super(frameID, new ATCommand(pin.getCommand()),
				new byte[] { (byte) function });
	}

	public void SetPinFunction(int functions) {
		this.SetContent(4, (byte) functions);
	}
}
