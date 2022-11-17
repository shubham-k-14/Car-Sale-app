package com.car.sale.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.sale.entities.Address;
import com.car.sale.entities.Customer;
import com.car.sale.exception.InvalidCustomerException;
import com.car.sale.repository.CustomerRepository;




@Service
@Transactional
public class ICustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository customerRepository;
		

	// to add new customer in the database
		@Override
		public Customer addCustomer(Customer customer) throws InvalidCustomerException {
				 customer = customerRepository.save(customer);
				if(customer == null) 
					throw new InvalidCustomerException("Invalid Customer Details try again!");
				
				return customer;
					
			}
			 
		
	// to remove the customer
	@Override
	public Customer removeCustomer(long userId) throws InvalidCustomerException{
		
		Optional<Customer> c= customerRepository.findById(userId);
		if(c.isPresent())
		{
			customerRepository.deleteById(userId);
		}
		else
			throw new InvalidCustomerException("Customer Does not Exist" );
		return c.get();
			
	}

	//to update customer in the database
		@Override
		public Customer updateCustomer(Customer customer) throws InvalidCustomerException {
			//same save method is used to save new customer and update customer
			Optional<Customer> c = customerRepository.findById(customer.getUserId());
			if(c.isPresent()) {
				customerRepository.save(customer);
			}
			else 
				throw new InvalidCustomerException("Matching Record not found");
			return customer;
		}

	// to get all customers by id
	@Override
	public Customer getCustomer(long userId) throws InvalidCustomerException {
		Customer customer=null;
	Optional<Customer> c = customerRepository.findById(userId);
		if(c.isPresent()) {
		  customer = c.get();
		}
		else
			throw new InvalidCustomerException("Customer ID not Found");
		return customer;
		
	}

	//returns all the customers
	@Override
	public List<Customer> getAllCustomers() throws InvalidCustomerException {
		List<Customer> allCustomers = customerRepository.findAll();
		if( allCustomers== null || allCustomers.size() == 0)
			throw new InvalidCustomerException("There is no records of customers");
		else
		return  allCustomers;
	}

	//to return the customers by their location
	@Override
	public List<Customer> getCustomersByLocation(String city) throws InvalidCustomerException {
		List<Customer> reqList = new ArrayList<>();
		
		List<Customer> allCustomers = customerRepository.findAll();
		
		allCustomers.forEach((c)->{
			List<Address> addressList = c.getAddress();
			addressList.forEach((a)->{
				if(a.getCity().equals(city)) {
					reqList.add(c);
				}	
			});		
		});
		
		if(reqList==null || reqList.isEmpty())
			throw new InvalidCustomerException("Customer in specified city does not exist");
		
		return reqList;
	}


	@Override
	public List<Customer> getCustomersByLocationByQuery(String city) throws InvalidCustomerException{
		System.out.println("inside query method");
		
		List<Customer> cusList = customerRepository.getCustomersByLocationByQuery(city);
		if(cusList==null || cusList.isEmpty())
			throw new InvalidCustomerException("There is no records of customers in this city");
		
		else
		return cusList ;
	}




}
