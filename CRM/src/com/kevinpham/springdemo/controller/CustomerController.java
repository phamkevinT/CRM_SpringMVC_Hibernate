package com.kevinpham.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kevinpham.springdemo.dao.CustomerDAO;
import com.kevinpham.springdemo.entity.Customer;
import com.kevinpham.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// Inject the customer service
	// Spring will scan for a component that implements the CustomerDAO interface
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// Get customers from the DAO
		List<Customer> theCustomers = customerService.getCustomers();

		// Add the customers to the spring mvc model
		theModel.addAttribute("customers", theCustomers);

		return "list-customer";
	}
}
