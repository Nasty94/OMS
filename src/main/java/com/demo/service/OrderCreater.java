package com.demo.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.OrderVO;
 
public interface OrderCreater 
{
    public OrderVO insertOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ScriptException;
}