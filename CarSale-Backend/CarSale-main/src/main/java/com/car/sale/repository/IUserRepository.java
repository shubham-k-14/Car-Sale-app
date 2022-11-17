package com.car.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.sale.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
