/*******************************************************************************
 * Copyright (c) 2014-2015 University of Luxembourg.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Alfredo Capozucca - initial API and implementation
 ******************************************************************************/
package lu.uni.lassy.excalibur.examples.icrash.dev.java.system;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.IcrashEnvironment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActActivator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAuthenticated;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbAlerts;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbComCompanies;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbCrises;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbHumans;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAlert;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAuthenticated;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCrisis;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtHuman;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtState;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtAlertID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtComment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCrisisID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtGPSLocation;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtAlertStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisType;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtHumanKind;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.secondary.DtSMS;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDate;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDateAndTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtSecond;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.AdminActors;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.ComCompaniesInLux;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.ICrashUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;

public class IcrashSystemImpl extends UnicastRemoteObject implements IcrashSystem {

	private static final long serialVersionUID = 1L;

	CtState ctState = new CtState();
	
	ActAuthenticated currentRequestingAuthenticatedActor;
	ActComCompany currentConnectedComCompany;

	// Messir compositions
	//Key is the admin's login's name
	Hashtable<String, CtAdministrator> cmpSystemCtAdministrator = new Hashtable<String, CtAdministrator>();
	//Key is the login name
	Hashtable<String, CtAuthenticated> cmpSystemCtAuthenticated = new Hashtable<String, CtAuthenticated>();
	//Key is the AlertID name
	Hashtable<String, CtAlert> cmpSystemCtAlert;
	//Key is the CrisisID name
	Hashtable<String, CtCrisis> cmpSystemCtCrisis = new Hashtable<String, CtCrisis>();
	//Key is the Human's phone number
	Hashtable<String, CtHuman> cmpSystemCtHuman = new Hashtable<String, CtHuman>();
	
	
	// Messir associations	
	Hashtable<CtAlert, CtCrisis> assCtAlertCtCrisis = new Hashtable<CtAlert, CtCrisis>();
	Hashtable<CtAlert, CtHuman> assCtAlertCtHuman = new Hashtable<CtAlert, CtHuman>();
	Hashtable<CtAuthenticated, ActAuthenticated> assCtAuthenticatedActAuthenticated = new Hashtable<CtAuthenticated, ActAuthenticated>();
	Hashtable<CtCoordinator, ActCoordinator> assCtCoordinatorActCoordinator = new Hashtable<CtCoordinator,ActCoordinator>();
	Hashtable<CtCrisis, CtCoordinator> assCtCrisisCtCoordinator = new Hashtable<CtCrisis,CtCoordinator>();
	Hashtable<CtHuman, ActComCompany> assCtHumanActComCompany = new Hashtable<CtHuman,ActComCompany>();


	/**********************************
	 * Internal operations 
	***********************************/

	private List<CtAlert> getAlertsByCrisis(CtCrisis aCtCrisis){
	
		List<CtAlert> listAlerts = new ArrayList<CtAlert>();
	
		for(CtAlert ctAlert:assCtAlertCtCrisis.keySet()){
			if(assCtAlertCtCrisis.get(ctAlert).id.value.getValue().equals(aCtCrisis.id.value.getValue()))
				listAlerts.add(ctAlert);
		}
	
		return listAlerts;
	}



	private CtAuthenticated getCtAuthenticated(DtCoordinatorID aDtCoordinatorID){
	
		for(CtAuthenticated ctAuth:assCtAuthenticatedActAuthenticated.keySet()){
			if(ctAuth instanceof CtCoordinator){
				PtBoolean res = ((CtCoordinator)ctAuth).id.eq(aDtCoordinatorID);
				if(res.getValue())
					return  ctAuth;
			}
		}
	
		return null;
		
	}


