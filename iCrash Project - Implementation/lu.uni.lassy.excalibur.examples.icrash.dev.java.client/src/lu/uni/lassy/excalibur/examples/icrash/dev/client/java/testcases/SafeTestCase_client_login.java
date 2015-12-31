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

import lu.uni.lassy.excalibur.examples.icrash.dev.client.java.gui.ShowMessage;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActMsrCreator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.SafeIcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;
//test case to check whether the login function is working on the main/backup
public class SafeTestCase_client_login {

	static Logger log = Log4JUtils.getInstance().getLogger();
	//static	IcrashSystem sys = IcrashSystem.getInstance();
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException {

		//Step 1
		log.info("----Step 0-------");
		log.info("get registry");
		//Gathering the remote object as it was published into the registry
		SafeIcrashSystem iCrashSys_Server = new SafeIcrashSystem(new ShowMessage(null));
		
		//Step 1
		log.info("----Step 1-------");

		ActMsrCreator theCreator = new ActMsrCreator();
		theCreator.oeCreateSystemAndEnvironment(new PtInteger(4));
		
		//Step 2
		log.info("----Step 2------");
		ActAdministrator bill = new ActAdministrator("bill");

		//set up ActAuthenticated instance that performs the request
		iCrashSys_Server.setCurrentRequestingAuthenticatedActor(bill);
		log.info("message setCurrentRequestingAuthenticatedActor sent to system");

		//Step 3
		log.info("----Step 3------");

		DtLogin billLogin = new DtLogin(new PtString("icrashadmin"));
		DtPassword billPwd = new DtPassword(new PtString("7WXC1359"));
		
		iCrashSys_Server.oeLogin(billLogin,billPwd);
		log.info("message oeLogin sent to system");


	}

}
