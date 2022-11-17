package com.car.sale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.car.sale.entities.Address;
import com.car.sale.entities.Customer;
import com.car.sale.exception.InvalidCustomerException;
import com.car.sale.repository.CustomerRepository;
import com.car.sale.service.ICustomerService;

@SpringBootTest
class CustomerServiceTest {
	
	@Autowired
	private ICustomerService customerService;
	
	//to create mock repository so that the data in the db don't get affected
	@MockBean
	CustomerRepository customerRepository;

	
	//----------------------------- Methods Testing ----------------------------------------
	
	//test case for adding customer
	@Test
	public void addCustomer() {
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		try {
		Customer result = customerService.addCustomer(customer);
		assertEquals(customer, result);
		}
		catch(InvalidCustomerException e) {
			assertTrue(false);
		}
	}
	
	
	
	//test case for getting customer by id
	@Test
	public void testGetCustomer() {
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		Mockito.when(customerRepository.findById((long) 1)).thenReturn(Optional.of(customer));
		try { 
			assertThat(customerService.getCustomer(1)).isEqualTo(customer);
			}
		catch(InvalidCustomerException e) {
			assertTrue(false);
		}
		
	}
	
	
	
	// test case for getting all customers 
	@Test
	public void testGetAllCustomers() {
		
		Address a = new Address();
		a.setAddressId(10);
		a.setCity("nagpur");
		
		Address a1 = new Address();
		a1.setAddressId(20);
		a1.setCity("mumbai");
		
		List<Address> addressList = new ArrayList<>();
		addressList.add(a);
		addressList.add(a1);
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		customer.setAddress(addressList);

		Customer customer1 = new Customer();
		customer.setUserId(2);
		customer.setName("Samir");
		customer.setEmail("samir@gmail.com");
		customer.setContactNo("9652463556");
		customer.setDob(LocalDate.of(1999, 07, 1));
		customer.setAddress(addressList);
		
		List<Customer> CustomerList = new ArrayList<>();
		CustomerList.add(customer);
		CustomerList.add(customer1);
		
		Mockito.when(customerRepository.findAll()).thenReturn(CustomerList);
		try {
			assertThat(customerService.getAllCustomers()).isEqualTo(CustomerList);
		}
		catch(InvalidCustomerException e) {
			assertTrue(false);
		}
				
	}
	
	
	// test case for update customer
	@Test
	public void testUpdateCustomer(){
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		
		
		Mockito.when(customerRepository.findById((long) 1)).thenReturn(Optional.of(customer));
		customer.setName("Samir");
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		try {
			  assertThat(customerService.updateCustomer(customer)).isEqualTo(customer);
		}
		catch(InvalidCustomerException e) {
			assertTrue(false);
		}
	}
	
	// test case for removing customer
	@Test
	public void testRemoveCustomer() {
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		
		Mockito.when(customerRepository.findById((long) 1)).thenReturn(Optional.of(customer));
		
		try {
			customerService.removeCustomer(1);
			verify(customerRepository,times(1)).deleteById((long) 1);
		}
		catch(InvalidCustomerException e) {
			assertTrue(false);
		}
	    
	}
	
	// test get customers by location
	
	@Test
	public void testGetCustomersByLocationsByQuery() {
		
		Address a = new Address();
		a.setAddressId(10);
		a.setCity("nagpur");
		
		Address a1 = new Address();
		a1.setAddressId(20);
		a1.setCity("mumbai");
		
		List<Address> addressList = new ArrayList<>();
		addressList.add(a);
		addressList.add(a1);
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		customer.setAddress(addressList);
		
		
		
		Customer customer1 = new Customer();
		customer.setUserId(2);
		customer.setName("Samir");
		customer.setEmail("samir@gmail.com");
		customer.setContactNo("9652463556");
		customer.setDob(LocalDate.of(1999, 07, 1));
		customer.setAddress(addressList);
		
		
		List<Customer> CustomerList = new ArrayList<>();
		CustomerList.add(customer);
		CustomerList.add(customer1);
		
		Mockito.when(customerRepository.getCustomersByLocationByQuery("mumbai")).thenReturn(CustomerList);
		try {
			assertThat(customerService.getCustomersByLocationByQuery("mumbai")).isEqualTo(CustomerList);
		}
		catch(InvalidCustomerException e){
			assertTrue(false);
		}
	
	}
	
	
 //-----------------------------Exception Testing------------------------------------------
	
	
	
	
	// Exception testing for get customer by id
	@Test
	public void testGetCustomerForException() throws InvalidCustomerException {
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		
		Mockito.when(customerRepository.findById((long) 1)).thenReturn(Optional.of(customer));
		assertThrows(InvalidCustomerException.class, ()-> customerService.getCustomer(2));				
	}
	
	
	
