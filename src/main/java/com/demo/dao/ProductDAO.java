package com.demo.dao;
import java.util.List;

import com.demo.model.ProductVO;
 
public interface ProductDAO 
{
    public List<ProductVO> getAllProducts();
}