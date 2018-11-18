package jdbclab;
import java.io.IOException;
import java.sql.*;
public class Main {

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
    	   /**
         *partie simpleQuery
         */
      //  SimpleQuery ser=new SimpleQuery();
    	//ser.simpleQueryy();
    	 /**
         *partie SophisticateSQLProgram
         */
       // SophisticateSQLProgram sophisticateSQLProgram= new SophisticateSQLProgram();
        //	sophisticateSQLProgram.SQLExec("jdbc:mysql://localhost:8889/sakila", "com.mysql.jdbc.Driver", "root", "root", "SELECT * FROM actor");

    	 /**
         *partie ReverseEngineering
         */
        ReverseEngineering reverseEngineering = new ReverseEngineering();

			
        try {
			reverseEngineering.getColumnsMetadata();;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
    }
