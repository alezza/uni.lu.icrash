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
package lu.uni.lassy.excalibur.examples.icrash.dev.java.environment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActActivator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;

public class IcrashEnvironmentImpl extends UnicastRemoteObject implements IcrashEnvironment {

	private static final long serialVersionUID = 1L;

	//ActAdministrator actAdministrator;
	Hashtable<String, ActAdministrator> admins = new Hashtable<String, ActAdministrator>();
	ActActivator actActivator;
	Hashtable<String, ActComCompany> comCompanies = new Hashtable<String, ActComCompany>();
	//Key = ActCoordinator's login's name
	Hashtable<String, ActCoordinator> coordinators = new Hashtable<String, ActCoordinator>();


	//Eager singleton pattern
	private static volatile IcrashEnvironmentImpl instance = null;
 
    // private constructor
    private IcrashEnvironmentImpl() throws RemoteException {
     	super();
    }
 
    public static IcrashEnvironmentImpl getInstance()  throws RemoteException {
     	if(instance == null)
        	instance = new IcrashEnvironmentImpl();
        return instance;
    }

	
	public void doTest() throws java.rmi.RemoteException{
		System.out.println("The environment is set ...");
	}
	
	
	public void setActAdministrator(String keyName,ActAdministrator aActAdministrator){
		admins.put(keyName, aActAdministrator);
	}

	public ActAdministrator getActAdministrator(String keyName){
		return admins.get(keyName);
	}


	public Hashtable<String, ActAdministrator> getAdministrators(){
		return admins;
	}



	public void setActActivator(ActActivator aActActivator){
		actActivator = aActActivator;
	}

	public ActActivator getActActivator(){
		return actActivator;
	}
	
	public void setComCompany(String keyName, ActComCompany aActComCompany){
		comCompanies.put(keyName, aActComCompany);
	}

	
	public ActComCompany getComCompany(String keyName){
		return comCompanies.get(keyName);
	}



	public void setActCoordinator(String keyName, ActCoordinator aActCoordinator){
		coordinators.put(keyName, aActCoordinator);
	}


	public ActCoordinator getActCoordinator(String keyName){
		return coordinators.get(keyName);
	}



	public Hashtable<String, ActComCompany> getActComCompanies(){
		return comCompanies;
	}
}
