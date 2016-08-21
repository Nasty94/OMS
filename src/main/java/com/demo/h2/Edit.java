package com.demo.h2;

//import org.testng.annotations.Test;

import java.sql.*;

public class Edit {
	
	 private static final String DB_DRIVER = "org.h2.Driver";
	    private static final String DB_CONNECTION = "jdbc:h2:~/Documents/GitHub/OMS/src/main/oms";
	    private static final String DB_USER = "sa";
	    private static final String DB_PASSWORD = "";
	    
	    private void updateDatabase(int SecurityCode, String FirstName, String LastName,
	    		int Phone, String Country, String Address) throws SQLException {
	        Connection connection= DriverManager.getConnection(DB_CONNECTION);
	        Statement s=connection.createStatement();
	        
	        s.execute("ALTER TABLE CLIENT SET SECURITYCODE=" + SecurityCode +
	        		", FIRSTNAME=" + FirstName + ", LASTNAME=" + LastName +
	        		", PHONE=" + Phone + ", COUNTRY=" + Country + ", ADDRESS=" + Address);
	        PreparedStatement ps=connection.prepareStatement("select * from CLIENT");
	        ResultSet r=ps.executeQuery();
	        if(r.next()) {
	            System.out.println("data?");
	        }
	        r.close();
	        ps.close();
	        s.close();
	        connection.close();
	    }

}
