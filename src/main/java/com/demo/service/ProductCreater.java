package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.ProductVO;
 
public interface ProductCreater 
{
    public ProductVO insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;
}