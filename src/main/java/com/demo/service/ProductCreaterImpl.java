package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.ProductDAO;
import com.demo.model.ProductVO;

public class ProductCreaterImpl implements ProductCreater {
	
	 @Autowired
	    ProductDAO dao1;
	     
	    public ProductVO insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
	    {
	        return dao1.insertProduct(request, response);
	    }

		

		

}
