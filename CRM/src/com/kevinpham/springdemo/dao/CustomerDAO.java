package com.kevinpham.springdemo.dao;

import java.util.List;

import com.kevinpham.springdemo.entity.Customer;

public interface CustomerDAO {

	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);
}
