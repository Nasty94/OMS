package com.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.springframework.stereotype.Repository;
 
import com.demo.model.EmployeeVO;
 
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
   
 
    public List<EmployeeVO> getAllEmployees() throws SQLException 
   
    {
    	
        String query =
        		"SELECT * FROM CLIENT";
    
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
	     Statement s=connection.createStatement();
	        
	     s.execute("SELECT * FROM CLIENT");
	     ResultSet rs = s.executeQuery(query);
	     List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
	     
	     while(rs.next()) {
	    	 int SecurityCode = rs.getInt("SECURITYCODE");
	    	 String FirstName = rs.getString("FIRSTNAME");
	    	 String LastName = rs.getString("LASTNAME");
	    	 int Phone = rs.getInt("PHONE");
	    	 String Country = rs.getString("COUNTRY");
	    	 String Address = rs.getString("COUNTRY");
	    	 
	    	 EmployeeVO vo1 = new EmployeeVO();
	         vo1.setSecuritycode(SecurityCode);
	         vo1.setFirstName(FirstName);
	         vo1.setLastName(LastName);
	         vo1.setPhone(Phone);
	         vo1.setCountry(Country);
	         vo1.setAddress(Address);
	         employees.add(vo1);
	     }
    	

         
        return employees;
    }
}