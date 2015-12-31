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

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.secondary.DtSMS;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;

import org.apache.log4j.Logger;

public class ActComCompany implements Serializable {

		private static final long serialVersionUID = 227L;
		public String name;

		//IcrashSystem sys = IcrashSystem.getInstance();


		public ActComCompany(String n){
			name = n;
		} 
		
		
		/*
		synchronized public PtBoolean oeAlert(EtHumanKind aEtHumanKind,DtDate aDtDate,
									DtTime aDtTime,DtPhoneNumber aDtPhoneNumber,
									DtGPSLocation aDtGPSLocation,DtComment aDtComment) throws RemoteException, NotBoundException {
			
			Logger log = Log4JUtils.getInstance().getLogger();
			
			Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
			 	
			//Gathering the remote object as it was published into the registry
	        IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
	        
	        
			//set up ComCompany instance that performs the request
			iCrashSys_Server.setCurrentConnectedComCompany(this);
			
			
			log.info("message ActComCompany.oeAlert sent to system");
			PtBoolean res = iCrashSys_Server.oeAlert(aEtHumanKind,aDtDate,aDtTime,aDtPhoneNumber,aDtGPSLocation,aDtComment);
			
			
			if(res.getValue() == true)
				log.info("operation oeAlert successfully executed by the system");
		
			return new PtBoolean(true);
		}
		
		
		
		*/	
		
		public PtBoolean ieSmsSend(DtPhoneNumber aDtPhoneNumber,DtSMS aDtSMS){
			Logger log = Log4JUtils.getInstance().getLogger();
			
			log.info("message ActComCompany.ieSmsSend received from system");
			log.info("Phone number: "+aDtPhoneNumber.value.getValue());
			log.info("SMS: "+aDtSMS.value.getValue());
		
			return new PtBoolean(true);
		}
		
	
}
