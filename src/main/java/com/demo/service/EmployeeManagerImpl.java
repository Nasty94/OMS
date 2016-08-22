package com.demo.service;

import java.sql.SQLException;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.demo.dao.EmployeeDAO;
import com.demo.model.EmployeeVO;
 
@Service
public class EmployeeManagerImpl implements EmployeeManager {
 
    @Autowired
    EmployeeDAO dao;
     
    public List<EmployeeVO> getAllEmployees() throws SQLException 
    {
        return dao.getAllEmployees();
    }
}