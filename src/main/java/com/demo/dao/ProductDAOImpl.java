package com.demo.dao;

import java.io.IOException;
import java.io.PrintWriter;
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
    	 Connection connection= DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
     	 //~/Documents/GitHub/OMS/src/main/oms
 	     Statement s=connection.createStatement();
 	     
 	    String create = "create table product(barcode int, name varchar(255), price int, description varchar(255), date varchar(255))";
 		 TableCheck(connection, "PRODUCT", create);
 		 
 		 
    	 String create1 = "create table orders(ordernr int, convprice int, trandate varchar(255), barcode int, client int)";
         TableCheck(connection, "ORDERS", create1);
         
         String create2 = "create table client(securitycode int, firstname varchar(255), lastname varchar(255), phone int, country varchar(255), address varchar(255))";
		 	TableCheck(connection, "CLIENT", create2);
  	      
	         String create3 = "create table country(name varchar(255), currency varchar(255))";
	         TableCheck(connection, "COUNTRY", create3);
 	        
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
 			conn = DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
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
  			conn = DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
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
	public ProductVO insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		logger.debug("insertProduct() is executed!");
   	 Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:h2:~/oms", "sa", "");
		} catch (SQLException e1) {
			logger.debug("InsertProduct Connection Exception() is executed!" + e1.getMessage());
			e1.printStackTrace();
		}
		
		 // Create a Writer to write the response mess
		 PrintWriter out = response.getWriter();
	
	
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
     
     Statement validate = conn.createStatement();
     ResultSet vresult =  validate.executeQuery("SELECT * FROM PRODUCT WHERE BARCODE=" + barcode);
     if (vresult.next()){
   	  
   	  errorString = "Sorry! This barcode is already assigned to another product " + vo1.getName();
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
         DBUtils.insertProduct(conn, vo1);
     } catch (SQLException e) {
         e.printStackTrace();
         errorString = e.getMessage();
         logger.debug("insertProduct() DBUtils.insertOrder Exception is executed! " + errorString);
     }
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
    	


