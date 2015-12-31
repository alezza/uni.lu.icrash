package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.MySqlUtils;

import org.apache.log4j.Logger;

public class DbCoordinators {


	static Logger log = Log4JUtils.getInstance().getLogger();
	
	static Connection conn = null;

	static String url = MySqlUtils.URL;
	static String dbName = MySqlUtils.DB_NAME;
	
	static String userName = MySqlUtils.DB_USER_NAME; 
	static String password = MySqlUtils.DB_USER_PWD;

	
	
	

	static public void insertCoordinator(CtCoordinator aCtCoordinator){
	
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Insert
			
			try{
				Statement st = conn.createStatement();
				
				String id = aCtCoordinator.id.value.getValue();
				String login =  aCtCoordinator.login.value.getValue();
				String pwd =  aCtCoordinator.pwd.value.getValue();
	
				log.debug("[DATABASE]-Insert coordinator");
				int val = st.executeUpdate("INSERT INTO "+ dbName+ ".coordinators" +
											"(id,login,pwd)" + 
											"VALUES("+"'"+id+"'"+",'"+login+"','"+pwd+"')");
				
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
	
	
	static public CtCoordinator getCoordinator(String coordId){
		
		CtCoordinator aCtCoordinator = new CtCoordinator();
		
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			

			/********************/
			//Select
			
			try{
				String sql = "SELECT * FROM "+ dbName + ".coordinators WHERE id = " + coordId;
				

				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet  res = statement.executeQuery(sql);
				
				if(res.next()) {				
					
					aCtCoordinator = new CtCoordinator();
					//coordinator's id
					DtCoordinatorID aId = new DtCoordinatorID(new PtString(res.getString("id")));
					//coordinator's login
					DtLogin aLogin = new DtLogin(new PtString(res.getString("login")));
					//coordinator's pwd
					DtPassword aPwd = new DtPassword(new PtString(res.getString("pwd")));

					aCtCoordinator.init(aId, aLogin,aPwd);
					
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
		
		return aCtCoordinator;

	}
	
	
	
	
	static public void deleteCoordinator(CtCoordinator aCtCoordinator){
	
		try {
			conn = DriverManager.getConnection(url+dbName,userName,password);
			log.debug("Connected to the database");

			/********************/
			//Delete
			
			try{
				String sql = "DELETE FROM "+ dbName+ ".coordinators WHERE id = ?";
				String id = aCtCoordinator.id.value.getValue();

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
