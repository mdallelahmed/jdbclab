package jdbclab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;



public class SophisticateSQLProgram {
	public ArrayList<String>  tab=new ArrayList<String>();
	private int compt=0;
	private String header="",row;
	public void SQLExec(String databaseUrl,String driver, String user,String pass,String query) {

		try{  
			Class.forName(driver);  
			Connection con=DriverManager.getConnection(  
					databaseUrl,user,pass); 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			if(query.toLowerCase().contains("select")){
				  ResultSetMetaData rsmd = rs.getMetaData();
				     int numberOfColumns = rsmd.getColumnCount();
				     for(int i=1;i<numberOfColumns+1;i++) {
				    	
                          header=header+rsmd.getColumnLabel(i)+" ";
				    //	 tab.get(0).concat((rsmd.getColumnName(i)));
				   //	tab[1]= rsmd.getColumnName(i);  
				     }
				     tab.add(header);
				while(rs.next())  
				{
					compt++;
					row="";
					 for(int i=1;i<numberOfColumns+1;i++) {
						 row=row+rs.getObject(i)+" ";
						 }
					 tab.add(row);
					 }
				for (int j=0;j<tab.size();j++) {
					System.out.println(tab.get(j));
				}
			}
			else {
				while(rs.next())  
				{
					if(rs.last()) {
						System.out.println(rs.getRow());
					}
				}
			}
			
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			}  
	}

