package uk.ac.herts.SmartLab.ihs;

import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class IhsClient {

	private final String SERVER_HOST = "speech.herts.ac.uk";
	private final String SERVER_ADDRESS = "http://speech.herts.ac.uk/ihs/ihs.asmx";
	private final String SOAP_12_Header;
	private final String SOAP_SET_HOUSE_RESPONSE_OK;
	private final byte[] SOAP_GET_HOUSE;

	private IhsHouse house;
	private String profileString;

	private DocumentBuilder xmlReader;

	public IhsClient(IhsHouse house, String profileString) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			xmlReader = factory.newDocumentBuilder();
		} catch (Exception e) {
		}

		this.profileString = profileString;
		this.house = house;

		SOAP_GET_HOUSE = ("<?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"><soap12:Body><GetHouse xmlns=\"http://speech.herts.ac.uk/ihs\"><houseID>"
				+ house.GetHouseID() + "</houseID></GetHouse></soap12:Body></soap12:Envelope>")
				.getBytes();
		SOAP_SET_HOUSE_RESPONSE_OK = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><SetHouseResponse xmlns=\"http://speech.herts.ac.uk/ihs\" /></soap:Body></soap:Envelope>";
		SOAP_12_Header = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
	}

	public String GenerateSetHouseSOAPRequest() {
		StringBuffer soap = new StringBuffer(SOAP_12_Header);
		soap.append("<soap12:Body><SetHouse xmlns=\"http://speech.herts.ac.uk/ihs\"><houseID>");
		soap.append(house.GetHouseID());
		soap.append("</houseID>");
		soap.append(house.toString());
		soap.append("</SetHouse></soap12:Body></soap12:Envelope>");
		return soap.toString();
	}

	public boolean SetHouse() {
		byte[] soap_body = GenerateSetHouseSOAPRequest().getBytes();

		HttpConnection conn = null;
		InputStream input = null;
		OutputStream output = null;
		int rc;

		try {
			conn = (HttpConnection) Connector.open(SERVER_ADDRESS
					+ profileString);
			conn.setRequestMethod(HttpConnection.POST);
			conn.setRequestProperty("Host", SERVER_HOST);
			conn.setRequestProperty("User-Agent", "Cinterion Concept Board");
			conn.setRequestProperty("Content-Type",
					"application/soap+xml; charset=utf-8");
			conn.setRequestProperty("Content-Length", soap_body.length + "");

			output = conn.openOutputStream();
			output.write(soap_body);

			rc = conn.getResponseCode();
			if (rc != HttpConnection.HTTP_OK)
				return false;

			input = conn.openInputStream();

			StringBuffer str = new StringBuffer();
			int ch;

			while ((ch = input.read()) != -1)
				str.append((char) ch);

			if (str.toString().equalsIgnoreCase(SOAP_SET_HOUSE_RESPONSE_OK))
				return true;
			else
				return false;

		} catch (Exception e) {
		} finally {
			try {
				if (output != null)
					output.close();
				if (input != null)
					input.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return false;
	}

	public boolean GetHouse() {
		HttpConnection conn = null;
		InputStream input = null;
		OutputStream output = null;
		int rc;

		try {
			conn = (HttpConnection) Connector.open(SERVER_ADDRESS
					+ profileString);
			conn.setRequestMethod(HttpConnection.POST);
			conn.setRequestProperty("Host", SERVER_HOST);
			conn.setRequestProperty("User-Agent", "Cinterion Concept Board");
			conn.setRequestProperty("Content-Type",
					"application/soap+xml; charset=utf-8");
			conn.setRequestProperty("Content-Length", SOAP_GET_HOUSE.length
					+ "");

			output = conn.openOutputStream();
			output.write(SOAP_GET_HOUSE);

			rc = conn.getResponseCode();
			if (rc != HttpConnection.HTTP_OK)
				return false;

			input = conn.openInputStream();

			Document dom = xmlReader.parse(new InputSource(input));

			XMLtoHouse(dom);

			return true;

		} catch (Exception e) {
		} finally {
			try {
				if (output != null)
					output.close();
				if (input != null)
					input.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}

		return false;
	}

	private void XMLtoHouse(Document dom) {
		NodeList list = dom.getElementsByTagName("DownStairs");
		if (list.getLength() > 0) {
			list = list.item(0).getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {

				String key = list.item(i).getNodeName();

				if (key.equalsIgnoreCase("DoorLock"))
					house.DownStairs.DoorLock = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("Fan"))
					house.DownStairs.Fan = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("Heater"))
					house.DownStairs.Heater = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("PIRSensor"))
					house.DownStairs.PIRSensor = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("Television"))
					house.DownStairs.Television = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("Temperature"))
					house.DownStairs.Temperature = Integer.parseInt(list
							.item(i).getTextContent());
				else if (key.equalsIgnoreCase("Lights")) {
					house.DownStairs.Lights[0] = Integer.parseInt(list.item(i)
							.getChildNodes().item(0).getTextContent());
					house.DownStairs.Lights[1] = Integer.parseInt(list.item(i)
							.getChildNodes().item(1).getTextContent());
				} else if (key.equalsIgnoreCase("LightSensors")) {
					house.DownStairs.LightSensors[0] = Integer.parseInt(list
							.item(i).getChildNodes().item(0).getTextContent());
					house.DownStairs.LightSensors[1] = Integer.parseInt(list
							.item(i).getChildNodes().item(1).getTextContent());
				} else if (key.equalsIgnoreCase("LightSwitches")) {
					house.DownStairs.LightSwitches[0] = Integer.parseInt(list
							.item(i).getChildNodes().item(0).getTextContent());
					house.DownStairs.LightSwitches[1] = Integer.parseInt(list
							.item(i).getChildNodes().item(1).getTextContent());
				}
			}
		}

		list = dom.getElementsByTagName("UpStairs");
		if (list.getLength() > 0) {
			list = list.item(0).getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {

				String key = list.item(i).getNodeName();

				if (key.equalsIgnoreCase("Fan"))
					house.DownStairs.Fan = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("Heater"))
					house.UpStairs.Heater = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("PIRSensor"))
					house.UpStairs.PIRSensor = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("Temperature"))
					house.UpStairs.Temperature = Integer.parseInt(list.item(i)
							.getTextContent());
				else if (key.equalsIgnoreCase("Lights")) {
					house.UpStairs.Lights[0] = Integer.parseInt(list.item(i)
							.getChildNodes().item(0).getTextContent());
					house.UpStairs.Lights[1] = Integer.parseInt(list.item(i)
							.getChildNodes().item(1).getTextContent());
				} else if (key.equalsIgnoreCase("LightSensors")) {
					house.UpStairs.LightSensors[0] = Integer.parseInt(list
							.item(i).getChildNodes().item(0).getTextContent());
					house.UpStairs.LightSensors[1] = Integer.parseInt(list
							.item(i).getChildNodes().item(1).getTextContent());
				} else if (key.equalsIgnoreCase("LightSwitches")) {
					house.UpStairs.LightSwitches[0] = Integer.parseInt(list
							.item(i).getChildNodes().item(0).getTextContent());
					house.UpStairs.LightSwitches[1] = Integer.parseInt(list
							.item(i).getChildNodes().item(1).getTextContent());
				}
			}
		}

		list = dom.getElementsByTagName("Outside");
		if (list.getLength() > 0) {
			list = list.item(0).getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {

				String key = list.item(i).getNodeName();

				if (key.equalsIgnoreCase("Buzzer"))
					house.Outside.Buzzer = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("FairyLights"))
					house.Outside.FairyLights = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("PIRSensor"))
					house.Outside.PIRSensor = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("LightSensor"))
					house.Outside.LightSensor = Integer.parseInt(list.item(i)
							.getTextContent());
				else if (key.equalsIgnoreCase("Lights")) {
					house.Outside.Lights[0] = Integer.parseInt(list.item(i)
							.getChildNodes().item(0).getTextContent());
					house.Outside.Lights[1] = Integer.parseInt(list.item(i)
							.getChildNodes().item(1).getTextContent());
				}
			}
		}

		list = dom.getElementsByTagName("Loft");
		if (list.getLength() > 0) {
			list = list.item(0).getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {

				String key = list.item(i).getNodeName();

				if (key.equalsIgnoreCase("Fan"))
					house.Loft.Fan = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("Heater"))
					house.Loft.Heater = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("PIRSensor"))
					house.Loft.PIRSensor = list.item(i).getTextContent();
				else if (key.equalsIgnoreCase("Temperature"))
					house.Loft.Temperature = Integer.parseInt(list.item(i)
							.getTextContent());
				else if (key.equalsIgnoreCase("Lights")) {
					house.Loft.Lights[0] = Integer.parseInt(list.item(i)
							.getChildNodes().item(0).getTextContent());
					house.Loft.Lights[1] = Integer.parseInt(list.item(i)
							.getChildNodes().item(1).getTextContent());
				} else if (key.equalsIgnoreCase("LightSensors")) {
					house.Loft.LightSensors[0] = Integer.parseInt(list.item(i)
							.getChildNodes().item(0).getTextContent());
					house.Loft.LightSensors[1] = Integer.parseInt(list.item(i)
							.getChildNodes().item(1).getTextContent());
				} else if (key.equalsIgnoreCase("LightSwitches")) {
					house.Loft.LightSwitches[0] = Integer.parseInt(list.item(i)
							.getChildNodes().item(0).getTextContent());
					house.Loft.LightSwitches[1] = Integer.parseInt(list.item(i)
							.getChildNodes().item(1).getTextContent());
				}
			}
		}

		list = dom.getElementsByTagName("Config");
		if (list.getLength() > 0) {
			list = list.item(0).getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {

				String key = list.item(i).getNodeName();
				String value = list.item(i).getTextContent();

				if (key.equalsIgnoreCase("ProgramID"))
					house.Config.ProgramID = value;
				else if (key.equalsIgnoreCase("MFUpdate"))
					house.Config.MFUpdate = value;
				else if (key.equalsIgnoreCase("BTUpdate"))
					house.Config.BTUpdate = value;
				else if (key.equalsIgnoreCase("WIFIUpdate"))
					house.Config.WIFIUpdate = value;
				else if (key.equalsIgnoreCase("SetTempLoft"))
					house.Config.SetTempLoft = value;
				else if (key.equalsIgnoreCase("SetTempUpStairs"))
					house.Config.SetTempUpStairs = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("SetTempDownStairs"))
					house.Config.SetTempDownStairs = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("SetLightLevelLoft1"))
					house.Config.SetLightLevelLoft1 = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("SetLightLevelLoft2"))
					house.Config.SetLightLevelLoft2 = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("SetLightLevelUpStairs1"))
					house.Config.SetLightLevelUpStairs1 = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("SetLightLevelUpStairs2"))
					house.Config.SetLightLevelUpStairs2 = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("SetLightLevelDownStairs1"))
					house.Config.SetLightLevelDownStairs1 = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("SetLightLevelDownStairs2"))
					house.Config.SetLightLevelDownStairs2 = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("SetLightLevelOutside"))
					house.Config.SetLightLevelOutside = list.item(i)
							.getTextContent();
				else if (key.equalsIgnoreCase("Energy1"))
					house.Config.Energy1 = value;
				else if (key.equalsIgnoreCase("Energy2"))
					house.Config.Energy2 = value;
				else if (key.equalsIgnoreCase("Energy3"))
					house.Config.Energy3 = value;
				else if (key.equalsIgnoreCase("Energy4"))
					house.Config.Energy4 = value;
				else if (key.equalsIgnoreCase("DebugG"))
					house.Config.DebugG = value;
				else if (key.equalsIgnoreCase("DebugH"))
					house.Config.DebugH = value;
				else if (key.equalsIgnoreCase("DebugW"))
					house.Config.DebugW = value;
			}
		}

		list = dom.getElementsByTagName("LastUpdated");
		if (list.getLength() > 0) {
			list = list.item(0).getChildNodes();
			house.LastUpdate = list.item(0).getTextContent();
		}

	}

}
