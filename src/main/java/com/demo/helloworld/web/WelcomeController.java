package com.demo.helloworld.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.demo.edit.DoEditEmployeeServlet;
import com.demo.helloworld.service.HelloWorldService;
import com.demo.service.EmployeeChange;
import com.demo.service.EmployeeCreater;
import com.demo.service.EmployeeUpdate;
import com.demo.service.OrderCreater;
import com.demo.service.OrderGetImpl;
import com.demo.service.OrderManager;
import com.demo.service.OrderSort;
import com.demo.service.ProductChange;
import com.demo.service.ProductCreater;
import com.demo.service.EmployeeManager;
import com.demo.service.EmployeeManagerImpl;
import com.demo.service.ProductManager;
import com.demo.service.ProductUpdate;
import com.demo.edit.EditEmployeeServlet;

@Controller
public class WelcomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	    	
	    logger.debug("client() is executed!");

	        model.addAttribute("employees", manager.getAllEmployees());
	        return "index";
	    }
	    
	    @Resource(name="employeeChange")
	    EmployeeChange change;
	    
	    @RequestMapping(value = "editEmployee",method = {RequestMethod.POST, RequestMethod.GET})
	    public String getEmployee(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("change() is executed!");

	        model.addAttribute("employee", change.getEmployee(request, response));
	        return "index";
	    }
	    
	    @Resource(name="employeeUpdate")
	    EmployeeUpdate update;
	    
	    @RequestMapping(value = "doEditEmployee",method = {RequestMethod.POST, RequestMethod.GET})
	    public String updateEmployee(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("update() is executed!");

	        model.addAttribute("NewEmployee", update.updateEmployee(request, response));
	        return "index";
	    }
	 

	    
	    @Resource(name="productManagerImpl")
	    ProductManager manager1;
	 
	    @RequestMapping(value = "/product",method = RequestMethod.GET)
	    public String getAllProducts(Model model) throws SQLException
	    
	    
	    {
	    	
	    logger.debug("product() is executed!");

		
	        model.addAttribute("products", manager1.getAllProducts());
	        return "index";
	    }
	    
	    @Resource(name="productChange")
	    ProductChange changeP;
	    
	    @RequestMapping(value = "editProduct",method = {RequestMethod.POST, RequestMethod.GET})
	    public String getProduct (Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("changeP() is executed!");

	        model.addAttribute("product", changeP.getProduct(request, response));
	        return "index";
	    }
	    
	    @Resource(name="productUpdate")
	    ProductUpdate updateP;
	    
	    @RequestMapping(value = "doEditProduct",method = {RequestMethod.POST, RequestMethod.GET})
	    public String updateProduct(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("updateP() is executed!");

	        model.addAttribute("NewProduct", updateP.updateProduct(request, response));
	        return "index";
	    }
	    
	    @Resource(name="orderManagerImpl")
	    OrderManager manager2;
	 
	    @RequestMapping(value = "/order",method = RequestMethod.GET)
	    public String getAllOrders(Model model) throws SQLException
	    
	    
	    {
	    	
	    logger.debug("order() is executed!");

	        model.addAttribute("orders", manager2.getAllOrders());
	        return "index";
	    }
	    
	    @Resource(name="orderGetImpl")
	    OrderGetImpl getO;
	    
	    @RequestMapping(value = "createOrder",method = {RequestMethod.POST, RequestMethod.GET})
	    public String getOrder (Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("changeP() is executed!");

	        model.addAttribute("order", getO.getOrder(request, response));
	        return "index";
	    }
	    
	    @Resource(name="orderCreaterImpl")
	    OrderCreater creater;
	 
	    @RequestMapping(value = "doCreateOrder",method = {RequestMethod.POST, RequestMethod.GET})
	    public String insertOrder(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ScriptException
	    
	    
	    {
	    	
	    logger.debug("orderInsert() is executed!");

	        model.addAttribute("NewOrder", creater.insertOrder(request, response));
	        return "index";
	    }
	    
	    @Resource(name="productCreaterImpl")
	    ProductCreater createrP;
	 
	    @RequestMapping(value = "doCreateProduct",method = {RequestMethod.POST, RequestMethod.GET})
	    public String insertProduct(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("productInsert() is executed!");

	        model.addAttribute("NewProduct", createrP.insertProduct(request, response));
	        return "index";
	    }
	    
	    @Resource(name="employeeCreaterImpl")
	    EmployeeCreater createrE;
	    
	    @RequestMapping(value = "doCreateEmployee",method = {RequestMethod.POST, RequestMethod.GET})
	    public String insertEmployee(Model model, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("employeeInsert() is executed!");

	        model.addAttribute("NewEmployee", createrE.insertEmployee(request, response));
	        return "index";
	    }
	    
	    @Resource(name="orderSort")
	    OrderSort sort;
	    
	    @RequestMapping(value = "sort",method = {RequestMethod.POST, RequestMethod.GET})
	    public String sort(Model model) throws SQLException, ServletException, IOException
	    
	    
	    {
	    	
	    logger.debug("/sort() is executed!");

	        model.addAttribute("sort", sort.sort());
	        return "index";
	    }
	    
	 
	    

}