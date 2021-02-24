package com.kevinpham.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

	// Displays the 'list-customer' page
	// Used in the 'index.jsp' files that redirects to this page on start up
	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// Get customers from the DAO
		List<Customer> theCustomers = customerService.getCustomers();

		// Add the customers to the spring mvc model
		theModel.addAttribute("customers", theCustomers);

		return "list-customer";
	}
	
	
	// Displays the 'customer-form' page
	// Used in the 'list-customer.jsp' onclick button for adding new customer
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// Model Attribute to bind form data
		Customer theCustomer = new Customer();
		
		// Use this attribute name 'customer' to build html/jsp form
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	
	// Save the customer after user fills out form
	// '/saveCustomer' refers to the 'action' attribute in the HTML form
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// Save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		// After saving customer to the model, redirect to the list of customers
		return "redirect:/customer/list";
	}
}
