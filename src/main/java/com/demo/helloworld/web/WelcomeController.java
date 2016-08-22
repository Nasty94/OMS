package com.demo.helloworld.web;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.helloworld.service.HelloWorldService;
import com.demo.service.EmployeeManager;
import com.demo.service.EmployeeManagerImpl;
import com.demo.service.ProductManager;

@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
		
	public WelcomeController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("index() is executed!");

		model.put("title", helloWorldService.getTitle(""));
		model.put("msg", helloWorldService.getDesc());
		
		return "index";
	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		logger.debug("hello() is executed - $name {}", name);

		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		
		model.addObject("title", helloWorldService.getTitle(name));
		model.addObject("msg", helloWorldService.getDesc());
		
		return model;

	}
	
	    @Resource(name="employeeManagerImpl")
	    EmployeeManager manager;
	 
	    @RequestMapping(value = "/employee",method = RequestMethod.GET)
	    public String getAllEmployees(Model model) throws SQLException
	    
	    
	    {
	    	
	    logger.debug("index() is executed!");

	        model.addAttribute("employees", manager.getAllEmployees());
	        return "index";
	    }
	    
	    @Resource(name="productManagerImpl")
	    ProductManager manager1;
	 
	    @RequestMapping(value = "/product",method = RequestMethod.GET)
	    public String getAllProducts(Model model) throws SQLException
	    
	    
	    {
	    	
	    logger.debug("index() is executed!");

		
	        model.addAttribute("products", manager1.getAllProducts());
	        return "index";
	    }

}