package uk.ac.herts.SmartLab.gemalto;

public interface EHS6ResponseListener {

	public void onMessageReceived(String originatingAddress,
			String serviceCentreTimeStamp, String body);
	
	/*
	 * @param value : 0,1,2,3,4,5
	 */
	public void onSignalStrengthIndication(int value);
	
	public void onEHS6Ready();
}
