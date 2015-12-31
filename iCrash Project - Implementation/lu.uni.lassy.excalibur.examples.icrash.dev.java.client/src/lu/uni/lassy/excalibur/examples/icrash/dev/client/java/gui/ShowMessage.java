package lu.uni.lassy.excalibur.examples.icrash.dev.client.java.gui;
//show message to the user in the GUI
public class ShowMessage {
	
	private MainPanelController mainGUI;
	
	public boolean workingMode;
	
	public ShowMessage(MainPanelController mgui)
	{
		workingMode = true;
		mainGUI = mgui;
		if(mgui == null)
			workingMode = false;
	}
	
	public void logMessageToTheUser(String str)
	{
		if(workingMode)
			mainGUI.showMSG(str);
		else
		{
			System.out.println(str);
		}
	}

}
