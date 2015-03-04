package uk.ac.herts.SmartLab.XBee.Device;

public class ExplicitAddress extends Address {
	// total 6 bytes
	// 1 byte of SourceEndpoint + 1 byte of DestinationEndpoint + 2 bytes of
	// ClusterID + 2 bytes of ProfileID
	private byte[] ExplicitValue;

	public ExplicitAddress() {
		this.ExplicitValue = new byte[6];
	}

	public ExplicitAddress(byte[] AddressValue, byte[] ExplicitValue) {
		super(AddressValue);
		this.ExplicitValue = ExplicitValue;
	}

	public ExplicitAddress(int SerialNumberHigh, int SerialNumberLow,
			int NetworkAddress, int SourceEndpoint, int DestinationEndpoint,
			int ClusterID, int ProfileID) {
		super(SerialNumberHigh, SerialNumberLow, NetworkAddress);
		
		ExplicitValue = new byte[6];

		ExplicitValue[0] = (byte) SourceEndpoint;
		ExplicitValue[1] = (byte) DestinationEndpoint;
		ExplicitValue[2] = (byte) (ClusterID >> 8);
		ExplicitValue[3] = (byte) ClusterID;
		ExplicitValue[4] = (byte) (ProfileID >> 8);
		ExplicitValue[5] = (byte) ProfileID;
	}

	// / <summary>
	// / total 6 bytes
	// / 1 byte of SourceEndpoint + 1 byte of DestinationEndpoint + 2 bytes of
	// ClusterID + 2 bytes of ProfileID
	// / </summary>
	// / <returns></returns>
	public byte[] GetExplicitValue() {
		return ExplicitValue;
	}

	public int GetSourceEndpoint() {
		return ExplicitValue[0];
	}

	public void SetSourceEndpoint(int SourceEndpoint) {
		ExplicitValue[0] = (byte) SourceEndpoint;
	}

	public int GetDestinationEndpoint() {
		return ExplicitValue[1];
	}

	public void SetDestinationEndpoint(int DestinationEndpoint) {
		ExplicitValue[1] = (byte) DestinationEndpoint;
	}

	public int GetClusterID() {
		return (ExplicitValue[2] << 8) | ExplicitValue[3];
	}

	public void SetClusterID(int ClusterID) {
		ExplicitValue[2] = (byte) (ClusterID >> 8);
		ExplicitValue[3] = (byte) ClusterID;
	}

	public int GetProfileID() {
		return (ExplicitValue[4] << 8) | ExplicitValue[5];
	}

	public void SetProfileID(int ProfileID) {
		ExplicitValue[4] = (byte) (ProfileID >> 8);
		ExplicitValue[5] = (byte) ProfileID;
	}
}