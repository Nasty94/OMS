package com.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

import org.springframework.stereotype.Repository;

import com.demo.h2.DBUtils;

import com.demo.model.EmployeeVO;
 
@Repository
public class EmployeeDAOImpl  extends HttpServlet implements EmployeeDAO{
	
	private final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
   
	 public EmployeeDAOImpl() {
	        super();
	    }
	 
    public List<EmployeeVO> getAllEmployees() throws SQLException 
   
    {
    	logger.debug("getAllEmployees() is executed!");
        String query =
        		"SELECT * FROM CLIENT";
    
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
	     Statement s=connection.createStatement();
	        
	     //s.execute("SELECT * FROM CLIENT");
	     ResultSet rs = s.executeQuery(query);
	     List<EmployeeVO> employees = new ArrayList<EmployeeVO>();
	     
	     while(rs.next()) {
	    	 int SecurityCode = rs.getInt("SECURITYCODE");
	    	 String FirstName = rs.getString("FIRSTNAME");
	    	 String LastName = rs.getString("LASTNAME");
	    	 int Phone = rs.getInt("PHONE");
	    	 String Country = rs.getString("COUNTRY");
	    	 String Address = rs.getString("ADDRESS");
	    	 
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
    	logger.debug("getEmployee() is executed!");
    	 Connection conn = null;
 		try {
 			conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
 		} catch (SQLException e1) {
 			logger.debug("getEmployee Connection Exception() is executed!" + e1.getMessage());
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
             logger.debug("getEmployee() DBUtils.findEmployee Exception is executed!" + errorString);
         }
  
         if (errorString != null && employee == null) {
             try {
				response.sendRedirect(request.getServletPath() + "/employee");
			} catch (IOException e) {
				logger.debug("getEmployee() Send Request Exception is executed!" +e.getMessage());
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
    
    public EmployeeVO updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	logger.debug("updateEmployee() is executed!");
    	 Connection conn = null;
  		try {
  			conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
  		} catch (SQLException e1) {
  			logger.debug("updateEmployee()Connection Exception is executed! " + e1.getMessage());
  			e1.printStackTrace();
  		}
    	 
        int securitycode = Integer.parseInt((String) request.getParameter("securitycode"));
        String FirstName = (String) request.getParameter("firstname");
        String LastName = (String) request.getParameter("lastname");
        int Phone = Integer.parseInt((String) request.getParameter("phone"));
        String Country = (String) request.getParameter("country");
        String Address = (String) request.getParameter("address");

        EmployeeVO vo1 = new EmployeeVO();
        vo1.setSecuritycode(securitycode);
        vo1.setFirstName(FirstName);
        vo1.setLastName(LastName);
        vo1.setPhone(Phone);
        vo1.setCountry(Country);
        vo1.setAddress(Address);
  
        String errorString = null;
  
        try {
            DBUtils.updateEmployee(conn, vo1);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
            logger.debug("updateEmployee() DBUtils.updateEmployee Exception is executed! " + errorString);
        } catch (ParseException e) {
 		// TODO Auto-generated catch block
        	logger.debug("updateEmployee() ParseException is executed! " + e.getMessage());
 		 return null;
 	}
  
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("NewEmployee", vo1);
  
  
        // If error, forward to Edit page.
        if (errorString != null) {
        	ServletContext context = request.getSession().getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
            dispatcher.forward(request, response);
        }
         
        // If everything nice.
        // Redirect to the product listing page.            
        else {
            response.sendRedirect(request.getContextPath() + "/employee");
        }
        
        return vo1;
  
	        
	     }
    	

}