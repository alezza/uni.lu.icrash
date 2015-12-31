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
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.SafeIcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;

public class ActMsrCreator implements Serializable {

		private static final long serialVersionUID = 227L;
		
		//IcrashSystem sys = IcrashSystem.getInstance();


		public PtBoolean oeCreateSystemAndEnvironment(PtInteger aQtyComCompanies) throws RemoteException, NotBoundException {

			Logger log = Log4JUtils.getInstance().getLogger();
			
			//Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
  
			log.info("get registry");
			 	
			//Gathering the remote object as it was published into the registry
	        //IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
	        
	        SafeIcrashSystem iCrashSys_Server = new SafeIcrashSystem();
			
			log.info("message ActMsrCreator.oeCreateSystemAndEnvironment sent to system");
			PtBoolean res = iCrashSys_Server.oeCreateSystemAndEnvironment(aQtyComCompanies);
			
			
			if(res.getValue() == true)
				log.info("operation oeCreateSystemAndEnvironment successfully executed by the system");
		
			return new PtBoolean(true);
		}

}
