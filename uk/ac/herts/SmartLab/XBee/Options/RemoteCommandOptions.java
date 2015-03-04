package uk.ac.herts.SmartLab.XBee.Options;

public class RemoteCommandOptions extends TransmitOptions {
	// 0x00 - Default
	// 0x01 - Disable retries and route repair
	// 0x02 - Apply changes.
	// 0x20 - Enable APS encryption (if EE=1)
	// 0x40 - Use the extended transmission timeout

	public static final RemoteCommandOptions ApplyChanges = new RemoteCommandOptions(
			(byte) 0x02);

	public boolean GetApplyChanges() {
		if ((this.value & 0x02) == 0x02)
			return true;
		else
			return false;
	}

	public void SetApplyChanges(boolean status) {
		if (status)
			this.value = (byte) (this.value | 0x02);
		else
			this.value = (byte) (this.value & 0xFD);
	}

	public RemoteCommandOptions() {
	}

	public RemoteCommandOptions(byte value) {
		super(value);
	}

	public RemoteCommandOptions(boolean disable_retries_and_route_repair,
			boolean apply_changes, boolean enable_APS_encryption,
			boolean use_extended_transmission_timeout) {
		super(disable_retries_and_route_repair, enable_APS_encryption,
				use_extended_transmission_timeout);
		if (apply_changes)
			value = (byte) (value | 0x02);
	}
}