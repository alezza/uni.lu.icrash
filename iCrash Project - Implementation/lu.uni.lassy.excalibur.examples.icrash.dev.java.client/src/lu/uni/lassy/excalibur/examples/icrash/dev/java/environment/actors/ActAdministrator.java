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

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.SafeIcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;

public class ActAdministrator extends ActAuthenticated {

		private static final long serialVersionUID = 227L;
		String name;

		//IcrashSystem sys = IcrashSystem.getInstance();

		public ActAdministrator(String n){
			name = n;
		}

		public String getName(){
			return name;
		}

		synchronized public PtBoolean oeAddCoordinator(DtCoordinatorID aDtCoordinatorID,	DtLogin aDtLogin,DtPassword aDtPassword) throws RemoteException, NotBoundException {
		
			Logger log = Log4JUtils.getInstance().getLogger();
		
		
        	///Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
			//Gathering the remote object as it was published into the registry
	        //IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");

        	
        	SafeIcrashSystem iCrashSys_Server = new SafeIcrashSystem();
        	
			//set up ActAuthenticated instance that performs the request
			iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);
			
	        
			
			log.info("message ActAdministrator.oeAddCoordinator sent to system");
			PtBoolean res = iCrashSys_Server.oeAddCoordinator(aDtCoordinatorID,aDtLogin,aDtPassword);
			
			
			if(res.getValue() == true)
				log.info("operation oeAddCoordinator successfully executed by the system");
			
			
			return new PtBoolean(true);
		}




		synchronized public PtBoolean oeDeleteCoordinator(DtCoordinatorID aDtCoordinatorID) throws RemoteException, NotBoundException{

			Logger log = Log4JUtils.getInstance().getLogger();
			
			//Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
			//Gathering the remote object as it was published into the registry
	        //IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		
	        SafeIcrashSystem iCrashSys_Server = new SafeIcrashSystem();

			//set up ActAuthenticated instance that performs the request
			iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);
			
			log.info("message ActAdministrator.oeDeleteCoordinator sent to system");
			PtBoolean res = iCrashSys_Server.oeDeleteCoordinator(aDtCoordinatorID);
			
			
			if(res.getValue() == true)
				log.info("operation oeDeleteCoordinator successfully executed by the system");
			
		
			return new PtBoolean(true);
		}
		
		
		
		
		public PtBoolean ieCoordinatorAdded(){
			//log.info("message ActAdministrator.ieCoordinatorAdded received from system");
			return new PtBoolean(true);
		}
		
		
		
		
		public PtBoolean ieCoordinatorDeleted(){
			//log.info("message ActAdministrator.ieCoordinatorDeleted received from system");
			return new PtBoolean(true);
		}
		

}
