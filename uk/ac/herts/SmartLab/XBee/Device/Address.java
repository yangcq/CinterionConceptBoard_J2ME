package uk.ac.herts.SmartLab.XBee.Device;

public class Address {
	public static final Address BROADCAST_ZIGBEE = new Address(0x00000000,
			0x0000FFFF, 0xFFFE);
	public static final Address BROADCAST_XBEE = new Address(0x00000000,
			0x00000000, 0xFFFF);

	// total 10 bytes
	// IEEE 64 + 16bit networ address
	protected byte[] value;

	// / <summary>
	// / create empty address : 0x00000000 0x00000000 0x0000
	// / this is the default ZigBee Coordinatior
	// / </summary>
	public Address() {
		value = new byte[10];
	}

	// / <summary>
	// / create address from byte[8 + 2] value : 8 bytes of ieee + 2 bytes
	// network
	// / </summary>
	// / <param name="Address64"></param>
	// / <param name="NET16"></param>
	public Address(byte[] Address64, byte[] NET16) {
		this.value = new byte[] { Address64[0], Address64[1], Address64[2],
				Address64[3], Address64[4], Address64[5], Address64[6],
				Address64[7], NET16[0], NET16[1] };
	}

	// / <summary>
	// / create address from byte[10] value : 8 bytes of ieee follow 2 bytes
	// network
	// / </summary>
	// / <param name="value"></param>
	public Address(byte[] value) {
		this.value = value;
	}

	public Address(int SerialNumberHigh, int SerialNumberLow, int NetworkAddress) {
		value = new byte[10];

		SetSerialNumberHigh(SerialNumberHigh);
		SetSerialNumberLow(SerialNumberLow);
		SetNetworkAddress(NetworkAddress);
	}

	public int GetSerialNumberHigh() {
		return (value[0] << 24) | (value[1] << 16) | (value[2] << 8) | value[3];
	}

	public int GetSerialNumberLow() {
		return (value[4] << 24) | (value[5] << 16) | (value[6] << 8) | value[7];
	}

	public int GetNetworkAddress() {
		return (value[8] << 8) | value[9];
	}

	public void SetSerialNumberHigh(int SerialNumberHigh) {
		value[0] = (byte) (SerialNumberHigh >> 24);
		value[1] = (byte) (SerialNumberHigh >> 16);
		value[2] = (byte) (SerialNumberHigh >> 8);
		value[3] = (byte) SerialNumberHigh;
	}

	public void SetSerialNumberLow(int SerialNumberLow) {
		value[4] = (byte) (SerialNumberLow >> 24);
		value[5] = (byte) (SerialNumberLow >> 16);
		value[6] = (byte) (SerialNumberLow >> 8);
		value[7] = (byte) SerialNumberLow;
	}

	public void SetNetworkAddress(int NetworkAddress) {
		value[8] = (byte) (NetworkAddress >> 8);
		value[9] = (byte) NetworkAddress;
	}

	// / <summary>
	// / total 10 bytes
	// / IEEE 64 + 16bit networ address
	// / </summary>
	// / <returns></returns>
	public byte[] GetAddressValue() {
		return value;
	}

	// / <summary>
	// / extension method for convert DN / ND (with or without NI String)
	// response to address
	// / </summary>
	// / <param name="response">muset be non null parameter</param>
	// / <returns></returns>
	/*
	 * public static Address Parse(ICommandResponse response) { byte[] message =
	 * response.GetParameter(); if (message != null) if
	 * (response.GetRequestCommand().ToString().ToUpper() == "ND") { Address
	 * device = new Address();
	 * 
	 * device.value[0] = message[2]; device.value[1] = message[3];
	 * device.value[2] = message[4]; device.value[3] = message[5];
	 * device.value[4] = message[6]; device.value[5] = message[7];
	 * device.value[6] = message[8]; device.value[7] = message[9];
	 * 
	 * device.value[8] = message[0]; device.value[9] = message[1];
	 * 
	 * return device; } return null; }
	 */

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		Address address = null;
		if (obj instanceof Address)
			address = (Address) obj;

		if (address == null)
			return false;

		return this.GetSerialNumberHigh() == address.GetSerialNumberHigh()
				&& this.GetSerialNumberLow() == address.GetSerialNumberLow();
	}

	public boolean Equals(Address address) {
		if (address == null)
			return false;

		return this.GetSerialNumberHigh() == address.GetSerialNumberHigh()
				&& this.GetSerialNumberLow() == address.GetSerialNumberLow();
	}
}
