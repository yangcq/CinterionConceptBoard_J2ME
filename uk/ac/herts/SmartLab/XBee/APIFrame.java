package uk.ac.herts.SmartLab.XBee;

public class APIFrame {
	public static final byte StartDelimiter = 0x7E;
	private static final int EXPANDSIZE = 100;

	// / <summary>
	// / payload length not include the checksum
	// / </summary>
	private int position = 0;

	// / <summary>
	// / payload content not include the checksum, the valid length is indicated
	// by this.Length
	// / !! do not use FrameData.Length, this is not the packet's payload length
	// / </summary>
	private byte[] FrameData;

	private int CheckSum;

	// / <summary>
	// / a state to indicate whether this packet's checksum is verified while
	// process
	// / </summary>
	private boolean isVerify = false;

	public APIFrame(APIFrame frame) {
		this.FrameData = frame.FrameData;
		this.position = frame.position;
		this.CheckSum = frame.CheckSum;
		this.isVerify = frame.isVerify;
	}

	public APIFrame(int payloadLength) {
		this.FrameData = new byte[payloadLength];
	}

	public int GetFrameType() {
		return FrameData[0] & 0xFF;
	}

	// / <summary>
	// / this does not affect the position
	// / </summary>
	// / <param name="identifier"></param>
	public void SetFrameType(int identifier) {
		this.SetContent(0, (byte) identifier);
	}

	public int GetPosition() {
		return this.position;
	}

	public void SetPosition(int position) {
		if (position >= this.FrameData.length)
			this.position = this.FrameData.length;
		else
			this.position = position;
	}

	public void Allocate(int length) {
		if (length <= 0)
			return;

		if (length > this.FrameData.length)
			this.FrameData = new byte[length];

		this.Rewind();
	}

	public void Rewind() {
		this.position = 0;
		this.isVerify = false;
	}

	// / <summary>
	// / write the value into the current posiont and the posiont + 1
	// / will create more space if position overflow
	// / </summary>
	// / <param name="value"></param>
	public void SetContent(byte value) {
		SetContent(this.position, value);
		this.position++;
	}

	// / <summary>
	// / write the value into anywhere and the current positon not affected
	// / will create more space if position overflow
	// / </summary>
	// / <param name="offset"></param>
	// / <param name="value"></param>
	public void SetContent(int index, byte value) {
		if (index < 0)
			return;

		if (index >= this.FrameData.length)
			ExpandSpace(1);

		this.FrameData[index] = value;
	}

	// / <summary>
	// / write the value into the current posiont and the posiont + value.length
	// / will create more space if position overflow
	// / </summary>
	// / <param name="value"></param>
	public void SetContent(byte[] value) {
		SetContent(value, 0, value.length);
	}

	// / <summary>
	// / write the value into anywhere and the current positon not affected
	// / will create more space if position overflow
	// / </summary>
	// / <param name="index"></param>
	// / <param name="value"></param>
	public void SetContent(int index, byte[] value) {
		SetContent(index, value, 0, value.length);
	}

	// / <summary>
	// / write the value into the current posiont and the posiont + length
	// / will create more space if position overflow
	// / </summary>
	// / <param name="value"></param>
	// / <param name="offset"></param>
	// / <param name="length"></param>
	public void SetContent(byte[] value, int offset, int length) {
		SetContent(position, value, offset, length);
		position += length;
	}

	// / <summary>
	// / write the value into anywhere and the current positon not affected
	// / will create more space if position overflow
	// / </summary>
	// / <param name="index"></param>
	// / <param name="value"></param>
	// / <param name="offset"></param>
	// / <param name="length"></param>
	public void SetContent(int index, byte[] value, int offset, int length) {
		if (index + length - offset > this.FrameData.length)
			ExpandSpace(index + length - offset - this.FrameData.length);

		System.arraycopy(value, 0, this.FrameData, index, length);
	}

	public byte[] GetFrameData() {
		return this.FrameData;
	}

	public int GetCheckSum() {
		return CheckSum;
	}

	public void SetCheckSum(int value) {
		this.CheckSum = value;
	}

	public boolean VerifyChecksum() {
		if (isVerify)
			return true;

		int temp = 0x00;
		for (int i = 0; i < this.position; i++)
			temp += this.FrameData[i];
		if (((temp + this.CheckSum) & 0xFF) == 0xFF)
			isVerify = true;
		else
			isVerify = false;

		return isVerify;
	}

	public void CalculateChecksum() {
		if (isVerify)
			return;

		int CS = 0x00;
		for (int i = 0; i < this.position; i++)
			CS += this.FrameData[i];
		this.CheckSum = (0xFF - CS) & 0xFF;
		this.isVerify = true;
	}

	private void ExpandSpace(int length) {
		byte[] temp = this.FrameData;
		this.FrameData = new byte[this.FrameData.length + EXPANDSIZE
				* (1 + length / EXPANDSIZE)];
		System.arraycopy(temp, 0, this.FrameData, 0, this.position);

		this.Rewind();
	}
}