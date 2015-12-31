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
package lu.uni.lassy.excalibur.examples.icrash.dev.java.testcases;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Set;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbAlerts;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAlert;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCrisis;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtHuman;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtAlertID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtComment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCrisisID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtGPSLocation;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLatitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLongitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtAlertStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisType;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtHumanKind;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDate;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDateAndTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtReal;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.ICrashUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;

import org.apache.log4j.Logger;
//test case for sending 3 different crisis locations to the system
public class TestCase_db_alerts_location {

	static Logger log = Log4JUtils.getInstance().getLogger();
	//static	IcrashSystem sys = IcrashSystem.getInstance();
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException {

		log.info("---- test insert-------");
		//**********************************************************
		//set up id
		DtAlertID aId1 = new DtAlertID(new PtString("1"));
		DtAlertID aId2 = new DtAlertID(new PtString("2"));
		DtAlertID aId3 = new DtAlertID(new PtString("3"));
		
		//**********************************************************
		//set up status
		EtAlertStatus aStatus = EtAlertStatus.pending;

		//**********************************************************
		//set up location
		DtLatitude aDtLatitude1 = new DtLatitude(new PtReal(49.627675));
		DtLongitude aDtLongitude1 = new DtLongitude(new PtReal(6.159590));
		DtGPSLocation aDtGPSLocation1 = new DtGPSLocation(aDtLatitude1,aDtLongitude1);
		
		DtLatitude aDtLatitude2 = new DtLatitude(new PtReal(49.627424));
		DtLongitude aDtLongitude2 = new DtLongitude(new PtReal(6.160294));
		DtGPSLocation aDtGPSLocation2 = new DtGPSLocation(aDtLatitude2,aDtLongitude2);
		
		DtLatitude aDtLatitude3 = new DtLatitude(new PtReal(49.621623));
		DtLongitude aDtLongitude3 = new DtLongitude(new PtReal(6.1725));
		DtGPSLocation aDtGPSLocation3 = new DtGPSLocation(aDtLatitude3,aDtLongitude3);
		
		//**********************************************************
		//set up instant
		int d,m,y,h,min,sec;
		d=26; m=11;	 y=2017;
		DtDate aDtDate = ICrashUtils.setDate(y, m, d);
		h=10; min=10; sec=16;
		DtTime aDtTime = ICrashUtils.setTime(h, min, sec);
		DtDateAndTime aInstant = new DtDateAndTime(aDtDate,aDtTime);
		/*
		DtDate aDtDate = ICrashUtils.getCurrentDate();  
		DtTime aDtTime = ICrashUtils.getCurrentTime();
		*/
	
	
		//**********************************************************
		//set up comment
		DtComment aDtComment = new DtComment(new PtString("13 cars involved in an accident."));
		
		
		CtAlert aCtAlert1 = new CtAlert();
		aCtAlert1.init(aId1, aStatus,aDtGPSLocation1,aInstant, aDtComment);
		
		DbAlerts.insertAlert(aCtAlert1);
		
		CtAlert aCtAlert2 = new CtAlert();
		aCtAlert2.init(aId2, aStatus,aDtGPSLocation2,aInstant, aDtComment);
		
		DbAlerts.insertAlert(aCtAlert2);
		
		CtAlert aCtAlert3 = new CtAlert();
		aCtAlert3.init(aId3, aStatus,aDtGPSLocation3,aInstant, aDtComment);
		
		DbAlerts.insertAlert(aCtAlert3);
		
		log.info("---- test select -------");		
		CtAlert aCtAlert12 = DbAlerts.getAlert(aId1.value.getValue());
		log.debug("The retrieved alert's id is " + aCtAlert2.id.value.getValue());
		log.debug("The retrieved alert's status is " + aCtAlert2.status.toString());
		log.debug("The retrieved alert's comment is " + aCtAlert2.comment.value.getValue());
		
		Hashtable<String, CtAlert> tb1 = DbAlerts.getSystemAlerts();
		
		Set<String> keys = tb1.keySet();
//		for(String key : keys){
//			CtAlert temp = tb1.get(key);
//			log.debug("The retrieved alert's id is " + temp.id.value.getValue());
//			log.debug("The retrieved alert's status is " + temp.status.toString());
//			log.debug("The retrieved alert's comment is " + temp.comment.value.getValue());
//			log.debug("The retrieved alert's location (latitude) is " + temp.location.latitude.value);
//			log.debug("The retrieved alert's location (longitude) is " + temp.location.longitude.value);
//			
//			
//		}
		//comparing all alerts' locations with each other and using the best distance to determine whether they are near to each other
		for(String key : keys){
			CtAlert temp = tb1.get(key);
			
			for(String key2 : keys){
				CtAlert temp2 = tb1.get(key2);
				
				DtLatitude loc1 =  temp2.location.latitude;
				DtLongitude loc2 =  temp2.location.longitude;
				
				DtGPSLocation location = new DtGPSLocation(temp.location.latitude,temp.location.longitude);
				
				if (temp.location.latitude.value.getValue() == temp2.location.latitude.value.getValue() 
						&& temp.location.longitude.value.getValue() == temp2.location.longitude.value.getValue())
					continue;

				PtBoolean res = location.isNearTo(loc1, loc2);
				if (res.getValue() == true)
		        	log.info("location of reported crisis is near to the location of an already reported crisis");
		        else log.info("location is not near to any already reported incidents");
			}
			
//			log.debug("Alert id " + temp.id.value.getValue() + " is near to mid point : " + res.getValue());
//			log.debug("The retrieved alert's status is " + temp.status.toString());
//			log.debug("The retrieved alert's comment is " + temp.comment.value.getValue());
//			log.debug("The retrieved alert's location (latitude) is " + temp.location.latitude.value);
//			log.debug("The retrieved alert's location (longitude) is " + temp.location.longitude.value);
			
			
			
		}
		
		
	}


}
