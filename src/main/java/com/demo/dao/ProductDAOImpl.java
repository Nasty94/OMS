package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 	    	 int Price = rs.getInt("PRICE");
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
    
    public static void updateProduct(Connection conn, ProductVO product) throws SQLException, ParseException {
        String sql = "Update Product set Name =?, Price=?, Description=?, Date=? where BarCode=? ";
        conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
        PreparedStatement pstm = conn.prepareStatement(sql);
   
        pstm.setString(1, product.getName());
        pstm.setInt(2, product.getPrice());
        pstm.setString(3, product.getDescription());
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date parsed = format.parse(product.getDate());
        java.sql.Date SQLDate = new java.sql.Date(parsed.getTime());
        
        pstm.setDate(4, SQLDate);
        pstm.executeUpdate();
    }
}
