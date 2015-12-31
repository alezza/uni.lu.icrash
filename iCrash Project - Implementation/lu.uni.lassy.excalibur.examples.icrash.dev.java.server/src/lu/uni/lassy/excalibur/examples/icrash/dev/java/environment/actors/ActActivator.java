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

public class ActActivator implements Serializable {

		private static final long serialVersionUID = 227L;

		//IcrashSystem sys = IcrashSystemImpl.getInstance();

		/*
		public PtBoolean oeSetClock(DtDateAndTime aDtDateAndTime) throws RemoteException, NotBoundException {

			Logger log = Log4JUtils.getInstance().getLogger();
		
        	Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
			//Gathering the remote object as it was published into the registry
	        IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
		
			
			log.info("message ActActivator.oeSetClock sent to system");
			PtBoolean res = iCrashSys_Server.oeSetClock(aDtDateAndTime);
			
			
			if(res.getValue() == true)
				log.info("operation oeSetClock successfully executed by the system");
		
			return new PtBoolean(true);
		}




		public PtBoolean oeSollicitateCrisisHandling() throws RemoteException, NotBoundException {
		
			Logger log = Log4JUtils.getInstance().getLogger();
		
        	Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
			//Gathering the remote object as it was published into the registry
	        IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");

			
			log.info("message ActActivator.oeSollicitateCrisisHandling sent to system");
			PtBoolean res = iCrashSys_Server.oeSollicitateCrisisHandling();
			
			
			if(res.getValue() == true)
				log.info("operation oeSollicitateCrisisHandling successfully executed by the system");
		
			return new PtBoolean(true);
		}

		*/
}
