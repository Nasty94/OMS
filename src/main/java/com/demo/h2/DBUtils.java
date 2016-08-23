package com.demo.h2;

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
 
import com.demo.model.*;

 
public class DBUtils {
 
    public List<ProductVO> getAllProducts(Connection conn) throws SQLException 
    {
    	 String query =
         		"SELECT * FROM PRODUCT";
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
     	 
 	     Statement s=conn.createStatement();
 	        
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
    
 
  public static ProductVO findProduct(Connection conn, int BarCode) throws SQLException {
      String sql = "Select a.Code, a.Name, a.Price, a.Description from Product a where a.Code=?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setInt(1, BarCode);
 
      ResultSet rs = pstm.executeQuery();
 
      while (rs.next()) {
          String Name = rs.getString("Name");
          int Price = rs.getInt("Price");
          String Description = rs.getString("Description");
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	      String textDate = df.format(rs.getString("DATE"));
	      ProductVO vo1 = new ProductVO();
	         vo1.setBarCode(BarCode);
	         vo1.setName(Name);
	         vo1.setPrice(Price);
	         vo1.setDescription(Description);
	         vo1.setDate(textDate);
          return vo1;
      }
      return null;
  }
  
  public static EmployeeVO findEmployee(Connection conn, int securitycode) throws SQLException {
      String sql = "Select a.SecurityCode, a.FirstName, a.LastName, a.Phone, a.Country, a.Address from Client a where a.SecurityCode=?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setInt(1, securitycode);
 
      ResultSet rs = pstm.executeQuery();
 
      while (rs.next()) {
          String FirstName = rs.getString("firstname");
          String LastName = rs.getString("lastname");
          int Phone = rs.getInt("phone");
          String Country = rs.getString("country");
          String Address = rs.getString("address");
	      EmployeeVO vo1 = new EmployeeVO();
	         vo1.setSecuritycode(securitycode);
	         vo1.setFirstName(FirstName);
	         vo1.setLastName(LastName);
	         vo1.setPhone(Phone);
	         vo1.setCountry(Country);
	         vo1.setAddress(Address);
          return vo1;
      }
      return null;
  }
 
  public static void updateProduct(Connection conn, ProductVO product) throws SQLException, ParseException {
      String sql = "Update Product set Name =?, Price=?, Description=?, Date=? where BarCode=? ";
 
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
  
  public static void updateEmployee(Connection conn, EmployeeVO employee) throws SQLException, ParseException {
      String sql = "Update Product set FirstName =?, LastName=?, Phone=?, Country=?, Address=?where SecurityCode=? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, employee.getFirstName());
      pstm.setString(2, employee.getLastName());
      pstm.setInt(3, employee.getPhone());
      pstm.setString(4, employee.getCountry());
      pstm.setString(5, employee.getAddress());
      pstm.executeUpdate();
  }
 
 /* public static void insertProduct(Connection conn, Product product) throws SQLException {
      String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, product.getCode());
      pstm.setString(2, product.getName());
      pstm.setFloat(3, product.getPrice());
 
      pstm.executeUpdate();
  }
 
  public static void deleteProduct(Connection conn, String code) throws SQLException {
      String sql = "Delete Product where Code= ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, code);
 
      pstm.executeUpdate();
  }*/
 
}