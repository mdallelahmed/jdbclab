package jdbclab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReverseEngineering {
	public static final String databaseUrl = "jdbc:mysql://localhost:8889/sakila";
	public static final String user = "root";
	public static final String pass = "root";
	public static final String driver = "com.mysql.jdbc.Driver";
	static DatabaseMetaData metadata = null;
	static GetTable tables;
	static GetType getType;
	static GetTableUnique getTableUnique;
	static GetTableFk fk;
	static GetNull getNull;
	static GetTableIndex getTableIndex;
	static GetTablePk getTablePk;
	static GetMetaData getMetaData;
	static String nullfiled, unique = "", type;
	final static File fichier = new File("src/main/java/script.txt");

	public ReverseEngineering() {
		try {
			fichier.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getColumnsMetadata() throws SQLException, IOException {
		FileWriter writer = new FileWriter(fichier);
		ResultSet rs, rs2 = null;
		metadata = GetMetaData.getMetadata(driver, databaseUrl, user, pass);
		ArrayList tablee = tables.getTablesMetadata(metadata);
		Iterator<String> it = tablee.iterator();
		while (it.hasNext()) {
			String actualTable = it.next();
			rs = metadata.getColumns(null, null, actualTable, null);
			rs2 = metadata.getColumns(null, null, actualTable, null);
			writer.write("CREATE TABLE " + actualTable + "( \n");
			int nbr = 0, nbr2 = countColumn(rs2);
			while (rs.next()) {
				nbr++;
				unique = getTableUnique.getUnique(metadata, actualTable, rs.getString("COLUMN_NAME"));
				type = getType.getType(rs);
				nullfiled = getNull.ColumnNull(rs.getInt("NULLABLE"));
				if (nbr != nbr2) {
					writer.write(rs.getString("COLUMN_NAME") + " " + type + nullfiled + " " + unique + ", \n");
				} else {
					writer.write(rs.getString("COLUMN_NAME") + " " + type + nullfiled + " " + unique
							+ getTablePk.tablePk(metadata, actualTable) + fk.tableFk(metadata, actualTable)
							+ getTableIndex.tableIndex(metadata, actualTable) + "); \n");
					writer.write("\r\n");
				}
			}
		}

		writer.close();
	}

	public static int countColumn(ResultSet rs) throws SQLException {
		int nbr = 0;
		while (rs.next()) {
			nbr++;
		}
		return nbr;
	}
}
