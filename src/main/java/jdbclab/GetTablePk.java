package jdbclab;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTablePk {
	
	public static String tablePk(DatabaseMetaData metadata,String actualTable) throws SQLException {
		  ResultSet PK = metadata.getPrimaryKeys(null,null, actualTable);

		while(PK.next())
		{
		    return ",\r\n PRIMARY KEY " +"("+PK.getString("COLUMN_NAME")+")";
		}
		return"";
	}
}
