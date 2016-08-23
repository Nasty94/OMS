package com.demo.dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.EmployeeVO;
 
public interface EmployeeDAO 
{
    public List<EmployeeVO> getAllEmployees() throws SQLException;
    
    public EmployeeVO getEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;
    
    public EmployeeVO updateEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException;
}