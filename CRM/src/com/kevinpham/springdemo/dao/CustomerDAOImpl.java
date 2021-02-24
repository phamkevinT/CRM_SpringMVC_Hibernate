package com.kevinpham.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevinpham.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Customer> getCustomers() {

		// Get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// Create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);

		// Get result list from execution
		List<Customer> customers = theQuery.getResultList();

		// Return the results
		return customers;
	}

}
