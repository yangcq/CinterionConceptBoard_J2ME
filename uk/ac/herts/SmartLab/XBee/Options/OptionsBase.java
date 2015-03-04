package uk.ac.herts.SmartLab.XBee.Options;

public class OptionsBase {
	public static final OptionsBase DEFAULT = new OptionsBase();

	public static final OptionsBase DisableRetriesRouteRepair = new OptionsBase(
			(byte) 0x01);

	protected byte value;

	public boolean GetDisableRetriesRouteRepair() {
		if ((this.value & 0x01) == 0x01)
			return true;
		else
			return false;
	}

	public void SetDisableRetriesRouteRepair(boolean status) {
		if (status)
			this.value = (byte) (this.value | 0x01);
		else
			this.value = (byte) (this.value & 0xFE);
	}

	public OptionsBase(byte value) {
		this.value = value;
	}

	public OptionsBase() {
		this.value = 0x00;
	}

	public byte GetValue() {
		return this.value;
	}
}
