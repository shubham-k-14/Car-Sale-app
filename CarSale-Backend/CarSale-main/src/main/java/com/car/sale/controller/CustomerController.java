package com.car.sale.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//import java.time.LocalDate;
//import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.data.annotation.Persistent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.sale.entities.Address;
import com.car.sale.entities.Customer;
import com.car.sale.exception.InvalidCustomerException;
import com.car.sale.repository.AddressRepository;
import com.car.sale.repository.CustomerRepository;
import com.car.sale.service.ICustomerService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("customer")
public class CustomerController {
	 
	@Autowired
	ICustomerService customerService;
	

	//	to save the customer in the database
	@PostMapping(value="addCustomer")
	public ResponseEntity addCustomer(@Valid @RequestBody Customer customer , BindingResult br) {
		
		if(br.hasErrors())
        {
            String error = "";
            for(ObjectError err : br.getAllErrors())
            {
                error = error + "\n" + err.getDefaultMessage();
            }
            return new ResponseEntity<String>(error, HttpStatus.UNAUTHORIZED);
         }
		
		try {
			 customer = customerService.addCustomer(customer);
			ResponseEntity<Customer> rc= new ResponseEntity<Customer>(customer,HttpStatus.OK);
			return rc;
		}
		catch (InvalidCustomerException e) {
			ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			return rs;
		}
		
	}
	
	
	// to fetch all customers from the database
	@GetMapping(value="getAllCustomers")
	public ResponseEntity getAllCustomers(){		
	try {
		System.out.println("inside controller");
		List <Customer> customer = customerService.getAllCustomers();
		ResponseEntity<List<Customer>>  rc= new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
		return rc;
		}
	catch (InvalidCustomerException e) {
				ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
				return rs;
			}
			
	}
	
	
	//to delete customer in the database
	@DeleteMapping("removeCustomer")
	public ResponseEntity removecustomer(@RequestParam int userId) {
		try {
			Customer customer = customerService.removeCustomer(userId);
			ResponseEntity<Customer> rc= new ResponseEntity<Customer>(customer,HttpStatus.OK);
			return rc;
		}
		catch (InvalidCustomerException e) {
			ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			return rs;
		}
		
	}
	
	// to update Customer in the database
	@PutMapping("updateCustomer")
	public ResponseEntity updateCustomner(@RequestBody Customer customer) {
		try {
			 customer = customerService.updateCustomer(customer);
			ResponseEntity<Customer> rc= new ResponseEntity<Customer>(customer,HttpStatus.OK);
			return rc;
		}
		catch (InvalidCustomerException e) {
			ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			return rs;
		}	
		
	}
	
	// to fetch the customers by their ids
	@GetMapping("/findCustomerById/{userId}")
	public ResponseEntity getCustomer(@PathVariable int userId)  {
		try {
			Customer customer = customerService.getCustomer(userId);
			ResponseEntity<Customer> rc= new ResponseEntity<Customer>(customer,HttpStatus.OK);
			return rc;
		} catch (InvalidCustomerException e) {
			ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			return rs;
		}
		
	}
	
	// to fetch the customers by their locations
	@GetMapping("/findCustomersByLocation/{city}")
	public ResponseEntity getCustomersByLocation(@PathVariable("city") String city){
		
		try {
			List<Customer> customer = customerService.getCustomersByLocation(city);
			ResponseEntity<List<Customer>> rc= new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
			return rc;
		}
		catch (InvalidCustomerException e) {
			ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			return rs;
		}
	}
		
	// to fetch the customers by their locations using Query
	@GetMapping("/findCustomersByLocationByQuery/{city}")
	public ResponseEntity getCustomersByLocationByQuery(@PathVariable("city") String city){
			
		try {
			List<Customer> customer = customerService.getCustomersByLocationByQuery(city);
			ResponseEntity<List<Customer>> rc= new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
			return rc;
			}
			catch (InvalidCustomerException e) {
			ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			return rs;
		}
	}

	
}
