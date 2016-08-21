package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.ProductDAO;
import com.demo.model.ProductVO;

public class ProductManagerImpl implements ProductManager {
	
	 @Autowired
	    ProductDAO dao1;
	     
	    public List<ProductVO> getAllProducts() 
	    {
	        return dao1.getAllProducts();
	    }


}
