package com.demo.edit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.demo.model.EmployeeVO;
import com.demo.h2.DBUtils;
import com.demo.h2.MyUtils;
 
@WebServlet(urlPatterns = { "/doEditEmployee" })
public class DoEditEmployeeServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public DoEditEmployeeServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       Connection conn = MyUtils.getStoredConnection(request);
 
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
       } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
       // Store infomation to request attribute, before forward to views.
       request.setAttribute("errorString", errorString);
       request.setAttribute("employee", vo1);
 
 
       // If error, forward to Edit page.
       if (errorString != null) {
           RequestDispatcher dispatcher = request.getServletContext()
                   .getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
           dispatcher.forward(request, response);
       }
        
       // If everything nice.
       // Redirect to the product listing page.            
       else {
           response.sendRedirect(request.getContextPath() + "/employee");
       }
 
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}