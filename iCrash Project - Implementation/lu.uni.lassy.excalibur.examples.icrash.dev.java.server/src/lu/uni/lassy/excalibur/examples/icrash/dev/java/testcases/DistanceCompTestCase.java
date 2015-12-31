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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtGPSLocation;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLatitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLongitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtReal;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

import org.apache.log4j.Logger;

public class DistanceCompTestCase {

	static Logger log = Log4JUtils.getInstance().getLogger();
	//static	IcrashSystem sys = IcrashSystem.getInstance();
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
       
        PtReal nr1 = new PtReal (49.627869);
        PtReal nr2 = new PtReal (6.153422);
        
        DtLatitude lat = new DtLatitude(nr1);
        DtLongitude longt = new DtLongitude(nr2);
        
        PtReal nr1p = new PtReal (49.628219);
        PtReal nr2p = new PtReal (6.154172);
        
        DtLatitude latp = new DtLatitude(nr1p);
        DtLongitude longtp = new DtLongitude(nr2p);
        
        DtGPSLocation location = new DtGPSLocation(lat,longt);
        if (location.isNearTo(latp, longtp).getValue() == true)
        	log.info("location of reported crisis is near to the location of an already reported crisis");
        else log.info("location is not near to any already reported incidents");
	}


}