	private CtAuthenticated getCtAuthenticated(ActAuthenticated aActAuthenticated){
		
			//get aActAuthenticated's name
			String ActAuthname = "";
			if(aActAuthenticated instanceof ActAdministrator){
				ActAuthname = ((ActAdministrator)aActAuthenticated).getName();
			}else{
				ActAuthname = ((ActCoordinator)aActAuthenticated).getName();
			}
		
			String currName = "";
			for(CtAuthenticated ctAuth:assCtAuthenticatedActAuthenticated.keySet()){
				
				ActAuthenticated currActAuth = assCtAuthenticatedActAuthenticated.get(ctAuth);
				if(currActAuth instanceof ActAdministrator){
					currName = ((ActAdministrator)currActAuth).getName();
				}else{
					currName = ((ActCoordinator)currActAuth).getName();
				}
				
				if(currName.equals(ActAuthname))
						return ctAuth;
				
			}
	
		return null;
		
	}


	/**********************************
	 * New implementation operations 
	***********************************/
	
	
	public void doTest() throws java.rmi.RemoteException{
		System.out.println("I'm alive and reacheable boy!");
	}
 
	//Eager singleton pattern
	private static volatile IcrashSystem instance = null; 
 
    // constructor
    public IcrashSystemImpl() throws RemoteException {
     	super();
    }
    
 
 
 	public static IcrashSystem getInstance() throws RemoteException {
        if(instance == null)
        	instance = new IcrashSystemImpl();
        	
        return instance;
    }
    

	public void setCurrentRequestingAuthenticatedActor(ActAuthenticated aActAuthenticated) throws RemoteException {
    	currentRequestingAuthenticatedActor = aActAuthenticated;
    }


	public void setCurrentConnectedComCompany(ActComCompany aComCompany) throws RemoteException {
    	currentConnectedComCompany = aComCompany;
    }


	public CtState getCtState() throws RemoteException {
    	return ctState;
    }


	public CtCoordinator getRandomCtCoordinator() throws RemoteException {
	
		int max = assCtCoordinatorActCoordinator.size();
		int min = 0;
		int randomNum = 0;
		
		if(max > 1){
			Random rand = new Random();
		    // nextInt is exclusive of max, and inclusive of min,
		    randomNum = rand.nextInt(max - min) + min;
		}
		
		Collection<CtCoordinator> collCtCoor = assCtCoordinatorActCoordinator.keySet();
		CtCoordinator[] obj = collCtCoor.toArray(new CtCoordinator[0]);
		return obj[randomNum];
    	
    }
    
    

	public ActCoordinator getActCoordinator(CtCoordinator keyCtCoordinator) throws RemoteException {
		return assCtCoordinatorActCoordinator.get(keyCtCoordinator);
	}

	public void bindCtCrisisCtCoordinator(CtCrisis aCtCrisis,CtCoordinator aCtCoordinator) throws RemoteException{ 
		assCtCrisisCtCoordinator.put(aCtCrisis, aCtCoordinator);
		DbCrises.bindCrisisCoordinator(aCtCrisis, aCtCoordinator);
	}
    
    public List<ActAdministrator> getAllActAdministrators() throws RemoteException {
    	
    	List<ActAdministrator> listAdmins = new ArrayList<ActAdministrator>();
    	
    	for(CtAuthenticated ctAuth:assCtAuthenticatedActAuthenticated.keySet()){
    		if(ctAuth instanceof CtAdministrator)
    			listAdmins.add((ActAdministrator)assCtAuthenticatedActAuthenticated.get(ctAuth));
    	}
    	
    	return listAdmins;
    }


	public ActComCompany getActComCompany(CtHuman aHuman) throws RemoteException {
		return assCtHumanActComCompany.get(aHuman);
	}

	
	public Hashtable<String, CtAuthenticated> getCmpSystemCtAuthenticated() throws RemoteException {
		return cmpSystemCtAuthenticated;
	}
	
	


	/**************************
	 * System operations 
	 **************************/
	 
