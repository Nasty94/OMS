package com.demo.h2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
 
  
  public static EmployeeVO findEmployee(Connection conn, int securitycode) throws SQLException {
      String sql = "Select a.SecurityCode, a.FirstName, a.LastName, a.Phone, a.Country, a.Address from Client a where a.SecurityCode=?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setInt(1, securitycode);
 
      ResultSet rs = pstm.executeQuery();
 
      while (rs.next()) {
    	  int SecurityCode = rs.getInt("securitycode");
          String FirstName = rs.getString("firstName");
          String LastName = rs.getString("lastName");
          int Phone = rs.getInt("phone");
          String Country = rs.getString("country");
          String Address = rs.getString("address");
	      EmployeeVO vo1 = new EmployeeVO();
	         vo1.setSecuritycode(SecurityCode);
	         vo1.setFirstName(FirstName);
	         vo1.setLastName(LastName);
	         vo1.setPhone(Phone);
	         vo1.setCountry(Country);
	         vo1.setAddress(Address);
          return vo1;
      }
      return null;
  }

  
  public static void updateEmployee(Connection conn, EmployeeVO employee) throws SQLException, ParseException {
      String sql = "Update Client set FirstName =?, LastName=?, Phone=?, Country=?, Address=? where SecurityCode=? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      
      
      pstm.setString(1, employee.getFirstName());
      pstm.setString(2, employee.getLastName());
      pstm.setInt(3, employee.getPhone());
      pstm.setString(4, employee.getCountry());
      pstm.setString(5, employee.getAddress());
      pstm.setInt(6, employee.getSecuritycode());
      pstm.executeUpdate();
  }
 
 


public static void insertEmployee(Connection conn, EmployeeVO employee) throws SQLException {
	String create = "create table client(securitycode int, firstname varchar(255), lastname varchar(255), phone int, country varchar(255), address varchar(255))";
	TableCheck(conn, "CLIENT", create);
	  
	String sql = "Insert into Client(securitycode, firstname, lastname, phone, country, address) values (?,?,?,?,?,?)";

    PreparedStatement pstm = conn.prepareStatement(sql);

    pstm.setInt(1, employee.getSecuritycode());
    pstm.setString(2, employee.getFirstName());
    pstm.setString(3, employee.getLastName());
    pstm.setInt(4, employee.getPhone());
    pstm.setString(5, employee.getCountry());
    pstm.setString(6, employee.getAddress());

    pstm.executeUpdate();
	
}
 
 private static  void TableCheck (Connection conn, String TableName, String sql) throws SQLException {
	 DatabaseMetaData dbm = conn.getMetaData();
	// check if "employee" table is there
	ResultSet tables = dbm.getTables(null, null, TableName, null);
	if (!tables.next()) {
		PreparedStatement create = conn.prepareStatement(sql);
	    create.executeUpdate();
	}
	
	
 }
 
}