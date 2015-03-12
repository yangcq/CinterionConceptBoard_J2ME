package uk.ac.herts.SmartLab.ihs;

public class IhsHouse {

	private int houseID;

	public Loft Loft = new Loft();
	public UpStairs UpStairs = new UpStairs();
	public DownStairs DownStairs = new DownStairs();
	public Outside Outside = new Outside();
	public Config Config = new Config();

	public String LastUpdate = "1970-01-01T00:00:00Z";

	public IhsHouse(int HouseID) {
		this.houseID = HouseID;
	}

	public int GetHouseID() {
		return this.houseID;
	}

	public class Loft {
		public String Fan = "false", Heater = "false", PIRSensor = "false";
		public int[] Lights = new int[] { 0, 0 }, LightSensors = new int[] { 0,
				0 }, LightSwitches = new int[] { 0, 0 };
		public int Temperature = 0;
	}

	public class UpStairs {
		public String Fan = "false", Heater = "false", PIRSensor = "false";
		public int[] Lights = new int[] { 0, 0 }, LightSensors = new int[] { 0,
				0 }, LightSwitches = new int[] { 0, 0 };
		public int Temperature = 0;
	}

	public class DownStairs {
		public String DoorLock = "false", Fan = "false", Heater = "false",
				Television = "false", PIRSensor = "false";
		public int[] Lights = new int[] { 0, 0 }, LightSensors = new int[] { 0,
				0 }, LightSwitches = new int[] { 0, 0 };
		public int Temperature = 0;
	}

	public class Outside {
		public String Buzzer = "false", FairyLights = "false",
				PIRSensor = "false";
		public int[] Lights = new int[] { 0, 0 };
		public int LightSensor = 0;
	}

	public class Config {
		public String ProgramID = "0", SetTempLoft = "0",
				SetTempUpStairs = "0", SetTempDownStairs = "0",
				SetLightLevelLoft1 = "0", SetLightLevelLoft2 = "0",
				SetLightLevelUpStairs1 = "0", SetLightLevelUpStairs2 = "0",
				SetLightLevelDownStairs1 = "0", SetLightLevelDownStairs2 = "0",
				SetLightLevelOutside = "0";
		public String MFUpdate = "false", BTUpdate = "false",
				WIFIUpdate = "false";
		public String Energy1 = "0", Energy2 = "0", Energy3 = "0",
				Energy4 = "0";
		public String DebugG, DebugH, DebugW;
	}

