
package com.car.sale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.sale.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
//	public Order addOrder(Order order);
//	public Order removeOrder(long id);
//	public Order updateOrder(long id, Order order);
//	public Order getOrderDetails(long id);
//	public List<Order> getAllOrders(); 

}
