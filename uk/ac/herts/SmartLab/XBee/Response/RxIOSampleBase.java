package uk.ac.herts.SmartLab.XBee.Response;

import java.util.Hashtable;

import uk.ac.herts.SmartLab.XBee.APIFrame;
import uk.ac.herts.SmartLab.XBee.IOSamples;
import uk.ac.herts.SmartLab.XBee.Device.Address;
import uk.ac.herts.SmartLab.XBee.Device.Pin;

public abstract class RxIOSampleBase extends RxBase {
	public RxIOSampleBase(APIFrame frame) {
		super(frame);
	}

	public static IOSamples XBeeSamplesParse(byte[] IOSamplePayload, int offset) {
		if (IOSamplePayload.length - offset <= 3)
			return null;

		Hashtable Digital = new Hashtable();
		Hashtable Analog = new Hashtable();
		int index = 0;

		if ((IOSamplePayload[offset + 1] & 0x01) + IOSamplePayload[offset + 2] == 0)// digital
																					// mask
			index = 3;
		else {
			index = 5;
			if ((IOSamplePayload[offset + 2] & 0x01) == 0x01)
				Digital.put(
						Pin.XBee.AD0_DIO0,
						(IOSamplePayload[offset + 4] & 0x01) == 0x01 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x02) == 0x02)
				Digital.put(
						Pin.XBee.AD1_DIO1,
						(IOSamplePayload[offset + 4] & 0x02) == 0x02 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x04) == 0x04)
				Digital.put(
						Pin.XBee.AD2_DIO2,
						(IOSamplePayload[offset + 4] & 0x04) == 0x04 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x08) == 0x08)
				Digital.put(
						Pin.XBee.AD3_DIO3,
						(IOSamplePayload[offset + 4] & 0x08) == 0x08 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x10) == 0x10)
				Digital.put(
						Pin.XBee.AD4_DIO4,
						(IOSamplePayload[offset + 4] & 0x10) == 0x10 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x20) == 0x20)
				Digital.put(
						Pin.XBee.ASSOCIATE_AD5_DIO5,
						(IOSamplePayload[offset + 4] & 0x20) == 0x20 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x40) == 0x40)
				Digital.put(
						Pin.XBee.RTS_AD6_DIO6,
						(IOSamplePayload[offset + 4] & 0x40) == 0x40 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x80) == 0x80)
				Digital.put(
						Pin.XBee.CTS_DIO7,
						(IOSamplePayload[offset + 4] & 0x80) == 0x80 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 1] & 0x01) == 0x01)
				Digital.put(
						Pin.XBee.DTR_SLEEP_DIO8,
						(IOSamplePayload[offset + 3] & 0x01) == 0x01 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
		}
		if ((IOSamplePayload[offset + 1] & 0x02) == 0x02) {
			Analog.put(Pin.XBee.AD0_DIO0, new Integer(IOSamplePayload[offset
					+ index] << 8
					| IOSamplePayload[offset + index + 1]));
			index += 2;
		}
		if ((IOSamplePayload[offset + 1] & 0x04) == 0x04) {
			Analog.put(Pin.XBee.AD1_DIO1, new Integer(IOSamplePayload[offset
					+ index] << 8
					| IOSamplePayload[offset + index + 1]));
			index += 2;
		}
		if ((IOSamplePayload[offset + 1] & 0x08) == 0x08) {
			Analog.put(Pin.XBee.AD2_DIO2, new Integer(IOSamplePayload[offset
					+ index] << 8
					| IOSamplePayload[offset + index + 1]));
			index += 2;
		}
		if ((IOSamplePayload[offset + 1] & 0x10) == 0x10) {
			Analog.put(Pin.XBee.AD3_DIO3, new Integer(IOSamplePayload[offset
					+ index] << 8
					| IOSamplePayload[offset + index + 1]));
			index += 2;
		}
		if ((IOSamplePayload[offset + 1] & 0x20) == 0x20) {
			Analog.put(Pin.XBee.AD4_DIO4, new Integer(IOSamplePayload[offset
					+ index] << 8
					| IOSamplePayload[offset + index + 1]));
			index += 2;
		}
		if ((IOSamplePayload[offset + 1] & 0x40) == 0x40)
			Analog.put(Pin.XBee.ASSOCIATE_AD5_DIO5, new Integer(
					IOSamplePayload[offset + index] << 8
							| IOSamplePayload[offset + index + 1]));

		return new IOSamples(Analog, Digital, 0);
	}

	public static IOSamples ZigBeeSamplesParse(byte[] IOSamplePayload,
			int offset) {
		if (IOSamplePayload.length - offset <= 3)
			return null;

		Hashtable Digital = new Hashtable();
		Hashtable Analog = new Hashtable();
		int SUPPLY_VOLTAGE = 0;
		int index = 0;

		if (IOSamplePayload[offset + 1] + IOSamplePayload[offset + 2] == 0)// digital
																			// mask
			index = 4;
		else {
			index = 6;
			if ((IOSamplePayload[offset + 2] & 0x01) == 0x01)
				Digital.put(
						Pin.ZigBee.AD0_DIO0_COMMISSIONONG_BUTTON,
						(IOSamplePayload[offset + 5] & 0x01) == 0x01 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x02) == 0x02)
				Digital.put(
						Pin.ZigBee.AD1_DIO1,
						(IOSamplePayload[offset + 5] & 0x02) == 0x02 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x04) == 0x04)
				Digital.put(
						Pin.ZigBee.AD2_DIO2,
						(IOSamplePayload[offset + 5] & 0x04) == 0x04 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x08) == 0x08)
				Digital.put(
						Pin.ZigBee.AD3_DIO3,
						(IOSamplePayload[offset + 5] & 0x08) == 0x08 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x10) == 0x10)
				Digital.put(
						Pin.ZigBee.DIO4,
						(IOSamplePayload[offset + 5] & 0x10) == 0x10 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x20) == 0x20)
				Digital.put(
						Pin.ZigBee.ASSOCIATE_DIO5,
						(IOSamplePayload[offset + 5] & 0x20) == 0x20 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x40) == 0x40)
				Digital.put(
						Pin.ZigBee.RTS_DIO6,
						(IOSamplePayload[offset + 5] & 0x40) == 0x40 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 2] & 0x80) == 0x80)
				Digital.put(
						Pin.ZigBee.CTS_DIO7,
						(IOSamplePayload[offset + 5] & 0x80) == 0x80 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));

			if ((IOSamplePayload[offset + 1] & 0x04) == 0x04)
				Digital.put(
						Pin.ZigBee.RSSI_PWM_DIO10,
						(IOSamplePayload[offset + 4] & 0x04) == 0x04 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 1] & 0x08) == 0x08)
				Digital.put(
						Pin.ZigBee.PWM_DIO11,
						(IOSamplePayload[offset + 4] & 0x08) == 0x08 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
			if ((IOSamplePayload[offset + 1] & 0x10) == 0x10)
				Digital.put(
						Pin.ZigBee.DIO12,
						(IOSamplePayload[offset + 4] & 0x10) == 0x10 ? new Integer(
								Pin.Status.HIGH) : new Integer(Pin.Status.LOW));
		}
		if (IOSamplePayload[offset + 3] != 0x00)// analog mask
		{
			if ((IOSamplePayload[offset + 3] & 0x01) == 0x01) {
				Analog.put(Pin.ZigBee.AD0_DIO0_COMMISSIONONG_BUTTON,
						new Integer(IOSamplePayload[offset + index] << 8
								| IOSamplePayload[offset + index + 1]));
				index += 2;
			}
			if ((IOSamplePayload[offset + 3] & 0x02) == 0x02) {
				Analog.put(Pin.ZigBee.AD1_DIO1, new Integer(
						IOSamplePayload[offset + index] << 8
								| IOSamplePayload[offset + index + 1]));
				index += 2;
			}
			if ((IOSamplePayload[offset + 3] & 0x04) == 0x04) {
				Analog.put(Pin.ZigBee.AD2_DIO2, new Integer(
						IOSamplePayload[offset + index] << 8
								| IOSamplePayload[offset + index + 1]));
				index += 2;
			}
			if ((IOSamplePayload[offset + 3] & 0x08) == 0x08) {
				Analog.put(Pin.ZigBee.AD3_DIO3, new Integer(
						IOSamplePayload[offset + index] << 8
								| IOSamplePayload[offset + index + 1]));
				index += 2;
			}
			if ((IOSamplePayload[offset + 3] & 0x80) == 0x80)
				SUPPLY_VOLTAGE = IOSamplePayload[offset + index] << 8
						| IOSamplePayload[offset + index + 1];
		}

		return new IOSamples(Analog, Digital, SUPPLY_VOLTAGE);
	}

	public abstract IOSamples GetIOSamples();

	public abstract int GetReceiveStatus();

	public abstract Address GetRemoteDevice();

	// / <summary>
	// / not apply to ZigBee
	// / </summary>
	// / <returns></returns>
	public int GetRSSI() {
		return 0;
	}
}