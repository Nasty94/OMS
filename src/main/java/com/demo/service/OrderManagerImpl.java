package com.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.OrderDAO;
import com.demo.model.OrderVO;

public class OrderManagerImpl implements OrderManager {
	
	 @Autowired
	    OrderDAO dao1;
	     
	    public List<OrderVO> getAllOrders() throws SQLException 
	    {
	        return dao1.getAllOrders();
	    }


}
