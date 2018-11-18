package jdbclab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static DataSource data;
    private Connection con;
    public static final String databaseUrl = "jdbc:mysql://localhost:8889/sakila";
    public static final String user = "root";
    public static final String pass= "root";

    private DataSource() {
        try {
            con = DriverManager.getConnection(databaseUrl, user, pass);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    

    public Connection getConnection() {
        return con;
    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;

    }


	public static DataSource getData() {
		return data;
	}


	public static void setData(DataSource data) {
		DataSource.data = data;
	}


	public Connection getCon() {
		return con;
	}


	public void setCon(Connection con) {
		this.con = con;
	}


	public static String getDatabaseurl() {
		return databaseUrl;
	}


	public static String getUser() {
		return user;
	}


	public static String getPass() {
		return pass;
	}
    
}
