package com.car.sale.service;


import java.util.List;
import java.util.Optional;

import com.car.sale.exception.CarException;


import com.car.sale.entities.Car;
import com.car.sale.repository.ICarRepository;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ICarServiceImpl implements ICarService {
	
	@Autowired
	ICarRepository icarRepository;
	
public Car addCar(Car car) {
		
		return icarRepository.save(car);
	}


	@Override
	public Car removeCar(long id) throws CarException {
		// TODO Auto-generated method stub
		 Optional<Car> icarOptional = icarRepository.findById(id);
		 if(icarOptional.isEmpty()) {
			 throw new CarException("This car doesn't exists");
		 }
		 icarRepository.deleteById(id);
		 return icarOptional.get();
		
		//return null;
	}

	@Override
	public Car updateCar(long id, Car car) throws CarException {
		// TODO Auto-generated method stub
		 Optional<Car> icarOptional = icarRepository.findById(id);
		 if(icarOptional.isEmpty()) {
			 throw new CarException("This id doesn't exists");
		 }
		Car oldcar = icarOptional.get();
		oldcar.setBrand(car.getBrand());
		oldcar.setModel(car.getModel());
		oldcar.setRegistrationState(car.getRegistrationState());
		oldcar.setVariant(car.getVariant());
		oldcar.setRegistrationState(car.getRegistrationState());
			return oldcar;
	}

	@Override
	public Car getCar(long id) throws CarException{
		// TODO Auto-generated method stub
		Car c1 =  icarRepository.findById(id).orElse(null);;
		if(c1==null) throw new CarException("Car unavailable");

		 return c1;
		//return null;
	}

	@Override
	public List<Car> getAllCars() throws CarException {
		// TODO Auto-generated method stub
		List<Car>c1 =  icarRepository.findAll();
		if(c1==null) throw new CarException("Cars unavailable");

		 return c1;
		//return null;
	}

	@Override
	public List<Car> getCarsByLocation(String location) throws CarException {
		// TODO Auto-generated method stub
		List<Car> Cars = icarRepository.findAll();
		if(Cars==null) throw new CarException("Location unavailable");
		List<Car> filteredList =Cars.stream().filter(Car->Car.getRegistrationState().equals(location)).collect(Collectors.toList());
		return filteredList;
		//return null;
	}

	@Override
	public List<Car> getCarsByModel(String model) throws CarException{
		// TODO Auto-generated method stub
		List<Car> Cars = icarRepository.findAll();
		if(Cars==null) throw new CarException("Model unavailable");
		List<Car> filteredList =Cars.stream().filter(Car->Car.getModel().equals(model)).collect(Collectors.toList());
		return filteredList;
		//return null;
	}

	@Override
	public List<Car> getCarsByBrand(String brand) throws CarException{
		// TODO Auto-generated method stub
		List<Car> Cars = icarRepository.findAll();
		if(Cars==null) throw new CarException("Brand unavailable");
		List<Car> filteredList =Cars.stream().filter(Car->Car.getBrand().equals(brand)).collect(Collectors.toList());
		return filteredList;
		//return null;
	}
}