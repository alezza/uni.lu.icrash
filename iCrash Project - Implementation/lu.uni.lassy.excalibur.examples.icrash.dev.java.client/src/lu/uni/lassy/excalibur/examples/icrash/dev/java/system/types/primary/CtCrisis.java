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
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.SafeIcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDateAndTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

public class CtCrisis implements Serializable {

	private static final long serialVersionUID = 227L;

	public DtCrisisID id;
	public EtCrisisType type;
	public EtCrisisStatus status;
	public DtGPSLocation location;
	public DtDateAndTime instant;
	public DtComment comment;

	public PtBoolean init(DtCrisisID aId, EtCrisisType aType,
			EtCrisisStatus aStatus, DtGPSLocation aLocation,
			DtDateAndTime aInstant, DtComment aComment) {

		id = aId;
		type = aType;
		status = aStatus;
		location = aLocation;
		instant = aInstant;
		comment = aComment;

		return new PtBoolean(true);

	}

	public PtBoolean handlingDelayPassed() throws RemoteException, NotBoundException {
		if(status.toString().equals(EtCrisisStatus.pending.toString())){
		
			//Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
	        //IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");
			SafeIcrashSystem iCrashSys_Server = new SafeIcrashSystem();
		
			//IcrashSystem icrashSys = IcrashSystemImpl.getInstance();
			//CtState ctState = icrashSys.getCtState();
			CtState ctState = iCrashSys_Server.getCtState();
			double currentClockSecondsQty = ctState.clock.toSecondsQty().getValue();
			double vpLastReminderSecondsQty = ctState.vpLastReminder.toSecondsQty().getValue();
			
			if((currentClockSecondsQty - vpLastReminderSecondsQty)>ctState.crisisReminderPeriod.value.getValue())
				return new PtBoolean(true);
		}

		return new PtBoolean(false);
	}



	public PtBoolean maxHandlingDelayPassed() throws RemoteException, NotBoundException {
		if(status.toString().equals(EtCrisisStatus.pending.toString())){
			//IcrashSystem icrashSys = IcrashSystemImpl.getInstance();
			//Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
	        //IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");

			SafeIcrashSystem iCrashSys_Server = new SafeIcrashSystem();
			
			//CtState ctState = icrashSys.getCtState();			
			CtState ctState = iCrashSys_Server.getCtState();

			double currentClockSecondsQty = ctState.clock.toSecondsQty().getValue();
			double crisisInstantSecondsQty = instant.toSecondsQty().getValue();
			
			if((currentClockSecondsQty - crisisInstantSecondsQty)>ctState.maxCrisisReminderPeriod.value.getValue())
				return new PtBoolean(true);
		}

		return new PtBoolean(false);

	}



	public PtBoolean isSentToCoordinator(ActCoordinator aActCoordinator) {
		
		aActCoordinator.ieSendACrisis(this);
		return new PtBoolean(true);
	}



	public PtBoolean isAllocatedIfPossible() throws RemoteException, NotBoundException  {

		//IcrashSystem icrashSys = IcrashSystemImpl.getInstance();
		//Registry registry = LocateRegistry.getRegistry(RmiUtils.PORT);
	    //IcrashSystem iCrashSys_Server = (IcrashSystem)registry.lookup("iCrashServer");

		SafeIcrashSystem iCrashSys_Server = new SafeIcrashSystem();
		
		//CtState ctState = icrashSys.getCtState();
		CtState ctState = iCrashSys_Server.getCtState();
		
		double currentClockSecondsQty = ctState.clock.toSecondsQty().getValue();
		double crisisInstantSecondsQty = instant.toSecondsQty().getValue();
		
		if((currentClockSecondsQty - crisisInstantSecondsQty)>ctState.maxCrisisReminderPeriod.value.getValue()){
			CtCoordinator theCoordinator = iCrashSys_Server.getRandomCtCoordinator();
			if(theCoordinator != null){			
				ActCoordinator theCoordinatorActor = iCrashSys_Server.getActCoordinator(theCoordinator);
				iCrashSys_Server.bindCtCrisisCtCoordinator(this,theCoordinator);
				status = EtCrisisStatus.handled; 
				String crisisId = this.id.value.getValue().toString();
				PtString aMessage = new PtString("You are now considered as handling the crisis having ID: '"+crisisId+"'");
				theCoordinatorActor.ieMessage(aMessage);
			}else{
				
				PtString aMessage = new PtString("Please add new coordinators to handle pending crisis !");
				
				for(ActAdministrator admin:iCrashSys_Server.getAllActAdministrators()){
					admin.ieMessage(aMessage);
				}
			
			}
			
		
			return new PtBoolean(true);
		}




		
		return new PtBoolean(false);

	}
}
