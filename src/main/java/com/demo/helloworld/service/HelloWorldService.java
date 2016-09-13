package com.demo.helloworld.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.demo.dao.EmployeeDAO;
import com.demo.dao.ProductDAO;
import com.demo.model.EmployeeVO;
import com.demo.model.ProductVO;
import com.demo.service.EmployeeManager;
import com.demo.service.ProductManager;

@Service
public class HelloWorldService{

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

	public String getDesc() {

		logger.debug("getDesc() is executed!");

		return "Order Management System Demo";

	}

	public String getTitle(String name) {

		logger.debug("getTitle() is executed! $name : {}", name);

		if(StringUtils.isEmpty(name)){
			return "Hello!";
		}else{
			return "Hello " + name;
		}
		
	}
	
	 
	  
}