package com.car.sale.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.sale.exception.CarException;
import com.car.sale.entities.Car;
import com.car.sale.service.*;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	ICarService carService;
	
	
	@PostMapping("/addCar")
	public  Car addCar(@Valid @RequestBody Car car) {
		return carService.addCar(car);
	}
	
	@GetMapping("/car/{id}")
	public ResponseEntity<Object> getCar(@PathVariable long id) {
		try {
			Car  car = carService.getCar(id);
			return new ResponseEntity<Object>(car,HttpStatus.OK);
		} catch (CarException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}
	
	@GetMapping("/car")
		public ResponseEntity<Object> getAllCars(){
			try {
				List<Car> cars =carService.getAllCars();
				return new ResponseEntity<Object>(cars,HttpStatus.OK);
			} catch (CarException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
	
	@GetMapping("/Brand/{Brand}")
	public ResponseEntity<Object> getCarsByBrand(@PathVariable String Brand){
		try {
		List<Car> cars = carService.getCarsByBrand(Brand);
		return new ResponseEntity<Object>(cars,HttpStatus.OK);
		} catch (CarException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/Model/{Model}")
	public ResponseEntity<Object> getCarsByModel(@PathVariable String Model){
		try {
		List<Car> cars = carService.getCarsByModel(Model);
		return new ResponseEntity<Object>(cars,HttpStatus.OK);
		} catch (CarException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/Location/{Location}")
	public ResponseEntity<Object> getCarsByLocation(@PathVariable String Location){
		try {
		List<Car> cars = carService.getCarsByLocation(Location);
		return new ResponseEntity<Object>(cars,HttpStatus.OK);
		} catch (CarException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Object> removeCar(@PathVariable long id) throws CarException{
		try {
		Car cars = carService.removeCar(id);
		return new ResponseEntity<Object>(cars,HttpStatus.OK);
		} catch (CarException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCar( @PathVariable long id,@Valid @RequestBody  Car car) {
		try {
			Car cars = carService.updateCar(id, car);
			return new ResponseEntity<Object>(cars,HttpStatus.OK);
		} catch (CarException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
}
