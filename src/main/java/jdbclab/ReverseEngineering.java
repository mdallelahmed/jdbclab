package jdbclab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReverseEngineering {
    public static final String databaseUrl = "jdbc:mysql://localhost:8889/sakila";
    public static final String user = "root";
    public static final String pass= "root";
    static DatabaseMetaData metadata = null;
    static Connection con=null;
    static String script="";
    static String nullfiled,unique="",type;
    final static File fichier =new File("src/main/java/script.txt"); 
	


public ReverseEngineering() {
	 try {
		fichier .createNewFile();

		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static ArrayList getTablesMetadata() throws SQLException {
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		 con=DriverManager.getConnection(  
				databaseUrl,user,pass); 
		 metadata = con.getMetaData();
	  } catch (SQLException ex) {
          System.out.println(ex);
      } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String table[] = { "TABLE" };
	ResultSet rs = null;
	ArrayList tables = null;
	
	// receive the Type of the object in a String array.
	rs = metadata.getTables(null, null, null, table);
	tables = new ArrayList();
	while (rs.next()) {
		tables.add(rs.getString("TABLE_NAME"));
	}
	return tables;
}
public static void getColumnsMetadata()
		throws SQLException, IOException {
	 FileWriter writer = new FileWriter(fichier);
	ResultSet rs,rs2 = null;
	ArrayList tablee = getTablesMetadata();
	Iterator<String> it = tablee.iterator();
	while (it.hasNext()) {
		  String actualTable = it.next();
		  ResultSet PK = metadata.getPrimaryKeys(null,null, actualTable);
			ResultSet UN = metadata.getIndexInfo(null, null, actualTable, false, false);
			ResultSet UN2 = metadata.getIndexInfo(null, null, actualTable, false, false);

			ResultSet FK = metadata.getImportedKeys(null, null, actualTable);
		rs = metadata.getColumns(null, null, actualTable, null);
		rs2 = metadata.getColumns(null, null, actualTable, null);
		System.out.println(actualTable.toUpperCase());
		//create table actualtable
		script+="CREATE "+actualTable+"( \n";
		writer.write("CREATE TABLE "+actualTable+"( \n");
		int nbr=0,nbr2=countColumn(rs2);
		
		while (rs.next()) {
			nbr++;
			System.out.println(rs.getString("COLUMN_NAME") + " "
					+ rs.getString("TYPE_NAME") + " "
					+ rs.getString("COLUMN_SIZE")
					+"  "+rs.getInt("NULLABLE"));
			if(rs.getInt("NULLABLE")==0) {
				nullfiled="NOT NULL";
			}else {nullfiled="NULL";}
		//	System.out.println(countColumn(rs2));
			ArrayList<String> list =null;
			list=tableUnique(UN);
			if(list.contains(rs.getString("COLUMN_NAME"))) {
				unique="UNIQUE";
			}else {unique="";}
			if(!rs.getString("TYPE_NAME").contains("UNSIGNED")) {
				type=rs.getString("TYPE_NAME") +"("+ rs.getString("COLUMN_SIZE")+") ";
			}
			else
			{
				type=rs.getString("TYPE_NAME").substring(0, rs.getString("TYPE_NAME").indexOf("UNSIGNED"))+"("+ rs.getString("COLUMN_SIZE")+") "+"UNSIGNED ";
			}
			if(nbr!=nbr2) {
			writer.write(rs.getString("COLUMN_NAME") + " "+ type+nullfiled+" "+unique+", \n");}
			else {
				writer.write(rs.getString("COLUMN_NAME") + " "+type+nullfiled+" "+unique+tablePk(PK)+tableFk(FK, actualTable)+tableIndex(UN2)+"); \n");
				writer.write("\r\n"); 

			}
		//	script+=rs.getString("COLUMN_NAME") + " "+ rs.getString("TYPE_NAME") +"("+ rs.getString("COLUMN_SIZE")+")"+nullfiled+", \n";
		}
		
		//System.out.println(script);
		System.out.println("\n");
		
	
	
		
	    
	}
	writer.close();
}
public static int countColumn(ResultSet rs) throws SQLException {
	int nbr=0;
	while (rs.next()) {
		nbr++;
	}
	return nbr;
	
}
public static String tablePk(ResultSet PK) throws SQLException {
	while(PK.next())
	{
	    return ",\r\n PRIMARY KEY " +"("+PK.getString("COLUMN_NAME")+")";
	}
	return"";
}
public static String tableFk(ResultSet FK,String table) throws SQLException {
	String fk="";
	while(FK.next())
	{

    	   fk+=",\r\n FOREIGN KEY "+FK.getString("PKCOLUMN_NAME") +" REFERENCES "+ FK.getString("PKTABLE_NAME")+"("+ FK.getString("FKCOLUMN_NAME")+")";

       
	}
	return fk;
}
public static ArrayList<String> tableUnique(ResultSet UN) throws SQLException {
	 ArrayList<String> list = new ArrayList<String>();
	while(UN.next())
	{
		if(!UN.getString("NON_UNIQUE").equals("true")) {
			list.add(UN.getString("COLUMN_NAME"));
		}
	    System.out.println( UN.getString("INDEX_NAME") + "---" + UN.getString("COLUMN_NAME") + "===" + UN.getString("NON_UNIQUE") + "--- name      " + UN.getString("INDEX_NAME"));
	}	
	return list;
}

public static String tableIndex(ResultSet UN) throws SQLException {
	String index="";
	while(UN.next())
	{
		System.out.println("------indexxxx --------"+UN.getString("INDEX_NAME"));
		if(!UN.getString("INDEX_NAME").equals("PRIMARY")) {
index+=",\r\n INDEX "+UN.getString("INDEX_NAME")+"("+UN.getString("COLUMN_NAME")+")";}
	}	
	return index;
}
}