	// Exception testing for getting all customers 
	@Test
	public void testGetAllCustomersForException() throws InvalidCustomerException {
		
		Address a = new Address();
		a.setAddressId(10);
		a.setCity("nagpur");
		
		Address a1 = new Address();
		a1.setAddressId(20);
		a1.setCity("mumbai");
		
		List<Address> addressList = new ArrayList<>();
		addressList.add(a);
		addressList.add(a1);
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		customer.setAddress(addressList);

		Customer customer1 = new Customer();
		customer.setUserId(2);
		customer.setName("Samir");
		customer.setEmail("samir@gmail.com");
		customer.setContactNo("9652463556");
		customer.setDob(LocalDate.of(1999, 07, 1));
		customer.setAddress(addressList);
		
		List<Customer> CustomerList = new ArrayList<>();
		CustomerList.add(customer);
		CustomerList.add(customer1);
		
		Mockito.when(customerRepository.findAll()).thenReturn(null);	
		assertThrows(InvalidCustomerException.class, ()->customerService.getAllCustomers(),"Exception not matched");
			
	}
	
	
	
	// Exception testing for updating customers 
	@Test
	public void testUpdateCustomerForException() throws InvalidCustomerException{
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		
		
		Mockito.when(customerRepository.findById((long) 2)).thenReturn(Optional.of(customer));
		customer.setName("Samir");
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		assertThrows(InvalidCustomerException.class, ()-> customerService.updateCustomer(customer));
	}
	
	
	// Exception testing for removing customers 
	@Test
	public void testRemoveCustomerForException() throws InvalidCustomerException {
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		
		Mockito.when(customerRepository.findById((long) 2)).thenReturn(Optional.of(customer));
		assertThrows(InvalidCustomerException.class, ()-> customerService.removeCustomer(1));
		
	}
	
	
	//// Exception testing for getting customers by location
	@Test
	public void testGetCustomersByLocationsForException()throws InvalidCustomerException {
		
		Address a = new Address();
		a.setAddressId(10);
		a.setCity("nagpur");
		
		Address a1 = new Address();
		a1.setAddressId(20);
		a1.setCity("mumbai");
		
		List<Address> addressList = new ArrayList<>();
		addressList.add(a);
		addressList.add(a1);
		
		Customer customer = new Customer();
		customer.setUserId(1);
		customer.setName("Shubham");
		customer.setEmail("shubham@gmail.com");
		customer.setContactNo("1234567893");
		customer.setDob(LocalDate.of(1999, 11, 2));
		customer.setAddress(addressList);
		
		
		
		Customer customer1 = new Customer();
		customer.setUserId(2);
		customer.setName("Samir");
		customer.setEmail("samir@gmail.com");
		customer.setContactNo("9652463556");
		customer.setDob(LocalDate.of(1999, 07, 1));
		customer.setAddress(addressList);
		
		
		List<Customer> CustomerList = new ArrayList<>();
		CustomerList.add(customer);
		CustomerList.add(customer1);
		
		Mockito.when(customerRepository.getCustomersByLocationByQuery("pune")).thenReturn(null);
		assertThrows(InvalidCustomerException.class,()->customerService.getCustomersByLocationByQuery("pune"));
	}
	
	
	//-----------------------------Failed test cases--------------------------------------------------
	
	//failed test case for getting customer by id
		@Test
		public void failedTestGetCustomer() {
			Customer customer = new Customer();
			customer.setUserId(1);
			customer.setName("Shubham");
			customer.setEmail("shubham@gmail.com");
			customer.setContactNo("1234567893");
			customer.setDob(LocalDate.of(1999, 11, 2));
			Mockito.when(customerRepository.findById((long) 1)).thenReturn(Optional.of(customer));
			try { 
				assertThat(customerService.getCustomer(2)).isEqualTo(customer);
				}
			catch(InvalidCustomerException e) {
				assertTrue(false);
			}
			
		}
		
		
		// failed test case for removing customer
		@Test
		public void failedTestRemoveCustomer() {
			
			Customer customer = new Customer();
			customer.setUserId(1);
			customer.setName("Shubham");
			customer.setEmail("shubham@gmail.com");
			customer.setContactNo("1234567893");
			customer.setDob(LocalDate.of(1999, 11, 2));
			
			Mockito.when(customerRepository.findById((long) 1)).thenReturn(Optional.of(customer));
			
			try {
				customerService.removeCustomer(1);
				verify(customerRepository,times(1)).deleteById((long) 2);
			}
			catch(InvalidCustomerException e) {
				assertTrue(false);
			}
		    
		}
		
		// failed test case for update customer
		@Test
		public void failedTestUpdateCustomer(){
			
			Customer customer = new Customer();
			customer.setUserId(1);
			customer.setName("Shubham");
			customer.setEmail("shubham@gmail.com");
			customer.setContactNo("1234567893");
			customer.setDob(LocalDate.of(1999, 11, 2));
			
			
			Mockito.when(customerRepository.findById((long) 2)).thenReturn(Optional.of(customer));
			customer.setName("Samir");
			Mockito.when(customerRepository.save(customer)).thenReturn(customer);
			try {
				  assertThat(customerService.updateCustomer(customer)).isEqualTo(customer);
			}
			catch(InvalidCustomerException e) {
				assertTrue(false);
			}
		}
	

}
