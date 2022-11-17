package com.car.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.sale.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
