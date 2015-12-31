/*******************************************************************************
 * Copyright (c) 2014 University of Luxembourg.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Alfredo Capozucca - initial API and implementation
 ******************************************************************************/
package lu.uni.lassy.excalibur.examples.icrash.dev.client.java.gui;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.IcrashEnvironment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.SafeIcrashEnvironment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActActivator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActMsrCreator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.SafeIcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAuthenticated;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDateAndTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.ICrashUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;

//public class MainPanelController 
public class MainPanelController implements Initializable {
	
	
	static Logger log = Log4JUtils.getInstance().getLogger();
	
	//implementations of the "safe" system - the two instances of the server, by identifying them
	//through the name&port given in the config. file
	SafeIcrashEnvironment env;
	SafeIcrashSystem sys;
	
	private ShowMessage logger;
	
	
    //FXML elements
    
    @FXML
    private TableView<CtAdministrator> tableCtAdmins;

	@FXML
    private TableView<CtCoordinator> tableCtCoordinators;

	@FXML
    private TableView<ActComCompany> tableActComCompanies;

	@FXML
    private TableColumn<CtAdministrator, String> colAdminName;

	@FXML
    private TableColumn<CtCoordinator, String> colCoordId;
    
    @FXML
    private TableColumn<CtCoordinator, String> colCoordName;
    
    @FXML
    private TableColumn<ActComCompany, String> colComCompanyName;
       
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

	@FXML
    private Label clockValue; 

    @FXML 
    private Tab tabCtState; 
    
    
    @FXML
    private ChoiceBox<Integer> activatorDateY;
    
    
    @FXML
    private ChoiceBox<Integer> activatorDateM;
    
    @FXML
    private ChoiceBox<Integer> activatorDateD;
    
        
    @FXML
    private ChoiceBox<Integer> activatorTimeH;
    
    @FXML
    private ChoiceBox<Integer> activatorTimeMin;
    
    @FXML
    private ChoiceBox<Integer> activatorTimeSecs;


	@FXML
    private Button btOeSollicitateCrisisHandling;

    @FXML
    private Button btOeSetClock;

    @FXML
    private Label vpLastReminderValue;

	@FXML
    private TextField adminLoginName;

        
    @FXML
    private PasswordField adminLoginPwd;
    

	@FXML
    private TextField coordLoginName;

        
    @FXML
    private PasswordField coordLoginPwd;
    
    
    
	//final ObservableList<CtAdministrator> tableCtAdminContent = FXCollections.observableArrayList();

	/**
     * Initializes the controller class.
     */
	@Override
	public void initialize(URL url, ResourceBundle rsrcs) {
		assert clockValue != null : "fx:id=\"clockValue\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert tabCtState != null : "fx:id=\"tabCtState\" was not injected: check your FXML file 'MainPanel.fxml'.";

		assert activatorDateY != null : "fx:id=\"activatorDateY\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert activatorDateM != null : "fx:id=\"activatorDateM\" was not injected: check your FXML file 'MainPanel.fxml'.";
		assert activatorDateD != null : "fx:id=\"activatorDateD\" was not injected: check your FXML file 'MainPanel.fxml'.";

        assert activatorTimeH != null : "fx:id=\"activatorTimeH\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert activatorTimeMin != null : "fx:id=\"activatorTimeMin\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert activatorTimeSecs != null : "fx:id=\"activatorTimeSecs\" was not injected: check your FXML file 'MainPanel.fxml'.";

        assert btOeSetClock != null : "fx:id=\"btOeSetClock\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert btOeSollicitateCrisisHandling != null : "fx:id=\"btOeSollicitateCrisisHandling\" was not injected: check your FXML file 'MainPanel.fxml'.";

        assert vpLastReminderValue != null : "fx:id=\"vpLastReminderValue\" was not injected: check your FXML file 'MainPanel.fxml'.";
		
		//table elements        
        assert tableCtAdmins != null : "fx:id=\"tableCtAdmins\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert colAdminName != null : "fx:id=\"colAdminName\" was not injected: check your FXML file 'MainPanel.fxml'.";

		assert tableCtCoordinators != null : "fx:id=\"tableCtCoordinators\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert colCoordId != null : "fx:id=\"colCoordId\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert colCoordName != null : "fx:id=\"colCoordName\" was not injected: check your FXML file 'MainPanel.fxml'.";
        
        
        assert tableActComCompanies != null : "fx:id=\"tableActComCompanies\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert colComCompanyName != null : "fx:id=\"colComCompanyName\" was not injected: check your FXML file 'MainPanel.fxml'.";
        
        //Admin tab
        assert adminLoginName != null : "fx:id=\"adminLoginName\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert adminLoginPwd != null : "fx:id=\"adminLoginPwd\" was not injected: check your FXML file 'MainPanel.fxml'.";
        
        //Coordinator tab
        assert coordLoginName != null : "fx:id=\"coordLoginName\" was not injected: check your FXML file 'MainPanel.fxml'.";
        assert coordLoginPwd != null : "fx:id=\"coordLoginPwd\" was not injected: check your FXML file 'MainPanel.fxml'.";
        
        log.info(this.getClass().getSimpleName() + ".initialize");
        
        try{
	        //Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			log.info("get registry");
			 	
			//Gathering the remote object as it was published into the registry
			logger = new ShowMessage(this);
			env = new SafeIcrashEnvironment(logger);
			sys = new SafeIcrashSystem(logger);
			
	        //env = (IcrashEnvironment)registry.lookup("iCrashEnvironment");
	        //sys = (IcrashSystem)registry.lookup("iCrashServer");
	
			//Step 1
			log.info("---- Create System And Environment-------");
			ActMsrCreator theCreator = new ActMsrCreator();
			theCreator.oeCreateSystemAndEnvironment(new PtInteger(4));
			
			configureTabTables();
			
			updateCtState();
			
			setUpDateChoiceBoxes();
			
			fixDateChoiceBoxes();
			
			setUpTimeChoiceBoxes();
			
			fixTimeChoiceBoxes();
			
			initTables();

        }catch(Exception ex){
        	log.error("Exception in MainPanelControl ..." + ex);
        }
		
	}
    


