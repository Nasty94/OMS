package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Repository;

import com.demo.model.EmployeeVO;
import com.demo.model.ProductVO;
 
@Repository
public class ProductDAOImpl implements ProductDAO {
	/*
	 private static final String DBNAME = "oms";

		
	 private static final String DB_DRIVER = "org.h2.Driver";
	 //  private static final String DB_CONNECTION = //"jdbc:h2:mem~/Documents/GitHub/OMS/src/main/oms";
	    private static final String DB_USER = "sa";
	    private static final String DB_PASSWORD = "sa";*/
 
    public List<ProductVO> getAllProducts() throws SQLException 
    {
    	 String query =
         		"SELECT * FROM PRODUCT";
     
     	 Connection connection= DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
 	     Statement s=connection.createStatement();
 	        
 	     s.execute("SELECT * FROM PRODUCT");
 	     ResultSet rs = s.executeQuery(query);
 	     List<ProductVO> products = new ArrayList<ProductVO>();
 	     
 	     while(rs.next()) {
 	    	 int BarCode = rs.getInt("BarCODE");
 	    	 String Name = rs.getString("NAME");
 	    	 String Price = rs.getString("PRICE");
 	    	 String Description = rs.getString("DESCRIPTION");
 	    	 
 	    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
 	    	String textDate = df.format(rs.getString("DATE"));  
 	    	
 	    	 
 	    	 ProductVO vo1 = new ProductVO();
 	         vo1.setBarCode(BarCode);
 	         vo1.setName(Name);
 	         vo1.setPrice(Price);
 	         vo1.setDescription(Description);
 	         vo1.setDate(textDate);
 	         products.add(vo1);
 	     }

         return products;
     }
}
