package com.kevinpham.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kevinpham.springdemo.dao.CustomerDAO;
import com.kevinpham.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// Need to inject the customer data access object
	// Spring will scan for a component that implements the CustomerDAO interface
	@Autowired
	private CustomerDAO customerDAO;

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {

		// Get customers from the DAO
		List<Customer> theCustomers = customerDAO.getCustomers();

		// Add the customers to the spring mvc model
		theModel.addAttribute("customers", theCustomers);

		return "list-customer";
	}
}
