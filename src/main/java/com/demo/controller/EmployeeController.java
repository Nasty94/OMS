package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.demo.service.EmployeeManager;
 
@Controller
@RequestMapping("/")
public class EmployeeController 
{
    @Autowired
    EmployeeManager manager;
 
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String getAllEmployees(Model model)
    {
        model.addAttribute("employees", manager.getAllEmployees());
        return "index";
    }
}