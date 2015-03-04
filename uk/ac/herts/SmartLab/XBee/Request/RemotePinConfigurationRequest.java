package uk.ac.herts.SmartLab.XBee.Request;

import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Device.Pin;
import uk.ac.herts.SmartLab.XBee.Options.RemoteCommandOptions;
import uk.ac.herts.SmartLab.XBee.Type.ATCommand;

public class RemotePinConfigurationRequest extends RemoteATCommandRequest {
	public RemotePinConfigurationRequest(int frameID, Address remoteAddress,
			Pin pin, int function) {
		super(frameID, remoteAddress, new ATCommand(pin.getCommand()),
				RemoteCommandOptions.ApplyChanges,
				new byte[] { (byte) function });
	}

	public void SetPinFunction(int functions) {
		this.SetContent(15, (byte) functions);
	}
}