	//actMsrCreator Actor
	public PtBoolean oeCreateSystemAndEnvironment(PtInteger aQtyComCompanies) throws RemoteException {
	
		Logger log = Log4JUtils.getInstance().getLogger();
		try{
		
			log.debug("in IcrashSystemimpl.oeCreateSystemAndEnvironment..." );
			
			 
	
			/*
			 * 	PostF 1:
			 * 
			 *  the ctState instance is initialised with
			 *  the integer 1 for the crisis and alert counters used for their	identifications,
			 *  clock = current time 
			 *  the crisis reminder period is set to 300 seconds, 
			 *  the maximum crisis reminder period	is fixed to 1200 seconds (i.e. 20 minutes) and 
			 *  the system is considered in a started state.
			 *  aVpLastReminder = current time
			 *  Those predicates must be satisfied first since all the other depend on the existence of a
			 *  ctState instance !
				
			*/
			
			
			//OLD->DtInteger aNextValueForAlertID = new DtInteger(new PtInteger(1));
			int nextValueForAlertID = DbAlerts.getMaxAlertID() + 1;
			DtInteger aNextValueForAlertID = new DtInteger(new PtInteger(nextValueForAlertID));
			//OLd->DtInteger aNextValueForCrisisID = new DtInteger(new PtInteger(1));
			int nextValueForCrisisID = DbCrises.getMaxCrisisID() + 1;
			DtInteger aNextValueForCrisisID = new DtInteger(new PtInteger(nextValueForCrisisID));
			
			
			DtDateAndTime aClock = ICrashUtils.getCurrentDateAndTime();
			
			DtSecond aCrisisReminderPeriod = new DtSecond(new PtInteger(300));
			DtSecond aMaxCrisisReminderPeriod = new DtSecond(new PtInteger(1200));
			PtBoolean aVpStarted = new PtBoolean(true);
			
			ctState.init(aNextValueForAlertID, aNextValueForCrisisID, aClock, aCrisisReminderPeriod, aMaxCrisisReminderPeriod, aClock, aVpStarted);
			
		
			/* ENV
			PostF 2 the actMsrCreator actor instance is initiated (remember that since the
			oeCreateSystemAndEnvironment is a special event, its role is to make consistent the post
			state, thus creating the actor and its interfaces is required even though the sending 
			of this message	logically would need the actor and its interfaces to already exist ...).
			*/
			//implementation done at the level of the init method caller 		
			
			/*	ENV
			PostF 3 the environment for communication company actors, in the post state, is made of
			AqtyComCompanies instances allowing for receiving and sending messages to humans.
			*/
			Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
        	IcrashEnvironment env = (IcrashEnvironment)registry.lookup("iCrashEnvironment");
		
		
			for(int i= 0;i<aQtyComCompanies.getValue();i++){
				String aActComCompanyName = ComCompaniesInLux.values[i].name();
				DbComCompanies.insertComCompany(i+"", aActComCompanyName);
				ActComCompany aActComCompany = new ActComCompany(aActComCompanyName);
				env.setComCompany(aActComCompanyName,aActComCompany);
			}
			
			/*	ENV
			PostF 4 the environment for administrator actors, in the post state, is made of one instance.
			*/
			String adminName = AdminActors.values[0].name();
			ActAdministrator aActAdministrator = new ActAdministrator(adminName);
			env.setActAdministrator(adminName,aActAdministrator);
			
			/* ENV
			PostF 5 the environment for activator actors, in the post state, is made of one instance allowing for automatic
			message sending based on current system’s and environment state’.
			*/
			ActActivator aActActivator = new ActActivator();
			env.setActActivator(aActActivator);
	
		
			/*
			PostF 6 the set of ctAdministrator instances at post is made of one instance initialized with 
			’icrashadmin’ (resp. ’7WXC1359’) for login (resp. password) values.
			*/
			CtAdministrator ctAdmin = new CtAdministrator();
			DtLogin aLogin = new DtLogin(new PtString("icrashadmin"));
			DtPassword aPwd = new DtPassword(new PtString("7WXC1359"));
			ctAdmin.init(aLogin, aPwd);
			
			
			/*
			PostF 7 the association between ctAdministrator and actAdministrator is made of 
			one couple made of the jointly specified instances.
			*/
			assCtAuthenticatedActAuthenticated.put(ctAdmin, aActAdministrator);
		
			//set up Messir compositions		
			cmpSystemCtAdministrator.put(ctAdmin.login.value.toString(), ctAdmin);
			cmpSystemCtAuthenticated.put(ctAdmin.login.value.getValue(), ctAdmin);
	
	
	
			// initialise relationships taking information from the DB
			cmpSystemCtAlert = DbAlerts.getSystemAlerts();
			cmpSystemCtCrisis = DbCrises.getSystemCrises();
			cmpSystemCtHuman = DbHumans.getSystemHumans();

			assCtAlertCtCrisis = DbAlerts.getAssCtAlertCtCrisis();	
			assCtAlertCtHuman = DbAlerts.getAssCtAlertCtHuman();
			assCtCrisisCtCoordinator = DbCrises.getAssCtCrisisCtCoordinator();
			assCtHumanActComCompany = DbHumans.getAssCtHumanActComCompany();
	
	
			
		}catch(Exception ex){
			log.error("Exception when trying to reach Environment..." + ex);	
		}
	
	
		return new PtBoolean(true); 
	}


