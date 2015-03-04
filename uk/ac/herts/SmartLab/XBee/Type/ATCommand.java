package uk.ac.herts.SmartLab.XBee.Type;

public class ATCommand {
	public static final ATCommand IO_Sampling_Rate = new ATCommand(new byte[] {
			0x49, 0x52 });

	public static final ATCommand Digital_IO_Change_Detection = new ATCommand(
			new byte[] { 0x49, 0x43 });

	public static final ATCommand Instant_Sample = new ATCommand(new byte[] {
			0x49, 0x53 });

	public static final ATCommand Node_Descovery = new ATCommand(new byte[] {
			0x4E, 0x44 });

	public static final ATCommand Destination_Node_Descovery = new ATCommand(
			new byte[] { 0x44, 0x4E });

	public static final ATCommand Node_Identifier = new ATCommand(new byte[] {
			0x4E, 0x49 });

	public static final ATCommand Node_Join_Time = new ATCommand(new byte[] {
			0x4E, 0x4A });

	public static final ATCommand Network_Address = new ATCommand(new byte[] {
			0x4D, 0x59 });

	public static final ATCommand PAN_ID = new ATCommand(new byte[] { 0x49,
			0x44 });

	public static final ATCommand Destination_Address_High = new ATCommand(
			new byte[] { 0x44, 0x48 });

	public static final ATCommand Destination_Address_Low = new ATCommand(
			new byte[] { 0x44, 0x4C });

	public static final ATCommand Serial_Number_High = new ATCommand(
			new byte[] { 0x53, 0x48 });

	public static final ATCommand Serial_Number_Low = new ATCommand(new byte[] {
			0x53, 0x4C });

	protected byte[] value;

	public ATCommand() {
	}

	public ATCommand(byte[] commad) {
		this.value = commad;
	}

	public ATCommand(String commad) {
		this.value = commad.getBytes();
	}

	public byte[] GetValue() {
		return this.value;
	}

	public String toString() {
		return new String(this.value);
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		ATCommand command = null;

		if (obj instanceof ATCommand)
			command = (ATCommand) obj;

		if (command == null)
			return false;

		return this.value[0] == command.value[0]
				&& this.value[1] == command.value[1];
	}

	public boolean Equals(ATCommand command) {
		if (command == null)
			return false;

		return this.value[0] == command.value[0]
				&& this.value[1] == command.value[1];
	}
}
