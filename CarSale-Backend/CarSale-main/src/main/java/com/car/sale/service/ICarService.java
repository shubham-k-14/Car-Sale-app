package com.car.sale.service;

import java.util.List;

import com.car.sale.exception.CarException;
import com.car.sale.entities.Car;


public interface ICarService {
	public Car addCar(Car car);
	public Car removeCar (long id)throws CarException;
	public Car updateCar(long id, Car car)throws CarException;
	public Car getCar(long id)throws CarException;
	public List<Car> getAllCars()throws CarException;
	public List<Car> getCarsByLocation(String location)throws CarException;
	public List<Car> getCarsByModel(String model)throws CarException;
	public List<Car> getCarsByBrand(String brand)throws CarException;


}
