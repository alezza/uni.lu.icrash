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
package lu.uni.lassy.excalibur.examples.icrash.dev.client.java.testcases;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.SafeIcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;

public class TestCase_client_serverIsAlive {

	static Logger log = Log4JUtils.getInstance().getLogger();
	//static	IcrashSystem sys = IcrashSystem.getInstance();
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException {

		//System.setSecurityManager(new SecurityManager());
		//System.setProperty("java.security.policy","file:./securityManagerPolicies/client.policy");

		//Step 1
		log.info("----Step 0-------");

		Registry registry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
		log.info("get registry");
		 	
		//Gathering the remote object as it was published into the registry
        SafeIcrashSystem iCrashSys_Server = (SafeIcrashSystem)registry.lookup("iCrashServer");
		
		
		iCrashSys_Server.doTest();
		log.info("getting the echo...");

	}

}
