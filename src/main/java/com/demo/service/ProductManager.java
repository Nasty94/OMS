package com.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.demo.model.ProductVO;
 
public interface ProductManager 
{
    public List<ProductVO> getAllProducts() throws SQLException;
}