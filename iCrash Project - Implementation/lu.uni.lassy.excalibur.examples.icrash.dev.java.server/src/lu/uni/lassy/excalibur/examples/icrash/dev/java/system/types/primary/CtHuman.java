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
package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.secondary.DtSMS;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;


public class CtHuman implements Serializable {

	private static final long serialVersionUID = 227L;

	public DtPhoneNumber id;
	public EtHumanKind kind;


	public PtBoolean init(DtPhoneNumber aId,EtHumanKind aKind){

		id = aId;
		kind = aKind;

		return new PtBoolean(true);
	}


	public PtBoolean isAcknowledged() throws RemoteException {

		Logger log = Log4JUtils.getInstance().getLogger();
		try{
			//IcrashSystem icrashSys = IcrashSystemImpl.getInstance();
			Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
	        IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
			
			ActComCompany theComCompany = iCrashSys_Server.getActComCompany(this);
	
			if(theComCompany != null){
				DtSMS sms = new DtSMS(new PtString("The handling of your alert by our services is in progress !"));
				theComCompany.ieSmsSend(this.id, sms);
	
				return new PtBoolean(true);
			}
		}catch(Exception ex){
			log.error("Exception in CtHuman.isAcknowledged ..." + ex);
		}
		
		return new PtBoolean(false);
	}




}
