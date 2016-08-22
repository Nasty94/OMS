package com.demo.dao;
import java.sql.SQLException;
import java.util.List;

import com.demo.model.EmployeeVO;
 
public interface EmployeeDAO 
{
    public List<EmployeeVO> getAllEmployees() throws SQLException;
}