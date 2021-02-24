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
	public List<Customer> getCustomers() {

		// Get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Create a query - Sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", Customer.class);

		// Get result list from execution
		List<Customer> customers = theQuery.getResultList();

		// Return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save (if not primary key/id) or Update (if primary key/if exists) the customer
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Retrieve customer from database using the ID as primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
		
	}

}
