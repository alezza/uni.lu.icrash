package lu.uni.lassy.excalibur.examples.icrash.dev.java.environment;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.client.java.gui.ShowMessage;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActActivator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.IcrashSystem;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;



public class SafeIcrashEnvironment {
	
	private ShowMessage logger;
	
	public SafeIcrashEnvironment()
	{
		logger = null;
	}
	
	public SafeIcrashEnvironment(ShowMessage currLog)
	{
		logger = currLog;
	}
	//implementing all methods in the environment with try/catch constructions to check whether the main server is alive or not and to execute each of them accordingly
	//also, the users receive notifications in case either one or both the server instances are down
	public void doTest() throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        mainSys.doTest();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				backupSys.doTest(); 
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	public void setActAdministrator(String keyName,ActAdministrator aActAdministrator) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        mainSys.setActAdministrator(keyName, aActAdministrator);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				backupSys.setActAdministrator(keyName, aActAdministrator);
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public ActAdministrator getActAdministrator(String keyName) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        return mainSys.getActAdministrator(keyName);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				return backupSys.getActAdministrator(keyName);
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public Hashtable<String, ActAdministrator> getAdministrators() throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        return mainSys.getAdministrators();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				return backupSys.getAdministrators();
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public void setActActivator(ActActivator aActActivator) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        mainSys.setActActivator(aActActivator);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				backupSys.setActActivator(aActActivator);
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public ActActivator getActActivator() throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        return mainSys.getActActivator();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				return backupSys.getActActivator();
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public void setComCompany(String keyName, ActComCompany aActComCompany) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        mainSys.setComCompany(keyName, aActComCompany);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				backupSys.setComCompany(keyName, aActComCompany);
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public ActComCompany getComCompany(String keyName) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        return mainSys.getComCompany(keyName);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				return backupSys.getComCompany(keyName);
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public void setActCoordinator(String keyName, ActCoordinator aActCoordinator) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        mainSys.setActCoordinator(keyName, aActCoordinator);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				backupSys.setActCoordinator(keyName, aActCoordinator);
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public ActCoordinator getActCoordinator(String keyName) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        return mainSys.getActCoordinator(keyName);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				return backupSys.getActCoordinator(keyName);
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public Hashtable<String, ActComCompany> getActComCompanies() throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashEnvironment mainSys = (IcrashEnvironment)mainRegistry.lookup(RmiUtils.rmiEnvName);
	        return mainSys.getActComCompanies();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashEnvironment backupSys;
			try {
				backupSys = (IcrashEnvironment)backupRegistry.lookup(RmiUtils.rmiEnvName);
				return backupSys.getActComCompanies();
			}
			catch (RemoteException e) {
				if(logger != null)
					logger.logMessageToTheUser("Backup server crashed");
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	        
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

}
