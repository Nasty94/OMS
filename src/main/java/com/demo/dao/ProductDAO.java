package com.demo.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.EmployeeVO;
import com.demo.model.ProductVO;
 
public interface ProductDAO 
{
    public List<ProductVO> getAllProducts() throws SQLException;
    
 public ProductVO getProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;
    
    public ProductVO updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;

	public ProductVO insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}