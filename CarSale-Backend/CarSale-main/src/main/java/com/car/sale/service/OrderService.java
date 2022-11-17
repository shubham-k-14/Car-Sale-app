package com.car.sale.service;

import java.util.List;

import com.car.sale.exception.OrderException;
import com.car.sale.entities.Order;

public interface OrderService {
        public Order addOrder(Order order) throws OrderException;
        public Order removeOrder(long id) throws OrderException;
    	public Order updateOrder(long id, Order order) throws OrderException;
    	public Order getOrderDetails(long id) throws OrderException;
    	public List<Order> getAllOrders() throws OrderException; 
}
