package com.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.demo.service.OrderManager;
 
@Controller
@RequestMapping("/order")
public class OrderController 
{
    @Autowired
    OrderManager manager;
 
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public String getAllEmployees(Model model) throws SQLException
    {
        model.addAttribute("orders", manager.getAllOrders());
        return "index";
    }
}