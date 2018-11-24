package jdbclab;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetType {
public  static String getType(ResultSet rs) throws SQLException {
	String type="";
	if(!rs.getString("TYPE_NAME").equals("TIMESTAMP")&&!rs.getString("TYPE_NAME").equals("BLOB")&&!rs.getString("TYPE_NAME").equals("GEOMETRY")) {
	if(!rs.getString("TYPE_NAME").contains("UNSIGNED")) {
		type=rs.getString("TYPE_NAME") +"("+ rs.getString("COLUMN_SIZE")+") ";
	}
	else
	{
		type=rs.getString("TYPE_NAME").substring(0, rs.getString("TYPE_NAME").indexOf("UNSIGNED"))+"("+ rs.getString("COLUMN_SIZE")+") "+"UNSIGNED ";
	}
	}else
	{
	type=rs.getString("TYPE_NAME")+" ";
	}
	return type;
}
}
