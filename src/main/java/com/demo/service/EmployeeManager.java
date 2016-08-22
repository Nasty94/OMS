package com.demo.service;

import java.sql.SQLException;
import java.util.List;

import com.demo.model.EmployeeVO;
 
public interface EmployeeManager 
{
    public List<EmployeeVO> getAllEmployees() throws SQLException;
}