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
package lu.uni.lassy.excalibur.examples.icrash.dev.java.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.IcrashEnvironment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.IcrashEnvironmentImpl;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystemImpl;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.ConfigSingleton;

import org.apache.log4j.Logger;

public class LaunchServer {

	static Logger log = Log4JUtils.getInstance().getLogger();


//launch the main or the backup server - name and port of each of the instances in the "dupa.txt" configuration file
	public LaunchServer(String[] _args){
		try{
			String whoAmI = _args[0];
			readConfigFile("conf/dupa.txt", whoAmI);
			
			RmiUtils.PORT = ConfigSingleton.INSTANCE.getPORT();
			String rmiServerName = ConfigSingleton.INSTANCE.getRmiServerName();
			String rmiEnvName = ConfigSingleton.INSTANCE.getRmiEnvName();
			
			log.info("*************************************************");
			log.info("--- Registry created in port " + RmiUtils.PORT);
	    	LocateRegistry.createRegistry(RmiUtils.PORT);
	
			log.info("--- Registry located in port " +  RmiUtils.PORT);
		    Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
		
		
			log.info("*************************************************");
			log.info("--- Create iCrashSys_Server Remote Object");
			//IcrashSystem iCrashSys_Server = new IcrashSystemImpl();
			IcrashSystem iCrashSys_Server = IcrashSystemImpl.getInstance();

			log.info("--- Bind iCrashSys_Server Remote Object");
			registry.rebind(rmiServerName, iCrashSys_Server);

	    	log.info("--- ICrash Server ready and running ...");
	    	
	    	
	    	 
			log.info("*************************************************");

			log.info("--- Create Environment Remote Object");
			//IcrashSystem iCrashSys_Server = new IcrashSystemImpl();
			IcrashEnvironment iCrashEnvironment_RO = IcrashEnvironmentImpl.getInstance();

			log.info("--- Bind Environment Remote Object");
			registry.rebind(rmiEnvName, iCrashEnvironment_RO);
	    	 
	    	log.info("--- Environment Remote Object ready and running ...");
	    	
	    	
	    	
	    	
		
		} catch (Exception e) {
	       log.error("Troubles when launching the ICrash Server: " + e);
	     }
	
	}

	//read the configuration file and set the two instances with their specific name and port
	public void readConfigFile(String fname, String my)
	{
		ArrayList<String> file = new ArrayList<String>();
		
		 Scanner sc;
		 
		try {
			sc = new Scanner(new FileReader(fname));
			while (sc.hasNextLine()){
		        //System.out.println(sc.next());
		        file.add(sc.next());
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   		
		if(my.equalsIgnoreCase("main"))
		{
			ConfigSingleton.INSTANCE.setPORT(Integer.parseInt(file.get(0)));
			ConfigSingleton.INSTANCE.setRmiServerName(file.get(2));
			ConfigSingleton.INSTANCE.setRmiEnvName(file.get(3));
		}
		else
		{
			ConfigSingleton.INSTANCE.setPORT(Integer.parseInt(file.get(1)));
			ConfigSingleton.INSTANCE.setRmiServerName(file.get(2));
			ConfigSingleton.INSTANCE.setRmiEnvName(file.get(3));
		}
	}


	public static void main(String[] args) {
		
	    	  new LaunchServer(args);
	}

}
