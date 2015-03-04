package uk.ac.herts.SmartLab.XBee.Options;

public class Tx64TransmitOptions extends OptionsBase {
	// 0x01 - Disable retries and route repair (XTEND and XBEE)
	// 0x02 - Don't repeat this packet (not implemented)
	// 0x04 - Send packet with Broadcast Pan ID (XBEE only)
	// 0x08 - Invoke Traceroute (XTEND version 8030 only)
	// 0x10 (XB868DP) If the packet would be delayed due to duty cycle then
	// purge it. All other bits must be set to 0

	public boolean GetDonotRepeatPacket() {
		if ((this.value & 0x02) == 0x02)
			return true;
		else
			return false;
	}

	public void SetDonotRepeatPacket(boolean status) {
		if (status)
			this.value = (byte) (this.value | 0x02);
		else
			this.value = (byte) (this.value & 0xFD);
	}

	public boolean GetSendPacketWithBroadcastPanID() {
		if ((this.value & 0x04) == 0x04)
			return true;
		else
			return false;
	}

	public void SetSendPacketWithBroadcastPanID(boolean status) {
		if (status)
			this.value = (byte) (this.value | 0x04);
		else
			this.value = (byte) (this.value & 0xFB);
	}

	public boolean GetInvokeTraceroute() {
		if ((this.value & 0x08) == 0x08)
			return true;
		else
			return false;
	}

	public void SetInvokeTraceroute(boolean status) {
		if (status)
			this.value = (byte) (this.value | 0x08);
		else
			this.value = (byte) (this.value & 0xF7);
	}

	public boolean GetPurgePacketWhenDelayed() {
		if ((this.value & 0x10) == 0x10)
			return true;
		else
			return false;
	}

	public void SetPurgePacketWhenDelayed(boolean status) {
		if (status)
			this.value = (byte) (this.value | 0x10);
		else
			this.value = (byte) (this.value & 0xEF);
	}

	public static final Tx64TransmitOptions DonotRepeatPacket = new Tx64TransmitOptions(
			(byte) 0x02);

	public static final Tx64TransmitOptions SendPacketWithBroadcastPanID = new Tx64TransmitOptions(
			(byte) 0x04);

	public static final Tx64TransmitOptions InvokeTraceroute = new Tx64TransmitOptions(
			(byte) 0x08);

	public static final Tx64TransmitOptions PurgePacketWhenDelayed = new Tx64TransmitOptions(
			(byte) 0x10);

	public Tx64TransmitOptions() {
	}

	public Tx64TransmitOptions(byte value) {
		super(value);
	}

	public Tx64TransmitOptions(boolean disable_retries_and_route_repair,
			boolean donot_repeat_packet,
			boolean send_packet_with_broadcast_PanID,
			boolean invoke_traceroute,
			boolean purge_packet_if_delayed_due_to_duty_cycle) {
		value = 0x00;
		if (disable_retries_and_route_repair)
			value = (byte) (value | 0x01);
		if (donot_repeat_packet)
			value = (byte) (value | 0x02);
		if (send_packet_with_broadcast_PanID)
			value = (byte) (value | 0x04);
		if (invoke_traceroute)
			value = (byte) (value | 0x08);
		if (purge_packet_if_delayed_due_to_duty_cycle)
			value = (byte) (value | 0x10);
	}
}