	//actComCompany	 Actor
	public PtBoolean oeAlert(EtHumanKind aEtHumanKind,DtDate aDtDate,
				DtTime aDtTime,DtPhoneNumber aDtPhoneNumber,DtGPSLocation aDtGPSLocation,DtComment aDtComment)
				throws RemoteException {
		
		int nextValueForAlertID_at_pre = ctState.nextValueForAlertID.value.getValue();
		int nextValueForCrisisID_at_pre = ctState.nextValueForCrisisID.value.getValue();

		//PostF1				
		ctState.nextValueForAlertID.value = new PtInteger(ctState.nextValueForAlertID.value.getValue() + 1);
		
		//PostF2
		CtAlert aCtAlert = new CtAlert();
		DtAlertID aId = new DtAlertID(new PtString(""+nextValueForAlertID_at_pre));
		EtAlertStatus aStatus = EtAlertStatus.pending;
		DtDateAndTime aInstant = new DtDateAndTime(aDtDate,aDtTime);  
		aCtAlert.init(aId, aStatus,aDtGPSLocation,aInstant, aDtComment);
		//DB: insert alert in the database
		DbAlerts.insertAlert(aCtAlert);
		
		//PostF3
		boolean existsNear = false;
		CtCrisis aCtCrisis = new CtCrisis();
		//check if there already exists a reported Alert that is closer than 100 m. 
		for(CtAlert existingCtAlert:assCtAlertCtCrisis.keySet()){
			existsNear = existingCtAlert.location.isNearTo(aDtGPSLocation.latitude, aDtGPSLocation.longitude).getValue();
			if(existsNear){
				aCtCrisis = assCtAlertCtCrisis.get(existingCtAlert);
				break;
			}
		}
		
		//if there no exits a near alert, then we need to initialise the just created crisis instance
		if(!existsNear){		
			DtCrisisID acId = new DtCrisisID(new PtString(""+nextValueForCrisisID_at_pre));
			ctState.nextValueForCrisisID.value = new PtInteger(ctState.nextValueForCrisisID.value.getValue() + 1);
			EtCrisisType acType = EtCrisisType.small;
			EtCrisisStatus acStatus = EtCrisisStatus.pending;
			DtComment acComment = new DtComment(new PtString("no report defined, yet"));
			aCtCrisis.init(acId, acType, acStatus, aDtGPSLocation, aInstant, acComment);

			//DB: insert crisis in the database
			DbCrises.insertCrisis(aCtCrisis);

			//update Messir composition
			cmpSystemCtCrisis.put(aCtCrisis.id.value.getValue(), aCtCrisis);

		}
				
		//PostF4
		assCtAlertCtCrisis.put(aCtAlert, aCtCrisis);
		//DB: update just inserted alert with its corresponding associated (near) crisis
		DbAlerts.bindAlertCrisis(aCtAlert, aCtCrisis);  
				
				
		//PostF5
		CtHuman aCtHuman = new CtHuman();
		boolean existsHuman = false;

		//check if there already exists a human who reported an Alert 
		for(CtHuman existingHuman:assCtHumanActComCompany.keySet()){
			String exPhoneNumber = existingHuman.id.value.getValue();
			//OLD: String exKind = existingHuman.kind.toString();
			
			//OLD: if(exPhoneNumber.equals(aDtPhoneNumber.value.getValue()) && exKind.equals(aEtHumanKind.toString())){
			if(exPhoneNumber.equals(aDtPhoneNumber.value.getValue())){
				aCtHuman = existingHuman;
				existsHuman = true;
				break;
			}
		}
		
		//if there no exists human, then we need (1) to initialise the just created instance
		// and (2) to add it to the assCtHumanActComCompany relationship 
		if(!existsHuman){
			aCtHuman.init(aDtPhoneNumber, aEtHumanKind);
			assCtHumanActComCompany.put(aCtHuman, currentConnectedComCompany);
			
			//update Messir composition
			cmpSystemCtHuman.put(aCtHuman.id.value.getValue(), aCtHuman);
			
			
			//DB: get currentConnectedComCompany's id
			String idComCompany = DbComCompanies.getComCompanyID(currentConnectedComCompany.name);
			
			//DB: insert human in the database
			DbHumans.insertHuman(aCtHuman, idComCompany);  

			
		}


		//PostF6
		DtSMS sms = new DtSMS(new PtString("Your alert has been registered. We will handle it and keep you informed."));
		currentConnectedComCompany.ieSmsSend(aDtPhoneNumber, sms);
	

		
		//bind human with alert
		assCtAlertCtHuman.put(aCtAlert, aCtHuman);
		//DB: update just inserted alert with reporting human
		DbAlerts.bindAlertHuman(aCtAlert, aCtHuman);  
			
			
		//update Messir composition
		cmpSystemCtAlert.put(aCtAlert.id.value.getValue(), aCtAlert);


			
		return new PtBoolean(true); 
	}







