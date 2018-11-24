package jdbclab;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetTableUnique {
	static String unique;
	public static ArrayList<String> tableUnique(DatabaseMetaData metadata, String actualTable) throws SQLException {
		ResultSet UN = metadata.getIndexInfo(null, null, actualTable, false, false);
		 ArrayList<String> list = new ArrayList<String>();
		while(UN.next())
		{
			if(!UN.getString("NON_UNIQUE").equals("true")) {
				list.add(UN.getString("COLUMN_NAME"));
			}
		}	
		return list;
	}
public static String getUnique(DatabaseMetaData metadata, String actualTable,String column) throws SQLException {
	ArrayList<String> list =null;
	
	list=tableUnique(metadata,actualTable);
	if(list.contains(column)) {
		unique="UNIQUE";
	}else {unique="";}
	return unique;
}
}
