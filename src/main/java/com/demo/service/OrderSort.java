package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.demo.dao.OrderDAO;
import com.demo.model.OrderVO;
 
@Service
public class OrderSort extends HttpServlet implements OrderSortInt {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    OrderDAO dao;
    

    
    public List<OrderVO> sort() throws SQLException, ServletException, IOException
    {
     return dao.sort();
    }


}