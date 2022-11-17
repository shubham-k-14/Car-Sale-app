package com.car.sale.service;

import java.util.List;

import com.car.sale.entities.Customer;
import com.car.sale.exception.InvalidCustomerException;

public interface ICustomerService {
	// to save the customer 
	public Customer addCustomer(Customer customer) throws InvalidCustomerException;
	
	//to remove the customer
	public Customer removeCustomer(long userId) throws InvalidCustomerException;
	
	// to update the customer
	public Customer updateCustomer(Customer customer) throws InvalidCustomerException;
	
	// to fetch the customer from the db
	public Customer getCustomer(long userId) throws InvalidCustomerException;
	
	//fetch all customers from db
	public List<Customer> getAllCustomers() throws InvalidCustomerException; 
	
	//find all customers by location
	public List<Customer> getCustomersByLocation(String city) throws InvalidCustomerException;
	
	//find all customers by location by query
	public List<Customer> getCustomersByLocationByQuery(String city) throws InvalidCustomerException;
	
	
	
}
