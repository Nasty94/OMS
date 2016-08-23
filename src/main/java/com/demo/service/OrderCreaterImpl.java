package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.OrderDAO;
import com.demo.model.OrderVO;

public class OrderCreaterImpl implements OrderCreater {
	
	 @Autowired
	    OrderDAO dao1;
	     
	    public OrderVO insertOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
	    {
	        return dao1.insertOrder(request, response);
	    }

		

}
