/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbclab;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleQuery {
    
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    
    public SimpleQuery() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }  
    public void simpleQueryy() throws SQLException {
        String query = "SELECT last_name FROM actor";
        Statement stmt = con.prepareStatement(query);
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String lastname = rs.getString(1);
                System.out.println(lastname + "\t");
            }
        } catch (SQLException e ) {
System.out.println(e);
} finally {
            if (stmt != null) { stmt.close(); }
        } 
    }

    
}