	/**
     * Called when the btOeSetClock button is fired.
     *
     * @param event the action event.
	 * @throws NotBoundException 
	 * @throws RemoteException 
     */
    public void oeSetClockFired(ActionEvent event) throws RemoteException, NotBoundException {
    
    	
    
    	ActActivator theClock = env.getActActivator();
    	
    	int d,m,y;
    	int h,min,sec;
    	
    	y = activatorDateY.getSelectionModel().getSelectedItem();
    	m = activatorDateM.getSelectionModel().getSelectedItem();
    	d = activatorDateD.getSelectionModel().getSelectedItem();
		
		h = activatorTimeH.getSelectionModel().getSelectedItem();
		min = activatorTimeMin.getSelectionModel().getSelectedItem();
		sec = activatorTimeSecs.getSelectionModel().getSelectedItem();

		log.debug("YEAR: "+y);
		log.debug("Month: "+m);
		log.debug("Day: "+d);
		log.debug("Hour: "+h);
		log.debug("Min: "+min);
		log.debug("Secs: "+sec);
		
		theClock.oeSetClock(ICrashUtils.setDateAndTime(y, m, d, h, min, sec));
    
    	
    	updateCtState();
    
    }







    
    public void updateCtState() throws RemoteException {
    	
     	log.debug("in updateCtState");
     	int d,m,y,h,min,sec;
     	
     	DtDateAndTime currentTime = sys.getCtState().clock;
     	
     	d = currentTime.date.day.value.getValue();
     	m = currentTime.date.month.value.getValue();
     	y = currentTime.date.year.value.getValue();
     	
     	h = currentTime.time.hour.value.getValue();
     	min = currentTime.time.minute.value.getValue();
     	sec = currentTime.time.second.value.getValue();
     	
     	
     	if (clockValue != null ) {
     		clockValue.setText(y+":"+m+":"+d+"-"+h+":"+min+":"+sec);
     	}
     	
    }

    
    public void fixDateChoiceBoxes(){
    	Calendar cal = Calendar.getInstance();
		
		if(activatorDateY.getItems().contains(cal.get(Calendar.YEAR)))
			activatorDateY.getSelectionModel().select(new Integer(cal.get(Calendar.YEAR)));
		else
			activatorDateY.getSelectionModel().selectLast();
			
			
		if(activatorDateM.getItems().contains(cal.get(Calendar.MONTH)+1))
			activatorDateM.getSelectionModel().select(new Integer(cal.get(Calendar.MONTH)+1));
		else
			activatorDateM.getSelectionModel().selectLast();
			
			
		if(activatorDateD.getItems().contains(cal.get(Calendar.DAY_OF_MONTH)))
			activatorDateD.getSelectionModel().select(new Integer(cal.get(Calendar.DAY_OF_MONTH)));
		else
			activatorDateD.getSelectionModel().selectLast();
    }
    
    
    
     public void fixTimeChoiceBoxes(){
    	Calendar cal = Calendar.getInstance();
		
		if(activatorTimeH.getItems().contains(cal.get(Calendar.HOUR_OF_DAY)))
			activatorTimeH.getSelectionModel().select(new Integer(cal.get(Calendar.HOUR_OF_DAY)));
		else
			activatorTimeH.getSelectionModel().selectLast();
			
			
		if(activatorTimeMin.getItems().contains(cal.get(Calendar.MINUTE)))
			activatorTimeMin.getSelectionModel().select(new Integer(cal.get(Calendar.MINUTE)));
		else
			activatorTimeMin.getSelectionModel().selectLast();
			
			
		if(activatorTimeSecs.getItems().contains(cal.get(Calendar.SECOND)))
			activatorTimeSecs.getSelectionModel().select(new Integer(cal.get(Calendar.SECOND)));
		else
			activatorTimeSecs.getSelectionModel().selectLast();
    }
    
    
    public void setUpDateChoiceBoxes(){
        
    	for(int i=2000;i<2031;i++)
			activatorDateY.getItems().add(i);    
			
		for(int i=1;i<13;i++)
			activatorDateM.getItems().add(i);
    
    	for(int i=1;i<32;i++)
			activatorDateD.getItems().add(i);
	
    }
    
    
    public void setUpTimeChoiceBoxes(){
    
    	for(int i=0;i<24;i++)
			activatorTimeH.getItems().add(i);    
			
		for(int i=0;i<60;i++){
			activatorTimeMin.getItems().add(i);
			activatorTimeSecs.getItems().add(i);
		}
    
    }
    
    

