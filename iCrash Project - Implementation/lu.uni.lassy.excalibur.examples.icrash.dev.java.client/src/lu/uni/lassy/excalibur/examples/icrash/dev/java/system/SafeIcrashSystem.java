package lu.uni.lassy.excalibur.examples.icrash.dev.java.system;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;
import java.util.List;

import lu.uni.lassy.excalibur.examples.icrash.dev.client.java.gui.ShowMessage;
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
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.RmiUtils;

public class SafeIcrashSystem {
	
//	private IcrashSystem mainSys;
//	private IcrashSystem backupSys;
	
	private ShowMessage logger;
	
	public SafeIcrashSystem()
	{
		logger = null;
	}
	
	public SafeIcrashSystem(ShowMessage currLog)
	{
		logger = currLog;
	}

	/**********************************
	 * New implementation operations 
	***********************************/
	
	//implementing all methods in the environment with try/catch constructions to check whether the main server is alive or not and to execute each of them accordingly
	//also, the users receive notifications in case either one or both the server instances are down
	public void doTest() throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        mainSys.doTest();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
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

	public void setCurrentRequestingAuthenticatedActor(ActAuthenticated aActAuthenticated) throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        mainSys.setCurrentRequestingAuthenticatedActor(aActAuthenticated);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				backupSys.setCurrentRequestingAuthenticatedActor(aActAuthenticated);
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
	public void setCurrentConnectedComCompany(ActComCompany aComCompany) throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        mainSys.setCurrentConnectedComCompany(aComCompany);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				backupSys.setCurrentConnectedComCompany(aComCompany);
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
	public CtState getCtState() throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.getCtState();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.getCtState();
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
	public CtCoordinator getRandomCtCoordinator() throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.getRandomCtCoordinator();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.getRandomCtCoordinator();
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
	public ActCoordinator getActCoordinator(CtCoordinator keyCtCoordinator) throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.getActCoordinator(keyCtCoordinator);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.getActCoordinator(keyCtCoordinator);
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
	public void bindCtCrisisCtCoordinator(CtCrisis aCtCrisis,CtCoordinator aCtCoordinator) throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        mainSys.bindCtCrisisCtCoordinator(aCtCrisis, aCtCoordinator);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				backupSys.bindCtCrisisCtCoordinator(aCtCrisis, aCtCoordinator);
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
    public List<ActAdministrator> getAllActAdministrators() throws java.rmi.RemoteException{
    	try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.getAllActAdministrators();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.getAllActAdministrators();
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
	public ActComCompany getActComCompany(CtHuman aHuman) throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.getActComCompany(aHuman);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.getActComCompany(aHuman);
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
	public Hashtable<String, CtAuthenticated> getCmpSystemCtAuthenticated() throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.getCmpSystemCtAuthenticated();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.getCmpSystemCtAuthenticated();
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
	
	


	/**************************
	 * System operations 
	 **************************/
	 
	//actMsrCreator Actor
	public PtBoolean oeCreateSystemAndEnvironment(PtInteger aQtyComCompanies) throws java.rmi.RemoteException{
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeCreateSystemAndEnvironment(aQtyComCompanies);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeCreateSystemAndEnvironment(aQtyComCompanies);
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

	//actComCompany	 Actor
	public PtBoolean oeAlert(EtHumanKind aEtHumanKind,DtDate aDtDate,
				DtTime aDtTime,DtPhoneNumber aDtPhoneNumber,DtGPSLocation aDtGPSLocation,DtComment aDtComment) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeAlert(aEtHumanKind, aDtDate, aDtTime, aDtPhoneNumber, aDtGPSLocation, aDtComment);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeAlert(aEtHumanKind, aDtDate, aDtTime, aDtPhoneNumber, aDtGPSLocation, aDtComment);
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

	//actCoordinator Actor
	public PtBoolean oeValidateAlert(DtAlertID aDtAlertID) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeValidateAlert(aDtAlertID);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeValidateAlert(aDtAlertID);
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
	public PtBoolean oeInvalidateAlert(DtAlertID aDtAlertID) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeInvalidateAlert(aDtAlertID);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeInvalidateAlert(aDtAlertID);
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
	public PtBoolean oeSetCrisisType(DtCrisisID aDtCrisisID, EtCrisisType aEtCrisisType) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeSetCrisisType(aDtCrisisID, aEtCrisisType);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeSetCrisisType(aDtCrisisID, aEtCrisisType);
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
	public PtBoolean oeSetCrisisStatus(DtCrisisID aDtCrisisID, EtCrisisStatus aEtCrisisStatus) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeSetCrisisStatus(aDtCrisisID, aEtCrisisStatus);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeSetCrisisStatus(aDtCrisisID, aEtCrisisStatus);
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
	public PtBoolean oeSetCrisisHandler(DtCrisisID aDtCrisisID) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeSetCrisisHandler(aDtCrisisID);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeSetCrisisHandler(aDtCrisisID);
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
	public PtBoolean oeReportOnCrisis(DtCrisisID aDtCrisisID, DtComment aDtComment) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeReportOnCrisis(aDtCrisisID, aDtComment);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeReportOnCrisis(aDtCrisisID, aDtComment);
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
	public PtBoolean oeGetCrisisSet(EtCrisisStatus aEtCrisisStatus) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeGetCrisisSet(aEtCrisisStatus);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeGetCrisisSet(aEtCrisisStatus);
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
	public PtBoolean oeGetAlertsSet(EtAlertStatus aEtAlertStatus) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeGetAlertsSet(aEtAlertStatus);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeGetAlertsSet(aEtAlertStatus);
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
	public PtBoolean oeCloseCrisis(DtCrisisID aDtCrisisID) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeCloseCrisis(aDtCrisisID);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeCloseCrisis(aDtCrisisID);
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
	
	
	//actAuthenticated Actor
	public PtBoolean oeLogin(DtLogin aDtLogin,DtPassword aDtPassword) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeLogin(aDtLogin, aDtPassword);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				System.out.println("LOGIN INFO, login" + aDtLogin.value.getValue() + ", password " + aDtPassword.value.getValue());
				return backupSys.oeLogin(aDtLogin, aDtPassword);
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
	public PtBoolean oeLogout() throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeLogout();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeLogout();
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
	
	//actAdministrator Actor
	public PtBoolean oeAddCoordinator(DtCoordinatorID aDtCoordinatorID,DtLogin aDtLogin,DtPassword aDtPassword) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeAddCoordinator(aDtCoordinatorID, aDtLogin, aDtPassword);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeAddCoordinator(aDtCoordinatorID, aDtLogin, aDtPassword);
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
	public PtBoolean oeDeleteCoordinator(DtCoordinatorID aDtCoordinatorID) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeDeleteCoordinator(aDtCoordinatorID);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeDeleteCoordinator(aDtCoordinatorID);
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

	//actActivator Actor
	public PtBoolean oeSollicitateCrisisHandling() throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeSollicitateCrisisHandling();
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeSollicitateCrisisHandling();
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
	public PtBoolean oeSetClock(DtDateAndTime aCurrentClock) throws java.rmi.RemoteException {
		try
		{
			Registry mainRegistry = LocateRegistry.getRegistry(RmiUtils.mainPORT);
			
			//Gathering the remote object as it was published into the registry
			IcrashSystem mainSys = (IcrashSystem)mainRegistry.lookup(RmiUtils.rmiServerName);
	        return mainSys.oeSetClock(aCurrentClock);
		}
		catch(RemoteException ex)
		{
			if(logger != null)
				logger.logMessageToTheUser("Main server crashed. Sending to backup server...");
			Registry backupRegistry = LocateRegistry.getRegistry(RmiUtils.backupPORT);
			IcrashSystem backupSys;
			try {
				backupSys = (IcrashSystem)backupRegistry.lookup(RmiUtils.rmiServerName);
				return backupSys.oeSetClock(aCurrentClock);
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