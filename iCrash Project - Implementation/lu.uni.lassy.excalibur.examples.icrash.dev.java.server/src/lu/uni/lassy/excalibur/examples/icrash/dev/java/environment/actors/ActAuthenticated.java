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

import java.io.Serializable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;

import org.apache.log4j.Logger;

public abstract class ActAuthenticated implements Serializable {

		private static final long serialVersionUID = 227L;
		//IcrashSystem sys = IcrashSystem.getInstance();


		//public ActAuthenticated() {}


		/*
		synchronized public PtBoolean oeLogin(DtLogin aDtLogin,DtPassword aDtPassword)  throws RemoteException, NotBoundException {
			
			Logger log = Log4JUtils.getInstance().getLogger();

			Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
			//Gathering the remote object as it was published into the registry
	        IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		

			//set up ActAuthenticated instance that performs the request
			iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);
			
			 
			log.info("message ActAuthenticated.oeLogin sent to system");
			PtBoolean res = iCrashSys_Server.oeLogin(aDtLogin,aDtPassword);
			
			
			if(res.getValue() == true)
				log.info("operation oeLogin successfully executed by the system");
			
			
			return new PtBoolean(true);
		}




		synchronized public PtBoolean oeLogout() throws RemoteException, NotBoundException {
		
			Logger log = Log4JUtils.getInstance().getLogger();
		
			Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
			//Gathering the remote object as it was published into the registry
	        IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		
		
			//set up ActAuthenticated instance that performs the request
			iCrashSys_Server.setCurrentRequestingAuthenticatedActor(this);
			
			log.info("message ActAuthenticated.oeLogout sent to system");
			PtBoolean res = iCrashSys_Server.oeLogout();
			
			if(res.getValue() == true)
				log.info("operation oeLogout successfully executed by the system");
			
			return new PtBoolean(true);
		}

		*/

		
		public PtBoolean ieMessage(PtString aMessage){
			Logger log = Log4JUtils.getInstance().getLogger();
			
			log.info("message ActAuthenticated.ieMessage received from system");
			log.info("ieMessage is: " + aMessage.getValue());
		
			return new PtBoolean(true);
		}


}