	public void initTables() throws RemoteException {
     	
     	Hashtable<String, CtAuthenticated> ctAuth = sys.getCmpSystemCtAuthenticated(); 
    
		if(ctAuth!= null){		     	
     		for(String key:ctAuth.keySet()){
     		
     			CtAuthenticated user = ctAuth.get(key);
     			
     			if(user instanceof CtAdministrator){
     				tableCtAdmins.getItems().add((CtAdministrator)user);
	     			//tableCtAdminContent.add(ctAdmins.get(key));
     			}
     			
     			if(user instanceof CtCoordinator)
     				tableCtCoordinators.getItems().add((CtCoordinator)user);
     		}
		}

		//tableCtAdmins.setItems(tableCtAdminContent);
		
		Hashtable<String,ActComCompany> actComCompanies = env.getActComCompanies();
		if(actComCompanies!= null){		     	
     		for(String key:actComCompanies.keySet())
     			tableActComCompanies.getItems().add(actComCompanies.get(key));
		}
	

     	
    }




	 // Configure the table widget: set up its column, and register the
    // selection changed listener.
    private void configureTabTables() {
    
    
    	 colAdminName.setCellValueFactory(new Callback<CellDataFeatures<CtAdministrator, String>, ObservableValue<String>>() {
 			    public ObservableValue<String> call(CellDataFeatures<CtAdministrator, String> admin) {
        		 return new ReadOnlyObjectWrapper(admin.getValue().login.value.getValue());
		     }
		  });
    
    	colComCompanyName.setCellValueFactory(new Callback<CellDataFeatures<ActComCompany, String>, ObservableValue<String>>() {
 			    public ObservableValue<String> call(CellDataFeatures<ActComCompany, String> comcompany) {
        		 return new ReadOnlyObjectWrapper(comcompany.getValue().name);
		     }
		  });
    
    
    	colCoordId.setCellValueFactory(new Callback<CellDataFeatures<CtCoordinator, String>, ObservableValue<String>>() {
 			    public ObservableValue<String> call(CellDataFeatures<CtCoordinator, String> coord) {
        		 return new ReadOnlyObjectWrapper(coord.getValue().id.value.getValue());
		     }
		  });
    
    	colCoordName.setCellValueFactory(new Callback<CellDataFeatures<CtCoordinator, String>, ObservableValue<String>>() {
 			    public ObservableValue<String> call(CellDataFeatures<CtCoordinator, String> coord) {
        		 return new ReadOnlyObjectWrapper(coord.getValue().login.value.getValue());
		     }
		  });
    
    
    
        //tableCtAdmins.setItems(tableCtAdminContent);
        //assert tableCtAdmins.getItems() == tableCtAdminContent;

        //final ObservableList<CtAdministrator> tableSelection = tableCtAdmins.getSelectionModel().getSelectedItems();

        //tableSelection.addListener(tableSelectionChanged);
        
    }
    

	// This listener listen to changes in the table widget selection and
    // update the DeleteIssue button state accordingly.
    /*
    private final ListChangeListener<CtAdministrator> tableSelectionChanged =
            new ListChangeListener<CtAdministrator>() {
				
                @Override
                public void onChanged(Change<? extends CtAdministrator> c) {
                    //updateDeleteIssueButtonState();
                    tableCtAdmins.getSelectionModel().getSelectedItems();
                    log.debug("in listener");
                    //updateSaveIssueButtonState();
                }
            };
    */
    
    
    @FXML
    void btAdminLogin(ActionEvent event) {
         log.debug("in btAdminLogin");


    }
    
    
     @FXML
    void btAdminLogout(ActionEvent event) {
         log.debug("in btAdminLogout");


    }
    
    
     @FXML
    void btAdminAddCoord(ActionEvent event) {
         log.debug("in btAdminAddCoord");

    }


	@FXML
    void btAdminDelCoord(ActionEvent event) {
         log.debug("in btAdminDelCoord");

    }
    
    
    @FXML
    void btCoordLogin(ActionEvent event) {
         log.debug("in btCoordLogin");

    }
    
    @FXML
    void btCoordLogout(ActionEvent event) {
         log.debug("in btCoordLogout");

    }
    //dialog box displaying the message in case the main server is down
    void showMSG(String str)
    {
    	
    	final Stage dialogStage = new Stage();
    	Button closeDialog = new Button("OK");
        closeDialog.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	dialogStage.close();
            }
        });
    	dialogStage.initModality(Modality.WINDOW_MODAL);
    	dialogStage.setScene(new Scene(VBoxBuilder.create().
    	    children(new Text("Main server crashed, sending to backup..."), closeDialog).
    	    alignment(Pos.CENTER).padding(new Insets(5)).build()));
    	dialogStage.show();
    }
}
