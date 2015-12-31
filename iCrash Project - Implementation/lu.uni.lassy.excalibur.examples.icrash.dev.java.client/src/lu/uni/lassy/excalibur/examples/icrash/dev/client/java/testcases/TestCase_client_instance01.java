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
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.IcrashEnvironment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.SafeIcrashEnvironment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActActivator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActMsrCreator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtAlertID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtComment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCrisisID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtGPSLocation;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLatitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLongitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtHumanKind;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDate;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtReal;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.ICrashUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;

public class TestCase_client_instance01 {

	static Logger log = Log4JUtils.getInstance().getLogger();
	//static	IcrashSystem sys = IcrashSystem.getInstance();
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException {

		int d,m,y,h,min,sec;
        

		//Step 1
		log.info("----Step 1-------");
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


		//Step 2
		log.info("----Step 2------");
		
        SafeIcrashEnvironment env = new SafeIcrashEnvironment(new ShowMessage(null));
		
		ActActivator theClock = env.getActActivator();
		
		//set-up time
		d = 24;	m = 11;	y = 2017;
		h = 3;	min = 20;sec = 0;
		
		theClock.oeSetClock(ICrashUtils.setDateAndTime(y, m, d, h, min, sec));
		//theClock.oeSetClock(ICrashUtils.getCurrentDateAndTime());
		
		
		//Step 3
		log.info("----Step 3------");
		ActAdministrator bill = env.getActAdministrator("bill");
		DtLogin billLogin = new DtLogin(new PtString("icrashadmin"));
		DtPassword billPwd = new DtPassword(new PtString("7WXC1359"));

		bill.oeLogin(billLogin,billPwd);
		

		//Step 4
		log.info("----Step 4------");
		/*
	 	bill:actAdministrator sends to system oeAddCoordinator(1,Steve,pwdMessirExcalibur2017)
		to set up a crisis management team made of 
		one coordinator (i.e. identified here by steve) and 
		indicating its identification information in terms of an ID (i.e. 1) and 
		a password (i.e. pwdMessirExcalibur2017).
		*/
		DtCoordinatorID steveID = new DtCoordinatorID(new PtString("1"));
		DtLogin steveLogin = new DtLogin(new PtString("steve"));
		DtPassword stevePwd = new DtPassword(new PtString("pwdMessirExcalibur2017"));
		bill.oeAddCoordinator(steveID,steveLogin,stevePwd);

		//Step 5
		log.info("----Step 5-------");
		bill.oeLogout();
		
		//Step 6
		log.info("----Step 6-------");
		//set-up time
		d=26; m=11;	 y=2017;
		h=10; min=15; sec=0;
		theClock.oeSetClock(ICrashUtils.setDateAndTime(y, m, d, h, min, sec));
		//theClock.oeSetClock(ICrashUtils.getCurrentDateAndTime());
		
		//Step 7
		log.info("----Step 7-------");
		ActComCompany tango = env.getComCompany("tango");
		EtHumanKind aEtHumanKind = EtHumanKind.witness;
		
		d=26; m=11;	 y=2017;
		DtDate aDtDate = ICrashUtils.setDate(y, m, d);
		h=10; min=10; sec=16;
		DtTime aDtTime = ICrashUtils.setTime(h, min, sec);
		/*
		DtDate aDtDate = ICrashUtils.getCurrentDate();  
		DtTime aDtTime = ICrashUtils.getCurrentTime();
		*/
		
		//sending 3 different alerts to the system for the location verification
		//1st alert
		DtPhoneNumber aDtPhoneNumber1 = new DtPhoneNumber(new PtString("+3524666445252"));
		
		DtLatitude aDtLatitude1 = new DtLatitude(new PtReal(49.627675));
		DtLongitude aDtLongitude1 = new DtLongitude(new PtReal(6.159590));
		DtGPSLocation aDtGPSLocation1 = new DtGPSLocation(aDtLatitude1,aDtLongitude1);

		DtComment aDtComment1 = new DtComment(new PtString("2 cars involved in an accident."));
		
		tango.oeAlert(aEtHumanKind, aDtDate, aDtTime, aDtPhoneNumber1, aDtGPSLocation1, aDtComment1);
		
		//2nd alert
		DtPhoneNumber aDtPhoneNumber2 = new DtPhoneNumber(new PtString("+3524666445253"));
		
		DtLatitude aDtLatitude2 = new DtLatitude(new PtReal(49.627424));
		DtLongitude aDtLongitude2 = new DtLongitude(new PtReal(6.160294));
		DtGPSLocation aDtGPSLocation2 = new DtGPSLocation(aDtLatitude2,aDtLongitude2);

		DtComment aDtComment2 = new DtComment(new PtString("3 cars involved in an accident."));
		
		tango.oeAlert(aEtHumanKind, aDtDate, aDtTime, aDtPhoneNumber2, aDtGPSLocation2, aDtComment2);
		
		//3rd alert
		DtPhoneNumber aDtPhoneNumber3 = new DtPhoneNumber(new PtString("+3524666445254"));
		
		DtLatitude aDtLatitude3 = new DtLatitude(new PtReal(49.626671));
		DtLongitude aDtLongitude3 = new DtLongitude(new PtReal(6.159492));
		DtGPSLocation aDtGPSLocation3 = new DtGPSLocation(aDtLatitude3,aDtLongitude3);

		DtComment aDtComment3 = new DtComment(new PtString("4 cars involved in an accident."));
		
		tango.oeAlert(aEtHumanKind, aDtDate, aDtTime, aDtPhoneNumber3, aDtGPSLocation3, aDtComment3);
		
		//Step 8
		/*
		log.info("----Step 8-------");
		d=26; m=11;	 y=2017;
		h=10; min=30; sec=0;
		theClock.oeSetClock(ICrashUtils.setDateAndTime(y, m, d, h, min, sec));
		*/
		//theClock.oeSetClock(ICrashUtils.getCurrentDateAndTime());
		
		
		//Step 9
		log.info("----Step 9-------");
		theClock.oeSollicitateCrisisHandling();

		//Step 10
		log.info("----Step 10-------");
		ActCoordinator steve = env.getActCoordinator("steve");
		DtLogin steveLogin2 = new DtLogin(new PtString("steve"));
		DtPassword stevePwd2 = new DtPassword(new PtString("pwdMessirExcalibur2017"));
		steve.oeLogin(steveLogin2,stevePwd2);

		
		//Step 11
		log.info("----Step 11-------");
		steve.oeGetCrisisSet(EtCrisisStatus.pending);
		
		//Step 12
		log.info("----Step 12-------");
		steve.oeSetCrisisHandler(new DtCrisisID(new PtString("1")));
		
		//Step 13
		log.info("----Step 13-------");
		d=26; m=11;	 y=2017;
		h=10; min=45; sec=0;
		theClock.oeSetClock(ICrashUtils.setDateAndTime(y, m, d, h, min, sec));
		//theClock.oeSetClock(ICrashUtils.getCurrentDateAndTime());
		
		//Step 14
		log.info("----Step 14-------");
		steve.oeValidateAlert(new DtAlertID(new PtString("1")));
		//steve.oeSetCrisisHandler(new DtCrisisID(new PtString("1")));
		

		//Step 15
		log.info("----Step 15-------");
		tango = env.getComCompany("tango");
		aEtHumanKind = EtHumanKind.witness;

		d=26; m=11;	 y=2017;
		aDtDate = ICrashUtils.setDate(y, m, d);
		h=10; min=20; sec=00;
		aDtTime = ICrashUtils.setTime(h, min, sec);

		aDtPhoneNumber1 = new DtPhoneNumber(new PtString("+3524666445000"));
		
		aDtLatitude1 = new DtLatitude(new PtReal(49.627675));
		aDtLongitude1 = new DtLongitude(new PtReal(6.159590));
		aDtGPSLocation1 = new DtGPSLocation(aDtLatitude1,aDtLongitude1);

		aDtComment1 = new DtComment(new PtString("A car crash just happened."));
		
		tango.oeAlert(aEtHumanKind, aDtDate, aDtTime, aDtPhoneNumber1, aDtGPSLocation1, aDtComment1);



		//Step TEST - oeGetAlertsSet
		//log.info("----TEST - oeGetAlertsSet-------");
		//steve.oeGetAlertsSet(EtAlertStatus.pending);



		//Step 16
		log.info("----Step 16-------");
		d=26; m=11;	 y=2017;
		h=12; min=45; sec=0;
		theClock.oeSetClock(ICrashUtils.setDateAndTime(y, m, d, h, min, sec));
		//theClock.oeSetClock(ICrashUtils.getCurrentDateAndTime());

		
		
		//Step 17
		log.info("----Step 17-------");
		steve.oeSetCrisisStatus(new DtCrisisID(new PtString("1")), EtCrisisStatus.solved);
		
		//Step 18
		log.info("----Step 18-------");
		aDtComment1 = new DtComment(new PtString("3 victims sent to hospital, 2 cars evacuated and 4 rescue units mobilized."));
		steve.oeReportOnCrisis(new DtCrisisID(new PtString("1")), aDtComment1);
		
		//Step 19
		log.info("----Step 19-------");
		steve.oeCloseCrisis(new DtCrisisID(new PtString("1")));

	}

}
