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
package lu.uni.lassy.excalibur.examples.icrash.dev.java.system;

import java.util.Hashtable;
import java.util.List;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAuthenticated;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAuthenticated;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCrisis;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtHuman;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtState;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtAlertID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtComment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCrisisID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtGPSLocation;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtAlertStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisType;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtHumanKind;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDate;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDateAndTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;

public interface IcrashSystem extends java.rmi.Remote {



	/**********************************
	 * New implementation operations 
	***********************************/

	public void doTest() throws java.rmi.RemoteException;

	public void setCurrentRequestingAuthenticatedActor(ActAuthenticated aActAuthenticated) throws java.rmi.RemoteException; 
	public void setCurrentConnectedComCompany(ActComCompany aComCompany) throws java.rmi.RemoteException; 
	public CtState getCtState() throws java.rmi.RemoteException; 
	public CtCoordinator getRandomCtCoordinator() throws java.rmi.RemoteException; 
	public ActCoordinator getActCoordinator(CtCoordinator keyCtCoordinator) throws java.rmi.RemoteException; 
	public void bindCtCrisisCtCoordinator(CtCrisis aCtCrisis,CtCoordinator aCtCoordinator) throws java.rmi.RemoteException; 
    public List<ActAdministrator> getAllActAdministrators() throws java.rmi.RemoteException; 
	public ActComCompany getActComCompany(CtHuman aHuman) throws java.rmi.RemoteException; 
	public Hashtable<String, CtAuthenticated> getCmpSystemCtAuthenticated() throws java.rmi.RemoteException; 
	
	


	/**************************
	 * System operations 
	 **************************/
	 
	//actMsrCreator Actor
	public PtBoolean oeCreateSystemAndEnvironment(PtInteger aQtyComCompanies) throws java.rmi.RemoteException; 

	//actComCompany	 Actor
	public PtBoolean oeAlert(EtHumanKind aEtHumanKind,DtDate aDtDate,
				DtTime aDtTime,DtPhoneNumber aDtPhoneNumber,DtGPSLocation aDtGPSLocation,DtComment aDtComment) throws java.rmi.RemoteException; 

	//actCoordinator Actor
	public PtBoolean oeValidateAlert(DtAlertID aDtAlertID) throws java.rmi.RemoteException; 
	public PtBoolean oeInvalidateAlert(DtAlertID aDtAlertID) throws java.rmi.RemoteException; 
	public PtBoolean oeSetCrisisType(DtCrisisID aDtCrisisID, EtCrisisType aEtCrisisType) throws java.rmi.RemoteException; 
	public PtBoolean oeSetCrisisStatus(DtCrisisID aDtCrisisID, EtCrisisStatus aEtCrisisStatus) throws java.rmi.RemoteException; 		
	public PtBoolean oeSetCrisisHandler(DtCrisisID aDtCrisisID) throws java.rmi.RemoteException; 
	public PtBoolean oeReportOnCrisis(DtCrisisID aDtCrisisID, DtComment aDtComment) throws java.rmi.RemoteException; 
	public PtBoolean oeGetCrisisSet(EtCrisisStatus aEtCrisisStatus) throws java.rmi.RemoteException; 
	public PtBoolean oeGetAlertsSet(EtAlertStatus aEtAlertStatus) throws java.rmi.RemoteException; 
	public PtBoolean oeCloseCrisis(DtCrisisID aDtCrisisID) throws java.rmi.RemoteException; 
	
	
	//actAuthenticated Actor
	public PtBoolean oeLogin(DtLogin aDtLogin,DtPassword aDtPassword) throws java.rmi.RemoteException; 
	public PtBoolean oeLogout() throws java.rmi.RemoteException; 
	
	//actAdministrator Actor
	public PtBoolean oeAddCoordinator(DtCoordinatorID aDtCoordinatorID,DtLogin aDtLogin,DtPassword aDtPassword) throws java.rmi.RemoteException; 	
	public PtBoolean oeDeleteCoordinator(DtCoordinatorID aDtCoordinatorID) throws java.rmi.RemoteException; 

	//actActivator Actor
	public PtBoolean oeSollicitateCrisisHandling() throws java.rmi.RemoteException; 
	public PtBoolean oeSetClock(DtDateAndTime aCurrentClock) throws java.rmi.RemoteException; 

}
