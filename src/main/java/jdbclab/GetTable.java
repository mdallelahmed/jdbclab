package jdbclab;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetTable {
	static GetConnection getConnection;
	static GetMetaData metaData;
	public static ArrayList getTablesMetadata(DatabaseMetaData metadata) throws SQLException {
		String table[] = { "TABLE" };
		ResultSet rs = null;
		ArrayList tables = null;
		rs = metadata.getTables(null, null, null, table);
		tables = new ArrayList();
		while (rs.next()) {
			tables.add(rs.getString("TABLE_NAME"));
		}
		return tables;
	}
}
