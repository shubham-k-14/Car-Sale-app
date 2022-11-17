package com.car.sale.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.sale.exception.OrderException;
import com.car.sale.entities.Customer;
import com.car.sale.entities.Order;
import com.car.sale.repository.OrderRepository;


@Transactional
@Service
public class OrderServiceImp implements OrderService{
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public Order addOrder(Order order) throws OrderException {
	    long id;
		try {
			Order o1=orderRepository.save(order);
			if(o1 == null)
				throw new OrderException("Order not Added");
			id=o1.getOrderId();
	}catch(OrderException e) {
		throw e;
	}
	  return order;
}
	
	@Override
	public Order removeOrder(long id) throws OrderException{
		Optional<Order> orderOptional = orderRepository.findById(id);
		 if(orderOptional.isEmpty()) {
			 throw new OrderException("This id doesn't exists ");
		 }
		 orderRepository.deleteById(id);
		 return orderOptional.get();
	}
	
	@Override
	public Order updateOrder(long id,Order order) throws OrderException{
		Optional<Order> o1= orderRepository.findById(id);
		if(o1.isPresent()) {
			orderRepository.save(order);
	    }
	    else 
		    throw new OrderException("Order Details Not Found");
	    return order;
		
	}
	@Override
	public Order getOrderDetails(long id)  throws OrderException{
		Order order=null;
		Optional<Order> o1=orderRepository.findById( id);
		if(o1.isPresent()) {
			order=o1.get();
		}
		else
			throw new OrderException("Order Id Not Found");
		return order;
	//	return orderRepository.findById((int) id).orElse(null);
	}
	
	@Override
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	
	

}
