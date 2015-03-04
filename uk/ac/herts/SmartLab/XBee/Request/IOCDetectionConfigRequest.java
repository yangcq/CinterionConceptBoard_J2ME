package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Device.Pin;
import uk.ac.herts.SmartLab.XBee.Type.ATCommand;

public class IOCDetectionConfigRequest extends ATCommandRequest {
	public IOCDetectionConfigRequest(int frameID, Pin[] pins) {
		super(frameID, ATCommand.Digital_IO_Change_Detection, Pin
				.IOChangeDetectionConfiguration(pins));
	}
}