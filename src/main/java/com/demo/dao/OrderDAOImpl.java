package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.model.OrderVO;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	private final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	/*
	 private static final String DBNAME = "oms";

		
	 private static final String DB_DRIVER = "org.h2.Driver";
	 //  private static final String DB_CONNECTION = //"jdbc:h2:mem~/Documents/GitHub/OMS/src/main/oms";
	    private static final String DB_USER = "sa";
	    private static final String DB_PASSWORD = "sa";*/
 
    public List<OrderVO> getAllOrders() throws SQLException 
    {
    	String query =
         		"SELECT a.ORVERNR, a.CONVPRICE, a.TRANDATE, a.BARCODE, "
         		+ "b.NAME, b.PRICE, b.DESCRIPTION, b.DATE"
         		+ "FROM ORDER a INNER JOIN PRODUCT b"
         		+ "ON a.BARCODE = b.BARCODE";
    	
    	
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
     	 
 	     Statement s=connection.createStatement();
 	        
 	    // s.execute("SELECT * FROM PRODUCT");
 	     ResultSet rs = s.executeQuery(query);
 	    
 	     List<OrderVO> orders = new ArrayList<OrderVO>();
 	     
 	     while(rs.next()) {
 	    	 int ordernr = rs.getInt("ORDERNR");
 	    	 int convprice = rs.getInt("CONVPRICE");
 	    	 String trandate = rs.getString("TRANDATE");
 	    	 int barcode = rs.getInt("BARCODE");
 	    	 String name = rs.getString("NAME");
 	    	 String description = rs.getString("DESCRIPTION");
 	    	 String date = rs.getString("DATE");

 	    	 
 	    	 OrderVO vo1 = new OrderVO();
 	         vo1.setOrdernr(ordernr);
 	         vo1.setConvprice(convprice);
 	         vo1.setTrandate(trandate);
 	         vo1.setBarcode(barcode);
 	         vo1.setName(name);
 	         vo1.setDescription(description);
 	         vo1.setDate(date);
 	         orders.add(vo1);
 	     }

         return orders;
     }

}
