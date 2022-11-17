package com.car.sale.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.car.sale.exception.OrderException;
import com.car.sale.entities.Order;
import com.car.sale.service.OrderService;


@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200")
public class OrderController {
      @Autowired
      OrderService orderService;
      
      @GetMapping
  	  public String welcome() {
  		return "***WELCOME TO CAR SALE APPLICATION ORDER MUDULE***";
  	}
     
    
      @PostMapping("/addorder" )
      public ResponseEntity addOrder(@Valid @RequestBody Order order,BindingResult result) {
    	  System.out.println(" hello" + result.hasErrors());
    	  if(result.hasErrors())  
	      	{
    		    System.out.println("Validating add order method");
	      		String error = "";
	      		for(ObjectError err : result.getAllErrors()) 
	      		{
	      			error = error + "\n" + err.getDefaultMessage();
	      		}
	      		return new ResponseEntity<String>(error, HttpStatus.OK);
	       	}
	       try {
  	    	 	Order id=orderService.addOrder(order);
  	    	 	System.out.println("Order added.."+ id);
  	    	 	ResponseEntity<Order> rs= new ResponseEntity<Order>(id, HttpStatus.OK);
  	    	 	return rs;
  	          }
  	      catch(OrderException e) {
  	    	    ResponseEntity<String> rs1=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		        return rs1;
  	      }
      }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> removeOrder(@PathVariable long id){
    	Order order;
    	try {
    		order=orderService.removeOrder(id);
    		return new ResponseEntity<Object>(order, HttpStatus.FOUND);
    	}
    	catch(OrderException de) {
				return new ResponseEntity<Object>(de.getMessage(),HttpStatus.NOT_FOUND);
		}
  	}
    @PutMapping("/updateorder/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable long id,@RequestBody Order order){
    	try {
    		order=orderService.updateOrder(id,order);
    		return new ResponseEntity<Object>(order, HttpStatus.OK);
    	}
    	catch(OrderException e) {
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		} 
    
	}
    
    @RequestMapping("/getorderdetailsbyId/{id}")
    public ResponseEntity getOrderDetails(@PathVariable long id) {
    	Order order=null;
		try {
			order = orderService.getOrderDetails(id);
			ResponseEntity<Order> rs= new ResponseEntity<Order>(order,HttpStatus.OK);
			return rs;
		} catch (OrderException e) {
			ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			return rs;
		}
	}
    @GetMapping("/getallorders")
	public ResponseEntity<Object> getAllOrders(){
    	List<Order> list=null;
    	
    	try {
			list = orderService.getAllOrders();
			ResponseEntity<Object> rs= new ResponseEntity<Object>(list,HttpStatus.OK);
			return rs;
		} catch (OrderException e) {
			ResponseEntity<Object> rs= new ResponseEntity<Object>(e.getMessage(),HttpStatus.NOT_FOUND);
			return rs;
		}

	}
    
   
    
}
