package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Device.Pin;
import uk.ac.herts.SmartLab.XBee.Options.RemoteCommandOptions;
import uk.ac.herts.SmartLab.XBee.Type.ATCommand;

public class RemoteIODetectionConfigRequest extends RemoteATCommandRequest {
	public RemoteIODetectionConfigRequest(int frameID, Address remoteAddress,
			Pin[] pins) {
		super(frameID, remoteAddress, ATCommand.Digital_IO_Change_Detection,
				RemoteCommandOptions.ApplyChanges, Pin
						.IOChangeDetectionConfiguration(pins));
	}
}