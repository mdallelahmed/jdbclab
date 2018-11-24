package jdbclab;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTableFk {
	public static String tableFk(DatabaseMetaData metadata,String actualTable) throws SQLException {
		ResultSet FK = metadata.getImportedKeys(null, null, actualTable);

		String fk="";
		while(FK.next())
		{
	    	   fk+=",\r\n FOREIGN KEY "+FK.getString("PKCOLUMN_NAME") +" REFERENCES "+ FK.getString("PKTABLE_NAME")+"("+ FK.getString("FKCOLUMN_NAME")+")";       
		}
		return fk;
	}
}
