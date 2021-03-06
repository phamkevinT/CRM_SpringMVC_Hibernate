package com.kevinpham.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kevinpham.springdemo.entity.Customer;
import com.kevinpham.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	
	// Get the customer's information from the database
	@Override
	public List<Customer> getCustomers(int theSortField) {

		// Get current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Determine sort field
		String theFieldName = null;
		
		switch (theSortField) {
		
			case SortUtils.FIRST_NAME: 
				theFieldName = "firstName";
				break;
				
			case SortUtils.LAST_NAME:
				theFieldName = "lastName";
				break;
				
			case SortUtils.EMAIL:
				theFieldName = "email";
				break;
				
			default:
				// Default to sorting by last name
				theFieldName = "lastName";
		}
		
		// Create a query  
		String queryString = "from Customer order by " + theFieldName;
		
		Query<Customer> theQuery = 
				currentSession.createQuery(queryString, Customer.class);
		
		// Execute the query
		List<Customer> customers = theQuery.getResultList();
				
		// Return the results		
		return customers;
	}

	
	// Save a new customer to the database
	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Save (if not primary key/id) or Update (if primary key/id exists) the customer
		// Primary Key/ID is passed through by the '<form:hidden path="id" />' in 'customer-form.jsp'
		currentSession.saveOrUpdate(theCustomer);
		
	}

	
	// Get a single customer from the database based on primary key/id
	@Override
	public Customer getCustomer(int theId) {
		
		// Get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Retrieve customer from database using the ID as primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
		
	}
	
	
	// Delete a customer from the database based on primary key/id
	@Override
	public void deleteCustomer(int theId) {

		// Get current hibermate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// Delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		
		// Set the parameter in the query above to be the ID
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();		
	}

	
	// Get a list of customers based on name search
	@Override
    public List<Customer> searchCustomers(String theSearchName) {
		
        // Get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        // Only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {
        	
            // Search for firstName or lastName 
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            
    		// Set the parameter in the query above to be the ID
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
        	
            // theSearchName is empty -> return all customers from database
            theQuery = currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // Execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
    }

}
