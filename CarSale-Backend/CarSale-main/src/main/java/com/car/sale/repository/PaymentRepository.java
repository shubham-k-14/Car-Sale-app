package com.car.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.sale.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
