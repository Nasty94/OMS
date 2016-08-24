package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.EmployeeDAO;
import com.demo.model.EmployeeVO;

public class EmployeeCreaterImpl implements EmployeeCreater {
	
	 @Autowired
	    EmployeeDAO dao1;
	     
	    public EmployeeVO insertEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
	    {
	        return dao1.insertEmployee(request, response);
	    }


		

}
