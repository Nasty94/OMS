package com.demo.edit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.demo.model.ProductVO;
import com.demo.h2.DBUtils;
import com.demo.h2.MyUtils;
 
@WebServlet(urlPatterns = { "/doEditProduct" })
public class DoEditProductServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public DoEditProductServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       Connection conn = MyUtils.getStoredConnection(request);
 
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
          // logger.debug("updateProduct() DBUtils.updateProduct Exception is executed! " + errorString);
       } catch (ParseException e) {
		// TODO Auto-generated catch block
       //	logger.debug("updateProduct() ParseException is executed! " + e.getMessage());
		
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
       
 
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}