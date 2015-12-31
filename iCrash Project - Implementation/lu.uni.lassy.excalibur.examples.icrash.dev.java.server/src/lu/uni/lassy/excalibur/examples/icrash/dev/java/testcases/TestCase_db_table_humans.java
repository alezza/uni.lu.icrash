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

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db.DbHumans;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtHuman;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtHumanKind;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;

import org.apache.log4j.Logger;

public class TestCase_db_table_humans {

	static Logger log = Log4JUtils.getInstance().getLogger();
	//static	IcrashSystem sys = IcrashSystem.getInstance();
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException {

		log.info("---- test insert-------");
		//**********************************************************
		//set up human's id
		//DtPhoneNumber aDtPhoneNumber = new DtPhoneNumber(new PtString("+352621731391"));
		DtPhoneNumber aDtPhoneNumber = new DtPhoneNumber(new PtString("+352621731392"));
		
		//**********************************************************
		//set up human kind
		EtHumanKind aEtHumanKind = EtHumanKind.anonym;

		//**********************************************************
		//set up human communication company
		//String comCompany = "tango";
		String comCompany = "orange";
		
		CtHuman aCtHuman = new CtHuman();
		aCtHuman.init(aDtPhoneNumber, aEtHumanKind);
		
		DbHumans.insertHuman(aCtHuman, comCompany);
		

		log.info("---- test select -------");		
		CtHuman aCtHuman2 = DbHumans.getHuman(aDtPhoneNumber.value.getValue());
		log.debug("The retrieved human's phone is " + aCtHuman2.id.value.getValue());
		log.debug("The retrieved human's kind is " + aCtHuman2.kind.toString());
		//log.debug("The retrieved human's com company is " + aCtHuman2.status.toString());

		
		//log.info("---- delete -------");
		//TODO: uncomment the following line to see how a register is removed from the humans table
		//DbHumans.deleteHuman(aCtHuman2);
		
	}


}
