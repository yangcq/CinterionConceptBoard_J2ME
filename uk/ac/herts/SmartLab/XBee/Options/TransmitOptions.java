package uk.ac.herts.SmartLab.XBee.Options;

public class TransmitOptions extends OptionsBase {
	// Default = 0x00;
	// Disable_Retries_Route_Repair = 0x01;
	// Enable_APS = 0x20;
	// Use_Extended_Timeout = 0x40;

	public boolean GetEnableAPS() {
		if ((this.value & 0x20) == 0x20)
			return true;
		else
			return false;
	}

	public void SetEnableAPS(boolean status) {
		if (status)
			this.value = (byte) (this.value | 0x20);
		else
			this.value = (byte) (this.value & 0xDF);
	}

	public boolean GetUseExtendedTimeout() {
		if ((this.value & 0x40) == 0x40)
			return true;
		else
			return false;
	}

	public void SetUseExtendedTimeout(boolean status) {
		if (status)
			this.value = (byte) (this.value | 0x40);
		else
			this.value = (byte) (this.value & 0xBF);
	}

	public static final TransmitOptions EnableAPS = new TransmitOptions(
			(byte) 0x20);

	public static final TransmitOptions UseExtendedTimeout = new TransmitOptions(
			(byte) 0x40);

	public TransmitOptions() {
	}

	public TransmitOptions(byte value) {
		super(value);
	}

	public TransmitOptions(boolean disable_retries_and_route_repair,
			boolean enable_APS_encryption,
			boolean use_extended_transmission_timeout) {
		value = 0x00;
		if (disable_retries_and_route_repair)
			value = (byte) (value | 0x01);
		if (enable_APS_encryption)
			value = (byte) (value | 0x20);
		if (use_extended_transmission_timeout)
			value = (byte) (value | 0x40);
	}
}