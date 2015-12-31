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

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActMsrCreator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;

import org.apache.log4j.Logger;

public class TestCase_client_createSystemAndEnvironment {

	static Logger log = Log4JUtils.getInstance().getLogger();
	//static	IcrashSystem sys = IcrashSystem.getInstance();
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException {

		//Step 1
		log.info("----Step 0-------");
		
		/*
		 *  the unique and always existing actMsrCreator actor instantiated (named here theCreator) requests
		 * 	the initialization of the (1) system and (2) its environment (made of 
		 * 
		 * 	2.1 one administrator identified here by bill), 
		 *  2.2 one activator actor (identified by theClock), and 
		 *  2.3 indicating that the number of communication	company actor instances for the system's environment 
		 *  is 4 (one of them is identified here by tango)
		 *  
		 */

		ActMsrCreator theCreator = new ActMsrCreator();
		theCreator.oeCreateSystemAndEnvironment(new PtInteger(4));
		log.info("System and Environment created.");
		
		
		

	}

}
