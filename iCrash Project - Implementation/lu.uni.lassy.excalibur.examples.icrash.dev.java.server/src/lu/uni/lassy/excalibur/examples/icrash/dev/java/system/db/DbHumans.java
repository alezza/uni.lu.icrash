package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActComCompany;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtHuman;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtHumanKind;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.MySqlUtils;

import org.apache.log4j.Logger;

public class DbHumans {


	static Logger log = Log4JUtils.getInstance().getLogger();
	
	static Connection conn = null;

	static String url = MySqlUtils.URL;
	static String dbName = MySqlUtils.DB_NAME;
	
	static String userName = MySqlUtils.DB_USER_NAME; 
	static String password = MySqlUtils.DB_USER_PWD;

	
	
	

	static public void insertHuman(CtHuman aCtHuman, String comCompany){
	
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Insert
			
			try{
				Statement st = conn.createStatement();
				
				String phone = aCtHuman.id.value.getValue();
				String kind = aCtHuman.kind.toString();
	
				log.debug("[DATABASE]-Insert human");
				int val = st.executeUpdate("INSERT INTO "+ dbName+ ".humans" +
											"(phone,kind,comcompany)" + 
											"VALUES("+"'"+phone+"','"+kind+"','"+comCompany+"')");
				
				log.debug(val + " row affected");
			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}

	
			conn.close();
			log.debug("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	
	static public CtHuman getHuman(String phone){
		
		CtHuman aCtHuman = new CtHuman();
		
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Select
			
			try{
				String sql = "SELECT * FROM "+ dbName + ".humans WHERE phone = " + phone;

				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet  res = statement.executeQuery(sql);
				
				if(res.next()) {				
					
					aCtHuman = new CtHuman();
					//human's id
					DtPhoneNumber aId = new DtPhoneNumber(new PtString(res.getString("phone")));
					//human's kind  -> [witness,victim,anonym]
					String theKind = res.getString("kind");
					EtHumanKind aKind = null;
					if(theKind.equals(EtHumanKind.witness.name()))
						aKind = EtHumanKind.witness;
					if(theKind.equals(EtHumanKind.victim.name()))
						aKind = EtHumanKind.victim;
					if(theKind.equals(EtHumanKind.anonym.name()))
						aKind = EtHumanKind.anonym;

					aCtHuman.init(aId,aKind);
					
				}
								
			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}
			conn.close();
			log.debug("Disconnected from database");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return aCtHuman;

	}
	
	
	static public Hashtable<String, CtHuman> getSystemHumans(){
	
		Hashtable<String, CtHuman> cmpSystemCtHuman = new Hashtable<String, CtHuman>();
		
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			

			/********************/
			//Select
			
			try{
				String sql = "SELECT * FROM "+ dbName + ".humans ";
				

				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet  res = statement.executeQuery(sql);
				
				CtHuman aCtHuman = null;
				
				while(res.next()) {				
					
					aCtHuman = new CtHuman();
					
					//human's id
					DtPhoneNumber aId = new DtPhoneNumber(new PtString(res.getString("phone")));
					//human's kind  -> [witness,victim,anonym]
					String theKind = res.getString("kind");
					EtHumanKind aKind = null;
					if(theKind.equals(EtHumanKind.witness.name()))
						aKind = EtHumanKind.witness;
					if(theKind.equals(EtHumanKind.victim.name()))
						aKind = EtHumanKind.victim;
					if(theKind.equals(EtHumanKind.anonym.name()))
						aKind = EtHumanKind.anonym;

					aCtHuman.init(aId,aKind);
					
					//add instance to the hash
					cmpSystemCtHuman.put(aCtHuman.id.value.getValue(), aCtHuman);
					
				}
				
								
			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}
			conn.close();
			log.debug("Disconnected from database");
			
			
		} catch (Exception e) {
			log.error("SQL connection problems ...");
			e.printStackTrace();
		}
		
		return cmpSystemCtHuman;
		
	}
	
	
	
	static public Hashtable<CtHuman, ActComCompany> getAssCtHumanActComCompany(){
	
		Hashtable<CtHuman, ActComCompany> assCtHumanActComCompany = new Hashtable<CtHuman, ActComCompany>();
		
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			

			/********************/
			//Select
			
			try{
				String sql = "SELECT * FROM "+ dbName + ".humans "+ 
								"INNER JOIN "+ dbName + ".comcompanies ON "+
								 dbName + ".humans.comcompany = "+ dbName + ".comcompanies.id";
				

				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet  res = statement.executeQuery(sql);
				
				CtHuman aCtHuman = null;
				ActComCompany aActComCompany = null;
				
				while(res.next()) {				
					
					aCtHuman = new CtHuman();
					
					//human's id
					DtPhoneNumber aId = new DtPhoneNumber(new PtString(res.getString("phone")));
					//human's kind  -> [witness,victim,anonym]
					String theKind = res.getString("kind");
					EtHumanKind aKind = null;
					if(theKind.equals(EtHumanKind.witness.name()))
						aKind = EtHumanKind.witness;
					if(theKind.equals(EtHumanKind.victim.name()))
						aKind = EtHumanKind.victim;
					if(theKind.equals(EtHumanKind.anonym.name()))
						aKind = EtHumanKind.anonym;

					aCtHuman.init(aId,aKind);
					
					//*************************************
					aActComCompany = new ActComCompany(res.getString("name"));
					//add instances to the hash
					assCtHumanActComCompany.put(aCtHuman, aActComCompany);
					
				}
				
								
			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}
			conn.close();
			log.debug("Disconnected from database");
			
			
		} catch (Exception e) {
			log.error("SQL connection problems ...");
			e.printStackTrace();
		}

		
		return assCtHumanActComCompany;
		
	}

	
	
	
	
	
	static public void deleteHuman(CtHuman aCtHuman){
	
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Delete
			
			try{
				String sql = "DELETE FROM "+ dbName+ ".humans WHERE phone = ?";
				String id = aCtHuman.id.value.getValue();

				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, id);
				int rows = statement.executeUpdate();
				log.debug(rows+" row deleted");				
			}
			catch (SQLException s){
				log.error("SQL statement is not executed! "+s);
			}


			conn.close();
			log.debug("Disconnected from database");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