	public String toString() {
		StringBuffer soap = new StringBuffer("<house><DownStairs><DoorLock>");
		soap.append(this.DownStairs.DoorLock);
		soap.append("</DoorLock>");
		soap.append("<Fan>");
		soap.append(this.DownStairs.Fan);
		soap.append("</Fan><Heater>");
		soap.append(this.DownStairs.Heater);
		soap.append("</Heater><Lights>");
		soap.append("<int>");
		soap.append(this.DownStairs.Lights[0]);
		soap.append("</int><int>");
		soap.append(this.DownStairs.Lights[1]);
		soap.append("</int></Lights><LightSensors><int>");
		soap.append(this.DownStairs.Lights[0]);
		soap.append("</int>");
		soap.append("<int>");
		soap.append(this.DownStairs.Lights[1]);
		soap.append("</int></LightSensors><LightSwitches><int>");
		soap.append(this.DownStairs.Lights[0]);
		soap.append("</int><int>");
		soap.append(this.DownStairs.Lights[1]);
		soap.append("</int>");
		soap.append("</LightSwitches><PIRSensor>");
		soap.append(this.DownStairs.PIRSensor);
		soap.append("</PIRSensor><Television>");
		soap.append(this.DownStairs.Television);
		soap.append("</Television>");
		soap.append("<Temperature>");
		soap.append(this.DownStairs.Temperature);
		soap.append("</Temperature></DownStairs><UpStairs><Fan>");
		soap.append(this.UpStairs.Fan);
		soap.append("</Fan><Heater>");
		soap.append(this.UpStairs.Heater);
		soap.append("</Heater>");
		soap.append("<Lights><int>");
		soap.append(this.UpStairs.Lights[0]);
		soap.append("</int><int>");
		soap.append(this.UpStairs.Lights[1]);
		soap.append("</int></Lights><LightSensors><int>");
		soap.append(this.UpStairs.Lights[0]);
		soap.append("</int>");
		soap.append("<int>");
		soap.append(this.UpStairs.Lights[1]);
		soap.append("</int></LightSensors><LightSwitches><int>");
		soap.append(this.UpStairs.Lights[0]);
		soap.append("</int><int>");
		soap.append(this.UpStairs.Lights[1]);
		soap.append("</int>");
		soap.append("</LightSwitches><PIRSensor>");
		soap.append(this.UpStairs.PIRSensor);
		soap.append("</PIRSensor><Temperature>");
		soap.append(this.UpStairs.Temperature);
		soap.append("</Temperature>");
		soap.append("</UpStairs><Outside><Buzzer>");
		soap.append(this.Outside.Buzzer);
		soap.append("</Buzzer><FairyLights>");
		soap.append(this.Outside.FairyLights);
		soap.append("</FairyLights><Lights>");
		soap.append("<int>");
		soap.append(this.Outside.Lights[0]);
		soap.append("</int><int>");
		soap.append(this.Outside.Lights[1]);
		soap.append("</int></Lights><LightSensor>");
		soap.append(this.Outside.LightSensor);
		soap.append("</LightSensor>");
		soap.append("<PIRSensor>");
		soap.append(this.Outside.PIRSensor);
		soap.append("</PIRSensor></Outside><Loft><Fan>");
		soap.append(this.Loft.Fan);
		soap.append("</Fan>");
		soap.append("<Heater>");
		soap.append(this.Loft.Heater);
		soap.append("</Heater><Lights><int>");
		soap.append(this.Loft.Lights[0]);
		soap.append("</int><int>");
		soap.append(this.Loft.Lights[1]);
		soap.append("</int>");
		soap.append("</Lights><LightSensors><int>");
		soap.append(this.Loft.Lights[0]);
		soap.append("</int><int>");
		soap.append(this.Loft.Lights[1]);
		soap.append("</int></LightSensors><LightSwitches><int>");
		soap.append(this.Loft.Lights[0]);
		soap.append("</int>");
		soap.append("<int>");
		soap.append(this.Loft.Lights[1]);
		soap.append("</int></LightSwitches><PIRSensor>");
		soap.append(this.Loft.PIRSensor);
		soap.append("</PIRSensor><Temperature>");
		soap.append(this.Loft.Temperature);
		soap.append("</Temperature>");
		soap.append("</Loft><Config><ProgramID>");
		soap.append(this.Config.ProgramID);
		soap.append("</ProgramID><MFUpdate>");
		soap.append(this.Config.MFUpdate);
		soap.append("</MFUpdate>");
		soap.append("<BTUpdate>");
		soap.append(this.Config.BTUpdate);
		soap.append("</BTUpdate><WIFIUpdate>");
		soap.append(this.Config.WIFIUpdate);
		soap.append("</WIFIUpdate>");
		soap.append("<SetTempLoft>");
		soap.append(this.Config.SetTempLoft);
		soap.append("</SetTempLoft><SetTempUpStairs>");
		soap.append(this.Config.SetTempUpStairs);
		soap.append("</SetTempUpStairs><SetTempDownStairs>");
		soap.append(this.Config.SetTempDownStairs);
		soap.append("</SetTempDownStairs>");
		soap.append("<SetLightLevelLoft1>");
		soap.append(this.Config.SetLightLevelLoft1);
		soap.append("</SetLightLevelLoft1><SetLightLevelLoft2>");
		soap.append(this.Config.SetLightLevelLoft2);
		soap.append("</SetLightLevelLoft2>");
		soap.append("<SetLightLevelUpStairs1>");
		soap.append(this.Config.SetLightLevelUpStairs1);
		soap.append("</SetLightLevelUpStairs1><SetLightLevelUpStairs2>");
		soap.append(this.Config.SetLightLevelUpStairs2);
		soap.append("</SetLightLevelUpStairs2>");
		soap.append("<SetLightLevelDownStairs1>");
		soap.append(this.Config.SetLightLevelDownStairs1);
		soap.append("</SetLightLevelDownStairs1><SetLightLevelDownStairs2>");
		soap.append(this.Config.SetLightLevelDownStairs2);
		soap.append("</SetLightLevelDownStairs2>");
		soap.append("<SetLightLevelOutside>");
		soap.append(this.Config.SetLightLevelOutside);
		soap.append("</SetLightLevelOutside><Energy1>");
		soap.append(this.Config.Energy1);
		soap.append("</Energy1>");
		soap.append("<Energy2>");
		soap.append(this.Config.Energy2);
		soap.append("</Energy2><Energy3>");
		soap.append(this.Config.Energy3);
		soap.append("</Energy3><Energy4>");
		soap.append(this.Config.Energy4);
		soap.append("</Energy4>");
		soap.append("<DebugG>");
		soap.append(this.Config.DebugG);
		soap.append("</DebugG><DebugH>");
		soap.append(this.Config.DebugH);
		soap.append("</DebugH><DebugW>");
		soap.append(this.Config.DebugW);
		soap.append("</DebugW>");
		soap.append("</Config><LastUpdated>");
		soap.append(this.LastUpdate);
		soap.append("</LastUpdated></house>");
		return soap.toString();
	}
}
