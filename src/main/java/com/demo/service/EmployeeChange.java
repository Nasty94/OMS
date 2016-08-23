package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.demo.dao.EmployeeDAO;
import com.demo.model.EmployeeVO;
 
@Service
public class EmployeeChange extends HttpServlet implements EmployeeChangeInt {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    EmployeeDAO dao;
    

    
    public EmployeeVO getEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
     return dao.getEmployee(request, response);
    }


}