package jdbclab;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;


public class GetConnection {
public	 static Connection con=null;
static DatabaseMetaData metadata = null;
public static DatabaseMetaData connection(String driver,String databaseUrl,String user,String pass ) {
	try{  
		Class.forName(driver);  
		 con=DriverManager.getConnection(  
				databaseUrl,user,pass); 
			metadata = con.getMetaData();
			
	  } catch (SQLException ex) {
          System.out.println(ex);
      } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return metadata;
}
}
