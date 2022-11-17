package com.car.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.car.sale.entities.Car;

@Repository
public interface ICarRepository extends JpaRepository<Car,Long>{


}
