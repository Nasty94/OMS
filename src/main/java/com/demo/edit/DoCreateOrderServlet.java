package com.demo.edit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.demo.model.OrderVO;
import com.demo.h2.DBUtils;
import com.demo.h2.MyUtils;

 
@WebServlet(urlPatterns = { "/doCreateOrder" })
public class DoCreateOrderServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public DoCreateOrderServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       Connection conn = MyUtils.getStoredConnection(request);
 
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
       String regex = "\\w+";
 
      
 
       if (errorString == null) {
           try {
               DBUtils.insertOrder(conn, vo1);
           } catch (SQLException e) {
               e.printStackTrace();
               errorString = e.getMessage();
           }
       }
        
       // Store infomation to request attribute, before forward to views.
       request.setAttribute("errorString", errorString);
       request.setAttribute("order", vo1);
 
       // If error, forward to Edit page.
       if (errorString != null) {
           RequestDispatcher dispatcher = request.getServletContext()
                   .getRequestDispatcher("/WEB-INF/views/index.jsp");
           dispatcher.forward(request, response);
       }
 
       // If everything nice.
       // Redirect to the product listing page.            
       else {
           response.sendRedirect(request.getContextPath() + "/order");
       }
 
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}