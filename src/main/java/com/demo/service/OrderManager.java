package com.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.demo.model.OrderVO;
 
public interface OrderManager 
{
    public List<OrderVO> getAllOrders() throws SQLException;
}