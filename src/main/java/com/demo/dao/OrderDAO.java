package com.demo.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.demo.model.OrderVO;
import com.demo.model.ProductVO;
 
public interface OrderDAO 
{
    public List<OrderVO> getAllOrders() throws SQLException;
    
    public OrderVO insertOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ScriptException;

	public OrderVO getOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;

	public List<OrderVO> sort() throws SQLException;
    

}