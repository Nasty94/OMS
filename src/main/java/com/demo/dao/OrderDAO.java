package com.demo.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.demo.model.OrderVO;
 
public interface OrderDAO 
{
    public List<OrderVO> getAllOrders() throws SQLException;
    

}