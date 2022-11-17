package com.car.sale.repository;

import java.util.List;

//import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.car.sale.entities.Customer;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query(value="SELECT c.* , a.* FROM customer_details c LEFT JOIN address_details a ON c.user_id =a.user_id WHERE a.city=:ci",nativeQuery = true )
	public List<Customer> getCustomersByLocationByQuery(@Param("ci") String city);
	
	public Customer findCustomerByName(String name);
	
}
