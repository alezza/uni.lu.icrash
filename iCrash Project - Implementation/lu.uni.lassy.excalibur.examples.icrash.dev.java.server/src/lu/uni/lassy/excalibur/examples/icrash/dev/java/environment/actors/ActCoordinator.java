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
package lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAlert;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCrisis;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;

import org.apache.log4j.Logger;

public class ActCoordinator extends ActAuthenticated {

	private static final long serialVersionUID = 227L;
	String name;
	//IcrashSystem sys = IcrashSystem.getInstance();


	public ActCoordinator(String n){
			name = n;
		}

	public String getName(){
			return name;
		}


	/*
	synchronized public PtBoolean oeGetCrisisSet(EtCrisisStatus aEtCrisisStatus) throws RemoteException, NotBoundException {
	
		Logger log = Log4JUtils.getInstance().getLogger();
	
		Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
		//Gathering the remote object as it was published into the registry
	    IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		
	
		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		log.info("message ActCoordinator.oeGetCrisisSet sent to system");
		PtBoolean res = iCrashSys_Server.oeGetCrisisSet(aEtCrisisStatus);
			
			
		if(res.getValue() == true)
			log.info("operation oeGetCrisisSet successfully executed by the system");


		return new PtBoolean(true);
	}



	synchronized public PtBoolean oeSetCrisisHandler(DtCrisisID aDtCrisisID) throws RemoteException, NotBoundException {
	
		Logger log = Log4JUtils.getInstance().getLogger();
	
		Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
		//Gathering the remote object as it was published into the registry
	    IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
	
		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		log.info("message ActCoordinator.oeSetCrisisHandler sent to system");
		PtBoolean res = iCrashSys_Server.oeSetCrisisHandler(aDtCrisisID);
			
			
		if(res.getValue() == true)
			log.info("operation oeSetCrisisHandler successfully executed by the system");


		return new PtBoolean(true);
	}



	synchronized public PtBoolean oeValidateAlert(DtAlertID aDtAlertID) throws RemoteException, NotBoundException {
	
		Logger log = Log4JUtils.getInstance().getLogger();
	
		Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);

			 	
		//Gathering the remote object as it was published into the registry
	    IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
	
		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		log.info("message ActCoordinator.oeValidateAlert sent to system");
		PtBoolean res = iCrashSys_Server.oeValidateAlert(aDtAlertID);
			
			
		if(res.getValue() == true)
			log.info("operation oeValidateAlert successfully executed by the system");


		return new PtBoolean(true);
	}



	synchronized public PtBoolean oeSetCrisisStatus(DtCrisisID aDtCrisisID, EtCrisisStatus aEtCrisisStatus) throws RemoteException, NotBoundException {
	
		Logger log = Log4JUtils.getInstance().getLogger();
	
		Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);

			 	
		//Gathering the remote object as it was published into the registry
	    IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		log.info("message ActCoordinator.oeSetCrisisStatus sent to system");
		PtBoolean res = iCrashSys_Server.oeSetCrisisStatus(aDtCrisisID, aEtCrisisStatus);
			
			
		if(res.getValue() == true)
			log.info("operation oeSetCrisisStatus successfully executed by the system");


		return new PtBoolean(true);
	}





	synchronized public PtBoolean oeReportOnCrisis(DtCrisisID aDtCrisisID,DtComment aDtComment) throws RemoteException, NotBoundException {
	
		Logger log = Log4JUtils.getInstance().getLogger();

		Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
		//Gathering the remote object as it was published into the registry
	    IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);
		
		
		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		log.info("message ActCoordinator.oeReportOnCrisis sent to system");
		PtBoolean res = iCrashSys_Server.oeReportOnCrisis(aDtCrisisID, aDtComment);
			
			
		if(res.getValue() == true)
			log.info("operation oeReportOnCrisis successfully executed by the system");


		return new PtBoolean(true);

	}
	
	
	
	

	synchronized public PtBoolean oeCloseCrisis(DtCrisisID aDtCrisisID) throws RemoteException, NotBoundException {
	
		Logger log = Log4JUtils.getInstance().getLogger();

		Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);

			 	
		//Gathering the remote object as it was published into the registry
	    IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		log.info("message ActCoordinator.oeCloseCrisis sent to system");
		PtBoolean res = iCrashSys_Server.oeCloseCrisis(aDtCrisisID);
			
			
		if(res.getValue() == true)
			log.info("operation oeCloseCrisis successfully executed by the system");


		return new PtBoolean(true);

		
	}
	*/

	public PtBoolean ieSendACrisis(CtCrisis aCtCrisis) {
	
		Logger log = Log4JUtils.getInstance().getLogger();

		log.info("message ActCoordinator.ieSendACrisis received from system");
		log.info("crisis id '"	+ aCtCrisis.id.value.getValue().toString() + "' "+
				 "in status '"+ aCtCrisis.status.toString()+"'");

		return new PtBoolean(true);
	}
	

	public PtBoolean ieSendAnAlert(CtAlert aCtAlert) {
	
		Logger log = Log4JUtils.getInstance().getLogger();

		log.info("message ActCoordinator.ieSendAnAlert received from system");
		log.info("alert id '"	+ aCtAlert.id.value.getValue().toString() + "' "+
				 " with comment '"+ aCtAlert.comment.value.getValue() +"'"+
				 " in status '"+ aCtAlert.status.toString()+"'");

		return new PtBoolean(true);
	}


	/*
	synchronized public PtBoolean oeGetAlertsSet(EtAlertStatus aEtAlertStatus) throws RemoteException, NotBoundException {

		Logger log = Log4JUtils.getInstance().getLogger();

		Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);

			 	
		//Gathering the remote object as it was published into the registry
	    IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);

		log.info("message ActCoordinator.oeGetAlertsSet sent to system");
		PtBoolean res = iCrashSys_Server.oeGetAlertsSet(aEtAlertStatus);
			
			
		if(res.getValue() == true)
			log.info("operation oeGetAlertsSet successfully executed by the system");


		return new PtBoolean(true);

		
	}*/



}
