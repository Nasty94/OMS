package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.demo.dao.OrderDAO;
import com.demo.model.OrderVO;
 
@Service
public class OrderGetImpl extends HttpServlet implements OrderGet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    OrderDAO dao;
    

    
    public OrderVO getOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
     return dao.getOrder(request, response);
    }





}