	//actCoordinator Actor
	public PtBoolean oeValidateAlert(DtAlertID aDtAlertID) throws RemoteException {

		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
			ActCoordinator theActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor; 
		
			//PostF1
			CtAlert theAlert = cmpSystemCtAlert.get(aDtAlertID.value.getValue());
			theAlert.status = EtAlertStatus.valid;
			PtString aMessage = new PtString("The Alert with ID '"+ aDtAlertID.value.getValue() + "' is now declared as valid !");
			theActCoordinator.ieMessage(aMessage);
		
			return new PtBoolean(true);
		}
		
		return new PtBoolean(false);
	}



	public PtBoolean oeInvalidateAlert(DtAlertID aDtAlertID) throws RemoteException {
	
		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
				ActCoordinator theActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor; 
			
				//PostF1
				CtAlert theAlert = cmpSystemCtAlert.get(aDtAlertID.value.getValue());
				theAlert.status = EtAlertStatus.invalid;
				PtString aMessage = new PtString("The Alert with ID '"+ aDtAlertID.value.getValue() + "' is now declared as invalid !");
				theActCoordinator.ieMessage(aMessage);
			
				return new PtBoolean(true);
			}
			
		return new PtBoolean(false);
	}




	public PtBoolean oeSetCrisisType(DtCrisisID aDtCrisisID, EtCrisisType aEtCrisisType) throws RemoteException{
		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
			ActCoordinator theActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor; 
		
			//PostF1
			CtCrisis theCrisis = cmpSystemCtCrisis.get(aDtCrisisID.value.getValue());
			theCrisis.type = aEtCrisisType;
			PtString aMessage = new PtString("The Crisis with ID '"+ aDtCrisisID.value.getValue() + "' is now of type '"+ aEtCrisisType.toString() +"' !");
			theActCoordinator.ieMessage(aMessage);
		
			return new PtBoolean(true);
		}
		
		return new PtBoolean(false);	
	}





	public PtBoolean oeSetCrisisStatus(DtCrisisID aDtCrisisID, EtCrisisStatus aEtCrisisStatus) throws RemoteException{

		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
			ActCoordinator theActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor; 
		
			//PostF1
			CtCrisis theCrisis = cmpSystemCtCrisis.get(aDtCrisisID.value.getValue());
			theCrisis.status = aEtCrisisStatus;
			PtString aMessage = new PtString("The Crisis with ID '"+ aDtCrisisID.value.getValue() + "' is now declared as '"+ aEtCrisisStatus.toString() +"' !");
			theActCoordinator.ieMessage(aMessage);
		
			return new PtBoolean(true);
		}
		
		return new PtBoolean(false);
		 
	}





	
	public PtBoolean oeSetCrisisHandler(DtCrisisID aDtCrisisID) throws RemoteException {
		
		Logger log = Log4JUtils.getInstance().getLogger();
		
		
		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
			ActCoordinator theActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor;
			CtCoordinator theCtCoordinator = (CtCoordinator)getCtAuthenticated(theActCoordinator);
			
			CtCrisis theCrisis = cmpSystemCtCrisis.get(aDtCrisisID.value.getValue());
			log.debug("theCrisis Instance is " + theCrisis);
			log.debug("aDtCrisisID.value.getValue() is " + aDtCrisisID.value.getValue());
			
			CtCoordinator theCtCoordinatorAtPre = assCtCrisisCtCoordinator.get(theCrisis);
			log.debug("theCtCoordinatorAtPre is " + assCtCrisisCtCoordinator.get(theCrisis));
			
			//PostF1
			theCrisis.status = EtCrisisStatus.handled; 
			assCtCrisisCtCoordinator.put(theCrisis, theCtCoordinator);
			DbCrises.bindCrisisCoordinator(theCrisis, theCtCoordinator);
			PtString aMessage = new PtString("You are now considered as handling the crisis !");
			theActCoordinator.ieMessage(aMessage);
			
			//PostF2
			for(CtAlert theAlert:getAlertsByCrisis(theCrisis)){
				theAlert.isSentToCoordinator(theActCoordinator);
			}
			
			
			//PostF3
			if(theCtCoordinatorAtPre != null){
				ActCoordinator theActCoordinatorAtPre = (ActCoordinator)assCtAuthenticatedActAuthenticated.get(theCtCoordinatorAtPre);
				log.debug("One of the crisis you were handling is now handled by one of your colleagues!");		
				PtString aMessage2 = new PtString("One of the crisis you were handling is now handled by one of your colleagues!");
				theActCoordinatorAtPre.ieMessage(aMessage2);
			}
			
			
			//PostF4
			List<CtAlert> shitieAlerts = getAlertsByCrisis(theCrisis);
			for(int i = 0; i < shitieAlerts.size(); ++i)
			{
				CtAlert theAlert = shitieAlerts.get(i);
				if(assCtAlertCtHuman == null) System.out.println("HOW IS THAT POSSIBLE ???");
				if(theAlert == null) System.out.println("HOW IS THAT POSSIBLE 2 ???");
				assCtAlertCtHuman.get(theAlert).isAcknowledged();
			}
	
			return new PtBoolean(true);
		} 
		
		return new PtBoolean(false);
	}


	
	public PtBoolean oeReportOnCrisis(DtCrisisID aDtCrisisID, DtComment aDtComment){

		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
			ActCoordinator theActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor; 
		
			//PostF1
			CtCrisis theCrisis = cmpSystemCtCrisis.get(aDtCrisisID.value.getValue());
			theCrisis.comment = aDtComment;
			PtString aMessage = new PtString("The Crisis with ID '"+ aDtCrisisID.value.getValue() + "' has changed its comments to '"+ aDtComment.value.getValue()+"'.");
			theActCoordinator.ieMessage(aMessage);
		
			return new PtBoolean(true);
		}



		return new PtBoolean(true); 
	}


	


	
	public PtBoolean oeGetCrisisSet(EtCrisisStatus aEtCrisisStatus){

		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
			ActCoordinator aActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor;
			//go through all existing crises
			for(String crisisKey:cmpSystemCtCrisis.keySet()){
				CtCrisis crisis = cmpSystemCtCrisis.get(crisisKey);
				if(crisis.status.toString().equals(aEtCrisisStatus.toString()))
						crisis.isSentToCoordinator(aActCoordinator);
			}
			return new PtBoolean(true);
		} 

		
		return new PtBoolean(false);
	}


	
	public PtBoolean oeGetAlertsSet(EtAlertStatus aEtAlertStatus){
		
		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
			ActCoordinator theActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor; 
		
			//PostF1
			for(String alertKey:cmpSystemCtAlert.keySet()){
				CtAlert theCtAlert = cmpSystemCtAlert.get(alertKey);
				if(theCtAlert.status.equals(aEtAlertStatus))
					theCtAlert.isSentToCoordinator(theActCoordinator);
			}
			return new PtBoolean(true);
		}
		 
		return new PtBoolean(false);
	
	}
	
	
	
	public PtBoolean oeCloseCrisis(DtCrisisID aDtCrisisID){
		if(currentRequestingAuthenticatedActor instanceof ActCoordinator){
			ActCoordinator theActCoordinator = (ActCoordinator)currentRequestingAuthenticatedActor; 
			
			CtCrisis theCrisis = cmpSystemCtCrisis.get(aDtCrisisID.value.getValue());
			//PostF1
			theCrisis.status = EtCrisisStatus.closed;
		
			//PostF2
			assCtCrisisCtCoordinator.remove(theCrisis);
			
			//PostF3
			Collection<CtAlert> keys = assCtAlertCtCrisis.keySet();
			CtAlert[] alertkeys = keys.toArray(new CtAlert[0]);
			
			
			for(int i=0;i<alertkeys.length;i++){
				CtAlert theAlert = alertkeys[i];
				if(assCtAlertCtCrisis.get(theAlert)==theCrisis){
					assCtAlertCtCrisis.remove(theAlert);
					cmpSystemCtAlert.remove(theAlert.id.value.getClass());
					if(!assCtAlertCtCrisis.contains(theCrisis))
						break;
				}
			}
			
			//PostF4	
			PtString aMessage = new PtString("The Crisis with ID '"+ aDtCrisisID.value.getValue() + "' is now closed !");
			theActCoordinator.ieMessage(aMessage);
		
			return new PtBoolean(true);
		}



		return new PtBoolean(true);  
	}
	
	
	
	
	
	
	//actAuthenticated Actor
	public PtBoolean oeLogin(DtLogin aDtLogin,DtPassword aDtPassword) throws RemoteException {
		
		//check whether the credentials corresponds to an existing user
		//this is done by checking if there exists an instance with
		//such credential in the ctAuthenticatedInstances data structure
		CtAuthenticated ctAuthenticatedInstance = cmpSystemCtAuthenticated.get(aDtLogin.value.getValue());
		 
		PtBoolean pwdCheck = ctAuthenticatedInstance.pwd.eq(aDtPassword);
		 
		if(ctAuthenticatedInstance != null && pwdCheck.getValue()){
			ctAuthenticatedInstance.vpIsLogged = new PtBoolean(true);
			assCtAuthenticatedActAuthenticated.put(ctAuthenticatedInstance, currentRequestingAuthenticatedActor);
			
			PtString aMessage = new PtString("You are logged ! Welcome . .");
			currentRequestingAuthenticatedActor.ieMessage(aMessage);
			
			return new PtBoolean(true);
		}else{
		
			PtString aMessage = new PtString("Wrong identification information! Please try again ...");
			currentRequestingAuthenticatedActor.ieMessage(aMessage);
		
			Logger log = Log4JUtils.getInstance().getLogger();
			try{
		
				Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
        		IcrashEnvironment env = (IcrashEnvironment)registry.lookup("iCrashEnvironment");
		
				//notify to all administrators that exist in the environment
				//IcrashEnvironmentImpl env = IcrashEnvironmentImpl.getInstance();
				
				for(String adminKey:env.getAdministrators().keySet()){
					ActAdministrator admin = env.getActAdministrator(adminKey);
					aMessage = new PtString("Intrusion tentative !");
					admin.ieMessage(aMessage);
				}
				
			}catch(Exception ex){
				log.error("Exception when trying to reach Environment..." + ex);
			}	
		
			return new PtBoolean(false);
		}
			 
	}
	
	
	
	
	public PtBoolean oeLogout() throws java.rmi.RemoteException {

		Logger log = Log4JUtils.getInstance().getLogger();
		log.debug("current Requesting Authenticated Actor Instance is " + currentRequestingAuthenticatedActor);
		CtAuthenticated ctAuth = getCtAuthenticated(currentRequestingAuthenticatedActor);
		log.debug("current Associated CtAuthenticated Instance is " + ctAuth);
		
		if(ctAuth != null){
			String key = ctAuth.login.value.getValue();
			CtAuthenticated user = cmpSystemCtAuthenticated.get(key);
			user.vpIsLogged = new PtBoolean(false);
			assCtAuthenticatedActAuthenticated.remove(ctAuth);
			PtString aMessage = new PtString("'You are logged out! Good Bye ...");
			currentRequestingAuthenticatedActor.ieMessage(aMessage);
		}
	
		return new PtBoolean(true); 
	}
	
	
	//actAdministrator Actor
	public PtBoolean oeAddCoordinator(DtCoordinatorID aDtCoordinatorID,DtLogin aDtLogin,DtPassword aDtPassword) throws RemoteException {

		Logger log = Log4JUtils.getInstance().getLogger();
		try{

			Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
        	IcrashEnvironment env = (IcrashEnvironment)registry.lookup("iCrashEnvironment");

			//IcrashEnvironmentImpl env = IcrashEnvironmentImpl.getInstance();
			
			
			//PostF1
			ActCoordinator actCoordinator = new ActCoordinator(aDtLogin.value.getValue());
			env.setActCoordinator(aDtLogin.value.getValue(), actCoordinator);
			
			//PostF2
			CtCoordinator ctCoordinator = new CtCoordinator(); 
			ctCoordinator.init(aDtCoordinatorID, aDtLogin, aDtPassword);
		
			//PostF3 and PostF4 done at once w.r.t. our implementation
			assCtAuthenticatedActAuthenticated.put(ctCoordinator, actCoordinator);
			
			//Update composition relationships
			cmpSystemCtAuthenticated.put(aDtLogin.value.getValue(), ctCoordinator);
			assCtCoordinatorActCoordinator.put(ctCoordinator, actCoordinator);
					
			//Post5
			ActAdministrator admin = (ActAdministrator)currentRequestingAuthenticatedActor;
			admin.ieCoordinatorAdded();
		
		}catch(Exception ex){
			log.error("Exception when trying to reach Environment..." + ex);
		}	
		
		
		return new PtBoolean(true); 
	}
	

	public PtBoolean oeDeleteCoordinator(DtCoordinatorID aDtCoordinatorID){
		//retrieve CtAuthenticated -it should exists, according to spec's precondition
		CtAuthenticated ctAuth = getCtAuthenticated(aDtCoordinatorID);

		//however we do perform a check, just in case ;)		
		if(ctAuth != null){
			assCtAuthenticatedActAuthenticated.remove(ctAuth);
			cmpSystemCtAuthenticated.remove(ctAuth.login.value.getValue());
		}
		
		return new PtBoolean(true); 
	}


	//actActivator Actor
	public PtBoolean oeSollicitateCrisisHandling() throws RemoteException {

		Logger log = Log4JUtils.getInstance().getLogger();
		try{
			//go through all existing crises
			for(String crisisKey:cmpSystemCtCrisis.keySet()){
				CtCrisis crisis = cmpSystemCtCrisis.get(crisisKey);
				//PostF1
				if(crisis.maxHandlingDelayPassed().getValue())
					crisis.isAllocatedIfPossible();
				//PostF2
				if(crisis.handlingDelayPassed().getValue()){
					PtString AMessageForCrisisHandlers = new PtString("There are alerts pending since more than the defined delay. Please REACT !");
					//notify ActCoordinators about crises not being handled				
					for(CtCoordinator ctCoor:assCtCoordinatorActCoordinator.keySet())
						assCtCoordinatorActCoordinator.get(ctCoor).ieMessage(AMessageForCrisisHandlers);
				}
			}
		}catch(Exception ex){
			log.error("Exception in oeSollicitateCrisisHandling..." + ex);	
		}

		
		//PostP1
		ctState.vpLastReminder = ctState.clock;
		   
		return new PtBoolean(true); 
	}





	public PtBoolean oeSetClock(DtDateAndTime aCurrentClock){
		ctState.clock = aCurrentClock;
		ctState.clock.show();
		return new PtBoolean(true); 
	}

}
