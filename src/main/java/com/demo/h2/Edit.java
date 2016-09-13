package com.demo.h2;

//import org.testng.annotations.Test;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Edit {
	
	 private static final String DB_DRIVER = "org.h2.Driver";
	    private static final String DB_CONNECTION = "jdbc:h2:~/Documents/GitHub/OMS/src/main/oms";
	    private static final String DB_USER = "sa";
	    private static final String DB_PASSWORD = "";
	    
	    private void updateClient(int SecurityCode, String FirstName, String LastName,
	    		int Phone, String Country, String Address) throws SQLException {
	        Connection connection= DriverManager.getConnection(DB_CONNECTION);
	        Statement s=connection.createStatement();
	        
	        s.execute("UPDATE CLIENT SET SECURITYCODE=" + SecurityCode +
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
	    
	    private void updateProduct(int Barcode, String Name, String Price,
	    		String Description, String date) throws SQLException, ParseException {
	        Connection connection= DriverManager.getConnection(DB_CONNECTION);
	        Statement s=connection.createStatement();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	        java.util.Date parsed = format.parse(date);
	        java.sql.Date sql = new java.sql.Date(parsed.getTime());
	        s.execute("UPDATE PRODUCT SET BARCODE=" + Barcode +
	        		", NAME=" + Name + ", PRICE=" + Price +
	        		", DESCRIPTION=" + Description + ", DATE=" + sql);
	        PreparedStatement ps=connection.prepareStatement("select * from PRODUCT");
	        ResultSet r=ps.executeQuery();
	        if(r.next()) {
	            System.out.println("data?");
	        }
	        r.close();
	        ps.close();
	        s.close();
	        connection.close();
	    }
	    
	    private void updateOrder(int OrderNr, int Price, String currency, String date) throws SQLException, ParseException {
	        Connection connection= DriverManager.getConnection(DB_CONNECTION);
	        Statement s=connection.createStatement();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	        java.util.Date parsed = format.parse(date);
	        java.sql.Date sql = new java.sql.Date(parsed.getTime());
	        
	        s.execute("UPDATE ORDER SET ORDERNR=" + OrderNr +
	        		", PRICE=" + Price + ", CURRENCY=" + currency + "TransactionDate=" + sql );
	        
	        PreparedStatement ps=connection.prepareStatement("select * from ORDER");
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
