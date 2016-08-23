package com.demo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.h2.DBUtils;
import com.demo.model.CountryVO;
import com.demo.model.EmployeeVO;
import com.demo.model.OrderVO;
import com.demo.model.ProductVO;
import com.demo.rates.ExchangeRate;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	private final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	/*
	 private static final String DBNAME = "oms";

		
	 private static final String DB_DRIVER = "org.h2.Driver";
	 //  private static final String DB_CONNECTION = //"jdbc:h2:mem~/Documents/GitHub/OMS/src/main/oms";
	    private static final String DB_USER = "sa";
	    private static final String DB_PASSWORD = "sa";*/
	
	private double pickRate(String Currency) {
		 ExchangeRate exchange = new ExchangeRate();
		 
		 if (Currency=="USD") {
    	  double rateUsd = exchange.getEuroUsdRate();
    	  return rateUsd;
		 } else if (Currency=="UK") {
    	  double rateUk = exchange.getEuroUkRate();
    	  return rateUk;
		 }else if (Currency=="EUR") {
    	  double rateEur = exchange.getEuroEurRate();
    	  return rateEur;
		 }else if (Currency=="RUB") {
    	  double rateRub = exchange.getEuroRubRate();
    	  return rateRub;
		 }
		 
    	  double rateJpy = exchange.getEuroJpyRate();
    	  return rateJpy;
		  
	}
	
	 
	 private List<CountryVO> getAllCountries() throws SQLException
	 
	 {
		 
		 logger.debug("getAllCountries() is executed!");
	        String query =
	        		"SELECT * FROM COUNTRY";
	    
	    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
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
	 
	 private EmployeeVO findEmployee(int securitycode) throws SQLException {
	      String sql = "Select a.SecurityCode, a.FirstName, a.LastName, a.Phone, a.Country, a.Address from Client a where a.SecurityCode=" +securitycode;
	      Connection conn= DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
	      PreparedStatement pstm = conn.prepareStatement(sql);
	      	 
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
 
    public List<OrderVO> getAllOrders() throws SQLException 
    {
    	String query =
         		"SELECT a.ORDERNR, a.CONVPRICE, a.TRANDATE, a.BARCODE, a.CLIENT, "
         		+ "b.NAME, b.PRICE, b.DESCRIPTION,  b.DATE "
         		+ "FROM ORDERS AS a JOIN PRODUCT AS b "
         		+ "ON a.BARCODE = b.BARCODE";
    	
    	
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
     	 
 	     Statement s=connection.createStatement();
 	        
 	    // s.execute("SELECT * FROM PRODUCT");
 	     ResultSet rs = s.executeQuery(query);
 	    
 	     List<OrderVO> orders = new ArrayList<OrderVO>();
 	     
 	     while(rs.next()) {
 	    	 int ordernr = rs.getInt("ORDERNR");
 	    	 int eurprice = rs.getInt("PRICE");
 	    	 String trandate = rs.getString("TRANDATE");
 	    	 int barcode = rs.getInt("BARCODE");
 	    	 int client = rs.getInt("CLIENT");
 	    	 String name = rs.getString("NAME");
 	    	 int convprice = rs.getInt("CONVPRICE");
 	    	 String description = rs.getString("DESCRIPTION");
 	    	 String date = rs.getString("DATE");

 	    	 
 	    	 OrderVO vo1 = new OrderVO();
 	         vo1.setOrdernr(ordernr);
 	         vo1.setPrice(eurprice);
 	         vo1.setTrandate(trandate);
 	         vo1.setBarcode(barcode);
 	         vo1.setClient(client);
 	         vo1.setName(name);
 	         vo1.setConvprice(convprice);
 	         vo1.setDescription(description);
 	         vo1.setDate(date);
 	         orders.add(vo1);
 	     }

         return orders;
     }
    
public OrderVO getOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	logger.debug("getOrder() is executed!");
    	 Connection conn = null;
 		try {
 			conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
 		} catch (SQLException e1) {
 			logger.debug("getOrder Connection Exception() is executed!" + e1.getMessage());
 			e1.printStackTrace();
 		}
  
         String ordernr = (String) request.getParameter("ordernr");
  
         OrderVO order = null;
  
         String errorString = null;
  
         order = DBUtils.findOrder(conn, Integer.parseInt(ordernr));
  
         
         // Store errorString in request attribute, before forward to views.
         request.setAttribute("errorString", errorString);
         request.setAttribute("order", order);
         ServletContext context = request.getSession().getServletContext();

         RequestDispatcher dispatcher = context
                 .getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
         dispatcher.forward(request, response);
         
         return order;
	         
	        
	     }
    
    public OrderVO insertOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	  
    	{
        	logger.debug("insertOrder() is executed!");
        	 Connection conn = null;
     		try {
     			conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
     		} catch (SQLException e1) {
     			logger.debug("InsertOrder Connection Exception() is executed!" + e1.getMessage());
     			e1.printStackTrace();
     		}
     		
     	 
    	
    	  int ordernr = Integer.parseInt((String) request.getParameter("ordernr"));
          int eurprice = Integer.parseInt((String) request.getParameter("price"));
          String trandate = (String) request.getParameter("trandate");
          int barcode = Integer.parseInt((String)request.getParameter("barcode"));
          int client = Integer.parseInt((String) request.getParameter("client"));
          String currency = null;
          
          List<CountryVO> countries = getAllCountries();
          for (CountryVO v: countries){
        	  if (v.getName()== findEmployee(client).getCountry()) {
        		  currency = v.getCurrency();
        	  }
          }
          
          double rate = pickRate(currency);
          OrderVO vo1 = new OrderVO();
          vo1.setOrdernr(ordernr);
          
          double convert = eurprice * rate;
          int converted = (int) convert;
          
          vo1.setConvprice(converted);
          vo1.setTrandate(trandate);
          vo1.setBarcode(barcode);
          vo1.setClient(client);
          String errorString = null;
    
         
          // Product ID is the string literal [a-zA-Z_0-9]
          // with at least 1 character
          //String regex = "\\w+";
  
          try {
              DBUtils.insertOrder(conn, vo1);
          } catch (SQLException e) {
              e.printStackTrace();
              errorString = e.getMessage();
              logger.debug("insertOrder() DBUtils.insertOrder Exception is executed! " + errorString);
          }
           
          // Store infomation to request attribute, before forward to views.
          request.setAttribute("errorString", errorString);
          request.setAttribute("NewOrder", vo1);
    
          // If error, forward to Edit page.
          if (errorString != null) {
        	  ServletContext context = request.getSession().getServletContext();
              RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/index.jsp");
              dispatcher.forward(request, response);
          }
    
          // If everything nice.
          // Redirect to the product listing page.            
          else {
              response.sendRedirect(request.getContextPath() + "/order");
          }
          return vo1;
    	}
    
    
    }
}


