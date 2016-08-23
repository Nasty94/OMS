package com.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import org.springframework.stereotype.Repository;

import com.demo.h2.DBUtils;
import com.demo.model.EmployeeVO;
 
@Repository
public class EmployeeDAOImpl  extends HttpServlet implements EmployeeDAO{
   
	 public EmployeeDAOImpl() {
	        super();
	    }
	 
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
    
    public EmployeeVO getEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	
    	 Connection conn = null;
 		try {
 			conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
 		} catch (SQLException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
  
         String securitycode = (String) request.getParameter("securitycode");
  
         EmployeeVO employee = null;
  
         String errorString = null;
  
         try {
             employee = DBUtils.findEmployee(conn, Integer.parseInt(securitycode));
         } catch (SQLException e) {
             e.printStackTrace();
             errorString = e.getMessage();
         }
  
         if (errorString != null && employee == null) {
             try {
				response.sendRedirect(request.getServletPath() + "/employees");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             return null;
         }
  
         // Store errorString in request attribute, before forward to views.
         request.setAttribute("errorString", errorString);
         request.setAttribute("employee", employee);
         ServletContext context = request.getSession().getServletContext();

         RequestDispatcher dispatcher = context
                 .getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
         dispatcher.forward(request, response);
         
         return employee;
	         
	        
	     }
    	

}