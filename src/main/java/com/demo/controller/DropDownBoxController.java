/*package com.demo.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.demo.model.EmployeeVO;

public class DropDownBoxController extends SimpleFormController{

	public DropDownBoxController(){
		setCommandClass(EmployeeVO.class);
		setCommandName("employeeForm");
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
		throws Exception {

		EmployeeVO cust = new EmployeeVO();
		
		cust.setCountry("US");

		return cust;

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
		HttpServletResponse response, Object command, BindException errors)
		throws Exception {

		EmployeeVO employee = (EmployeeVO)command;
		return new ModelAndView("EmployeeSuccess","employee",employee);

	}

	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map referenceData = new HashMap();

		Map<String,String> country = new LinkedHashMap<String,String>();
		country.put("US", "United Stated");
		country.put("CHINA", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		referenceData.put("countryList", country);


		return referenceData;
	}
}*/