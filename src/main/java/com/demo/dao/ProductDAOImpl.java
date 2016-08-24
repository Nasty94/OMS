package com.demo.dao;

import java.io.IOException;
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
 
@Repository
public class ProductDAOImpl implements ProductDAO {
	
	private final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
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
 	        
 	    // s.execute("SELECT * FROM PRODUCT");
 	     ResultSet rs = s.executeQuery(query);
 	     List<ProductVO> products = new ArrayList<ProductVO>();
 	     
 	     while(rs.next()) {
 	    	 int BarCode = rs.getInt("BARCODE");
 	    	 String Name = rs.getString("NAME");
 	    	 int Price = rs.getInt("PRICE");
 	    	 String Description = rs.getString("DESCRIPTION");
 	    	 
 	    	//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
 	    	//String textDate = df.format(rs.getString("DATE"));  
 	    	 String textDate = rs.getString("DATE");
 	    	
 	    	 
 	    	 ProductVO vo1 = new ProductVO();
 	         vo1.setBarcode(BarCode);
 	         vo1.setName(Name);
 	         vo1.setPrice(Price);
 	         vo1.setDescription(Description);
 	         vo1.setDate(textDate);
 	         products.add(vo1);
 	     }

         return products;
     }
    
 public ProductVO getProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	logger.debug("getProduct() is executed!");
    	 Connection conn = null;
 		try {
 			conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
 		} catch (SQLException e1) {
 			logger.debug("getProduct Connection Exception() is executed!" + e1.getMessage());
 			e1.printStackTrace();
 		}
  
         String barcode = (String) request.getParameter("barcode");
  
         ProductVO product = null;
  
         String errorString = null;
  
         try {
             product = DBUtils.findProduct(conn, Integer.parseInt(barcode));
         } catch (SQLException e) {
             e.printStackTrace();
             errorString = e.getMessage();
             logger.debug("getProduct() DBUtils.findProduct Exception is executed!" + errorString);
         }
  
         if (errorString != null && product == null) {
             try {
				response.sendRedirect(request.getServletPath() + "/product");
			} catch (IOException e) {
				logger.debug("getProduct() Send Request Exception is executed!" +e.getMessage());
				e.printStackTrace();
			}
             return null;
         }
  
         // Store errorString in request attribute, before forward to views.
         request.setAttribute("errorString", errorString);
         request.setAttribute("product", product);
         ServletContext context = request.getSession().getServletContext();

         RequestDispatcher dispatcher = context
                 .getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
         dispatcher.forward(request, response);
         
         return product;
	         
	        
	     }
    
    public ProductVO updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    
    {
    	logger.debug("updateProduct() is executed!");
    	 Connection conn = null;
  		try {
  			conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
  		} catch (SQLException e1) {
  			logger.debug("updateProduct()Connection Exception is executed! " + e1.getMessage());
  			e1.printStackTrace();
  		}
    	 
        int barcode = Integer.parseInt((String) request.getParameter("barcode"));
        String name = (String) request.getParameter("name");
        int price = Integer.parseInt((String) request.getParameter("price"));
        String description = (String) request.getParameter("desription");
        String date = (String) request.getParameter("date");

        ProductVO vo1 = new ProductVO();
        vo1.setBarcode(barcode);
        vo1.setName(name);
        vo1.setPrice(price);
        vo1.setDescription(description);
        vo1.setDate(date);
  
        String errorString = null;
  
        try {
            DBUtils.updateProduct(conn, vo1);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
            logger.debug("updateProduct() DBUtils.updateProduct Exception is executed! " + errorString);
        } catch (ParseException e) {
 		// TODO Auto-generated catch block
        	logger.debug("updateProduct() ParseException is executed! " + e.getMessage());
 		 return null;
 	}
  
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("NewProduct", vo1);
  
  
        // If error, forward to Edit page.
        if (errorString != null) {
        	ServletContext context = request.getSession().getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
            dispatcher.forward(request, response);
        }
         
        // If everything nice.
        // Redirect to the product listing page.            
        else {
            response.sendRedirect(request.getContextPath() + "/product");
        }
        
        return vo1;
  
	        
	     }

	@Override
	public ProductVO insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("insertProduct() is executed!");
   	 Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:h2:~/Documents/GitHub/OMS/src/main/oms", "sa", "");
		} catch (SQLException e1) {
			logger.debug("InsertProduct Connection Exception() is executed!" + e1.getMessage());
			e1.printStackTrace();
		}
		
	 
	
	 int barcode = Integer.parseInt((String) request.getParameter("barcode"));
     String name = (String) request.getParameter("name");
     int price = Integer.parseInt((String)request.getParameter("price"));
     String date = (String) request.getParameter("date");
     String description = (String) request.getParameter("description");

     String errorString = null;

     ProductVO vo1 = new ProductVO();
     vo1.setBarcode(barcode);
     vo1.setName(name);
     vo1.setPrice(price);
     vo1.setDescription(description);
     vo1.setDate(date);
     // Product ID is the string literal [a-zA-Z_0-9]
     // with at least 1 character
     //String regex = "\\w+";

     try {
         DBUtils.insertProduct(conn, vo1);
     } catch (SQLException e) {
         e.printStackTrace();
         errorString = e.getMessage();
         logger.debug("insertProduct() DBUtils.insertOrder Exception is executed! " + errorString);
     }
      
     // Store infomation to request attribute, before forward to views.
     request.setAttribute("errorString", errorString);
     request.setAttribute("NewProduct", vo1);

     // If error, forward to Edit page.
     if (errorString != null) {
   	  ServletContext context = request.getSession().getServletContext();
         RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/views/index.jsp");
         dispatcher.forward(request, response);
     }

     // If everything nice.
     // Redirect to the product listing page.            
     else {
         response.sendRedirect(request.getContextPath() + "/product");
     }
     return vo1;
	}
	
}
    	


