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
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;

import org.springframework.stereotype.Repository;

import com.demo.h2.DBUtils;
import com.demo.model.CountryVO;
import com.demo.model.EmployeeVO;
import com.demo.model.ProductVO;
 
@Repository
public class EmployeeDAOImpl  extends HttpServlet implements EmployeeDAO{
	////////////
	private final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
   
	 public EmployeeDAOImpl() {
	        super();
	    }
	 
	 private List<CountryVO> getAllCountries() throws SQLException
	 
	 {
		 
		 logger.debug("getAllCountries() is executed!");
	        String query =
	        		"SELECT * FROM COUNTRY";
	    
	    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
		     Statement s=connection.createStatement();
		        
		     //s.execute("SELECT * FROM CLIENT");
		     ResultSet rs = s.executeQuery(query);
		     List<CountryVO> countries = new ArrayList<CountryVO>();
		     
		     while(rs.next()) {
		    	
		    	 String name = rs.getString("NAME");
		    	 String currency = rs.getString("CURRENCY");
		    			    	 
		    	 CountryVO vo1 = new CountryVO();
		       
		         vo1.setName(name);
		         vo1.setCurrency(currency);
		       
		         countries.add(vo1);
		     }
	    	

	         
	        return countries;
		 
	 }
	 
    public List<EmployeeVO> getAllEmployees() throws SQLException 
   
    {
    	logger.debug("getAllEmployees() is executed!");
        String query =
        		"SELECT * FROM CLIENT";
    
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
	     Statement s=connection.createStatement();
	     
	     String create = "create table client(securitycode int, firstname varchar(255), lastname varchar(255), phone int, country varchar(255), address varchar(255))";
	 	TableCheck(connection, "CLIENT", create);
	 	
	 	 String create1 = "create table product(barcode int, name varchar(255), price int, description varchar(255), date varchar(255))";
 		 TableCheck(connection, "PRODUCT", create1);
 		 
 		 
    	 String create2 = "create table orders(ordernr int, convprice int, trandate varchar(255), barcode int, client int)";
         TableCheck(connection, "ORDERS", create2);
         
         String create3 = "create table country(name varchar(255), currency varchar(255))";
         TableCheck(connection, "COUNTRY", create3);
	        
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
 			conn = DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
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
  			conn = DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
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
        
        List<CountryVO> countries = getAllCountries();
        
       // if(countries.contains(vo1.getCountry())) {
  
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
       // }
  
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

	@Override
	public EmployeeVO insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		logger.debug("insertEmployee() is executed!");
	   	 Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
			} catch (SQLException e1) {
				logger.debug("InsertEmployee Connection Exception() is executed!" + e1.getMessage());
				e1.printStackTrace();
			}
			
			 // Create a Writer to write the response mess
			 PrintWriter out = response.getWriter();
		
		 int securitycode = Integer.parseInt((String) request.getParameter("securitycode"));
	     String firstname = (String) request.getParameter("firstname");
	     String lastname = (String) request.getParameter("lastname");
	     int phone = Integer.parseInt((String)request.getParameter("phone"));
	     String country = (String) request.getParameter("country");
	     String address = (String) request.getParameter("address");

	     String errorString = null;

	     EmployeeVO vo1 = new EmployeeVO();
	     vo1.setSecuritycode(securitycode);
	     vo1.setFirstName(firstname);
	     vo1.setLastName(lastname);
	     vo1.setPhone(phone);
	     vo1.setCountry(country);
	     vo1.setAddress(address);
	     
	     // Product ID is the string literal [a-zA-Z_0-9]
	     // with at least 1 character
	     //String regex = "\\w+";
	     
	     
         Statement validate = conn.createStatement();
         Statement validate1 = conn.createStatement();
         ResultSet vresult =  validate.executeQuery("SELECT * FROM COUNTRY WHERE NAME LIKE " + "'"+country+"'");
         ResultSet vresult1 = validate1.executeQuery("SELECT * FROM CLIENT WHERE SECURITYCODE="+securitycode);
         if (!vresult.next()){
       	  
       	  errorString = "Sorry! This country is not supported.";
       	  //System.out.println(errorString);
       	  
       	  logger.debug(errorString);
       	  
       	// Set the MIME type for the response message
             response.setContentType("text/html");
            
         
             // The programming logic to produce a HTML page
             out.println("<p>" + errorString + "</p>");
            /* ScriptEngineManager factory = new ScriptEngineManager();
             ScriptEngine engine = factory.getEngineByName("JavaScript");
             engine.eval("window.alert("+errorString+")");*/
             
             response.sendRedirect(request.getContextPath() + "/employee");
       	  
         }
         else if (vresult1.next()){
        	 errorString = "Sorry! This securitycode is already assigned to another client " + vo1.getFirstName() + " " + vo1.getLastName();
          	  //System.out.println(errorString);
          	  
          	  logger.debug(errorString);
          	  
          	// Set the MIME type for the response message
                response.setContentType("text/html");
               
            
                // The programming logic to produce a HTML page
                out.println("<p>" + errorString + "</p>");
               /* ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("JavaScript");
                engine.eval("window.alert("+errorString+")");*/
                
                response.sendRedirect(request.getContextPath() + "/employee");
          	  
            }
         else {

	     try {
	         DBUtils.insertEmployee(conn, vo1);
	     } catch (SQLException e) {
	         e.printStackTrace();
	         errorString = e.getMessage();
	         logger.debug("insertEmployee() DBUtils.insertEmployee Exception is executed! " + errorString);
	     }
         }
	      
	     // Store infomation to request attribute, before forward to views.
	     request.setAttribute("errorString", errorString);
	     request.setAttribute("NewEmployee", vo1);

	     // If error, forward to Edit page.
	     if (errorString != null) {
	   	  ServletContext context = request.getSession().getServletContext();
	         RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/index.jsp");
	         dispatcher.forward(request, response);
	     }

	     // If everything nice.
	     // Redirect to the product listing page.            
	     else {
	         response.sendRedirect(request.getContextPath() + "/employee");
	     }
	     return vo1;
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