package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.demo.dao.ProductDAO;
import com.demo.model.ProductVO;
 
@Service
public class ProductChange extends HttpServlet implements ProductChangeInt {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    ProductDAO dao;
    

    
    public ProductVO getProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
     return dao.getProduct(request, response);
    }





}