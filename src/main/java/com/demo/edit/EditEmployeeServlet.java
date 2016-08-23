package com.demo.edit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.h2.DBUtils;
import com.demo.model.EmployeeVO;
import com.demo.model.ProductVO;
 
 
@WebServlet(urlPatterns = { "/editEmployee" })
public class EditEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditEmployeeServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
 
         
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && employee == null) {
            response.sendRedirect(request.getServletPath() + "/employee");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("employee", employee);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/jsp/index.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
