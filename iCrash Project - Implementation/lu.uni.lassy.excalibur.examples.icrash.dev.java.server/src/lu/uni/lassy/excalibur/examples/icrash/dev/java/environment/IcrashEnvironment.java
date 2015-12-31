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

import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActActivator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;

public interface IcrashEnvironment  extends java.rmi.Remote {

	public void doTest() throws java.rmi.RemoteException;

	public void setActAdministrator(String keyName,ActAdministrator aActAdministrator) throws java.rmi.RemoteException;
	public ActAdministrator getActAdministrator(String keyName) throws java.rmi.RemoteException;

	public Hashtable<String, ActAdministrator> getAdministrators() throws java.rmi.RemoteException;

	public void setActActivator(ActActivator aActActivator) throws java.rmi.RemoteException;
	public ActActivator getActActivator() throws java.rmi.RemoteException;

	public void setComCompany(String keyName, ActComCompany aActComCompany) throws java.rmi.RemoteException;
	public ActComCompany getComCompany(String keyName) throws java.rmi.RemoteException;

	public void setActCoordinator(String keyName, ActCoordinator aActCoordinator) throws java.rmi.RemoteException;
	public ActCoordinator getActCoordinator(String keyName) throws java.rmi.RemoteException;

	public Hashtable<String, ActComCompany> getActComCompanies() throws java.rmi.RemoteException;
}
