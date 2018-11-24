package jdbclab;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetMetaData {
	static GetConnection getConnection;
	public static DatabaseMetaData getMetadata(String driver,String databaseUrl,String user,String pass ) throws SQLException {
		DatabaseMetaData metadata;
		metadata=getConnection.connection(driver, databaseUrl, user, pass);
		return metadata;
	}
}
