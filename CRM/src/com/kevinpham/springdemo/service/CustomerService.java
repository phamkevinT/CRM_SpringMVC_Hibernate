package com.kevinpham.springdemo.service;

import java.util.List;

import com.kevinpham.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);
}
