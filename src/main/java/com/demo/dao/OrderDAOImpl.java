package com.demo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import com.demo.model.OrderVO;
import com.demo.model.ProductVO;

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
         		"SELECT a.ORDERNR, a.CONVPRICE, a.TRANDATE, a.BARCODE, "
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
 	    	 int convprice = rs.getInt("CONVPRICE");
 	    	 String trandate = rs.getString("TRANDATE");
 	    	 int barcode = rs.getInt("BARCODE");
 	    	 String name = rs.getString("NAME");
 	    	 int price = rs.getInt("PRICE");
 	    	 String description = rs.getString("DESCRIPTION");
 	    	 String date = rs.getString("DATE");

 	    	 
 	    	 OrderVO vo1 = new OrderVO();
 	         vo1.setOrdernr(ordernr);
 	         vo1.setConvprice(convprice);
 	         vo1.setTrandate(trandate);
 	         vo1.setBarcode(barcode);
 	         vo1.setName(name);
 	         vo1.setPrice(price);
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
          int convprice = Integer.parseInt((String) request.getParameter("convprice"));
          String trandate = (String) request.getParameter("trandate");
          int barcode = Integer.parseInt((String)request.getParameter("barcode"));
          OrderVO vo1 = new OrderVO();
          vo1.setOrdernr(ordernr);
          vo1.setConvprice(convprice);
          vo1.setTrandate(trandate);
          vo1.setBarcode(barcode);
    
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


