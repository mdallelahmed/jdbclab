package jdbclab;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTableIndex {
	public static String tableIndex(DatabaseMetaData metadata,String actualTable) throws SQLException {
		ResultSet UN = metadata.getIndexInfo(null, null, actualTable, false, false);
		String index="";
		while(UN.next())
		{
			if(!UN.getString("INDEX_NAME").equals("PRIMARY")) {
	index+=",\r\n INDEX "+UN.getString("INDEX_NAME")+"("+UN.getString("COLUMN_NAME")+")";}
		}	
		return index;
	}
}
