package com.car.sale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.sale.entities.Customer;
import com.car.sale.exception.InvalidCustomerException;
import com.car.sale.service.ICustomerService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	ICustomerService customerService;
	
	//default mapping for admin
	@RequestMapping
	public String welcomeAdmin() {
		return "Welcome Admin";
	}
	
	//getting all customer and their info
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
	
	//getting customer by their id
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
}
