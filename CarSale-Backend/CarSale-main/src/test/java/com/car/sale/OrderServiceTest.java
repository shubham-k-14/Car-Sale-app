package com.car.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.car.sale.entities.Customer;
import com.car.sale.entities.Order;
import com.car.sale.exception.OrderException;
import com.car.sale.repository.OrderRepository;
import com.car.sale.service.OrderService;

@SpringBootTest
public class OrderServiceTest {
    
	@Autowired 
	private OrderService orderService;
	
	@MockBean
	OrderRepository orderRepository;
	


//*** TESTING ***
	
	//test case for adding order
	@Test
	public void testAddOrder() {
		Order order = getOrder();
		when(orderRepository.save(order)).thenReturn(order);
		try {
			assertEquals(orderService.addOrder(order), order);
		} catch (OrderException e) {
			assertFalse(true);
		}
	}

	//test case for updating order
	@Test
	public void testUpdateOrder() {
		Order order = getOrder();
		when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		try {
			assertSame(orderService.updateOrder(order.getOrderId(), order), order);
		} catch (OrderException e) {
			assertFalse(true);
		}
	}

	//test case for removing order
	@Test
	public void testRemoveOrder() {
		Order order = getOrder();
		when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		try {
			orderService.removeOrder(order.getOrderId());
		} catch (OrderException e) {
			assertFalse(true);
		}
		verify(orderRepository, times(1)).deleteById(order.getOrderId());
	}
	
	//test case to get order by Id
	@Test
	public void testGetOrder() {
		Order order = getOrder();
		when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		try {
			orderService.getOrderDetails(order.getOrderId());
		} catch (OrderException e) {
			assertFalse(true);
		}
		verify(orderRepository, times(1)).findById(order.getOrderId());
	}

	//test case to get all order details
	@Test
	public void testGetAllOrder() {
		when(orderRepository.findAll()).thenReturn(Stream.of(getOrder()).collect(Collectors.toList()));
		try {
			assertEquals(1, orderService.getAllOrders().size());
		} catch (OrderException e) {
			assertFalse(true);
		}
	}

	
	private Order getOrder() {
		// TODO Auto-generated method stub
		Order order = new Order();
		
		order.setOrderId(1);
		order.setAmount(100000);
		order.setBillingDate(LocalDate.of(2021, 8, 29));
		return order;
	}
	
	private Customer getCustomer() {
		Customer customer = new Customer();
		
		customer.setUserId(1);
		customer.setRole("User");;
		customer.setName("Nivedita");
		customer.setEmail("nivedita@gmail.com");
		customer.setDob(LocalDate.of(1999, 2, 21));
		customer.setContactNo("9008965287");
		
		return customer;
	}



}
