package uk.ac.herts.SmartLab.XBee;


import java.util.Hashtable;

import uk.ac.herts.SmartLab.XBee.Device.Pin;

public class IOSamples {
	private int SUPPLY_VOLTAGE;
	private Hashtable analog;
	private Hashtable digital;

	// / <summary>
	// / SUPPLY_VOLTAGE not required by XBee
	// / </summary>
	// / <param name="analog"></param>
	// / <param name="digital"></param>
	// / <param name="SUPPLY_VOLTAGE"></param>
	public IOSamples(Hashtable analog,
			Hashtable digital, int SUPPLY_VOLTAGE) {
		this.analog = analog;
		this.digital = digital;
		this.SUPPLY_VOLTAGE = SUPPLY_VOLTAGE;
	}

	public Hashtable GetAnalogs() {
		return this.analog;
	}

	public Integer GetAnalog(Pin pin) {
		if (this.analog.containsKey(pin))
			return (Integer) analog.get(pin);
		return null;
	}

	public Integer GetDigital(Pin pin) {
		if (this.digital.containsKey(pin))
			return (Integer) digital.get(pin);
		return null;
	}

	public Hashtable GetDigitals() {
		return this.digital;
	}

	// / <summary>
	// / only avaliable in ZigBee device and when it is battery powered
	// / </summary>
	// / <returns></returns>
	public int GetSupplyVoltage() {
		return this.SUPPLY_VOLTAGE;
	}
}