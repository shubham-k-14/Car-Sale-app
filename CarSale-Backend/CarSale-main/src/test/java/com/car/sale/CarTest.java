package com.car.sale;

 

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

 

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import com.car.sale.entities.Car;
import com.car.sale.entities.Customer;
import com.car.sale.exception.CarException;
import com.car.sale.repository.ICarRepository;
import com.car.sale.service.ICarService;
@RunWith(SpringRunner.class)
@SpringBootTest

 

class CarTest {
    
    @Autowired
    private ICarService carService;
    
    @MockBean
    private ICarRepository carRepository;
    @Test
    void testaddCar() {
        Car car = new Car();
        car.setCarId(1);;
        car.setCustomer(new Customer());
        car.setBrand("Benz");
        car.setRegistrationState("Mumbai");
       car.setModel("AMG");
        car.setVariant("43");
        LocalDate date=LocalDate.of(2019,11,05);
       car.setRegistrationYear(date);
        
        Mockito.when(carRepository.save(car)).thenReturn(car);
        
        assertThat(carService.addCar(car)).isEqualTo(car);
    }
    
    @Test
    public void testgetCar() throws CarException{
        Car car = new Car();
        car.setCarId(1);;
        car.setCustomer(new Customer());
        car.setBrand("Benz");
        car.setRegistrationState("Mumbai");
       car.setModel("AMG");
        car.setVariant("43");
        LocalDate date=LocalDate.of(2019,11,05);
       car.setRegistrationYear(date);
        Mockito.when(carRepository.findById((long) 1)).thenReturn(Optional.of(car));
         assertThat(carService.getCar(1)).isEqualTo(car);
         }
    
    @Test
    public void testgetAllCars() throws CarException{
        Car car = new Car();
        car.setCarId(1);;
        car.setCustomer(new Customer());
        car.setBrand("Benz");
        car.setRegistrationState("Mumbai");
       car.setModel("AMG");
        car.setVariant("43");
        LocalDate date=LocalDate.of(2019,11,05);
       car.setRegistrationYear(date);
        
       Car car2 = new Car();
       car2.setCarId(2);;
       car2.setCustomer(new Customer());
       car2.setBrand("BMW");
       car2.setRegistrationState("Pune");
      car2.setModel("X6");
       car2.setVariant("D5");
       LocalDate date2=LocalDate.of(2019,11,05);
      car2.setRegistrationYear(date2);
        List<Car> CarList = new ArrayList<>();
        CarList.add(car);
       CarList.add(car2);
        
        Mockito.when(carRepository.findAll()).thenReturn(CarList);
        assertThat(carService.getAllCars()).isEqualTo(CarList);
    }
    @Test
    public void testgetCarsByModel() throws CarException{
        Car car = new Car();
        car.setCarId(1);;
        car.setCustomer(new Customer());
        car.setBrand("Benz");
        car.setRegistrationState("Mumbai");
       car.setModel("AMG");
        car.setVariant("43");
        LocalDate date=LocalDate.of(2019,11,05);
       car.setRegistrationYear(date);
        
       Car car2 = new Car();
       car2.setCarId(2);;
       car2.setCustomer(new Customer());
       car2.setBrand("BMW");
       car2.setRegistrationState("Pune");
      car2.setModel("X6");
       car2.setVariant("D5");
       LocalDate date2=LocalDate.of(2019,11,05);
      car2.setRegistrationYear(date2);
        List<Car> CarList = new ArrayList<>();
        CarList.add(car);
       CarList.add(car2);
        
        List<Car> filteredList = CarList.stream()
                .filter(Car->Car.getModel().equals("X6")).collect(Collectors.toList());
        
        Mockito.when(carRepository.findAll()).thenReturn(filteredList);
        assertThat(carService.getCarsByModel("X6")).isEqualTo(filteredList);
    }
    
    
    @Test
    public void testgetCarsByLocation() throws CarException{
        Car car = new Car();
        car.setCarId(1);;
        car.setCustomer(new Customer());
        car.setBrand("Benz");
        car.setRegistrationState("Mumbai");
       car.setModel("AMG");
        car.setVariant("43");
        LocalDate date=LocalDate.of(2019,11,05);
       car.setRegistrationYear(date);
        
       Car car2 = new Car();
       car2.setCarId(2);;
       car2.setCustomer(new Customer());
       car2.setBrand("BMW");
       car2.setRegistrationState("Pune");
      car2.setModel("X6");
       car2.setVariant("D5");
       LocalDate date2=LocalDate.of(2019,11,05);
      car2.setRegistrationYear(date2);
        List<Car> CarList = new ArrayList<>();
        CarList.add(car);
       CarList.add(car2);
        
        List<Car> filteredList = CarList.stream()
                .filter(Car->Car.getRegistrationState().equals("Pune")).collect(Collectors.toList());
        
        Mockito.when(carRepository.findAll()).thenReturn(filteredList);
        assertThat(carService.getCarsByLocation("Pune")).isEqualTo(filteredList);
    }
    @Test
    public void testgetCarsByBrand() throws CarException{
        Car car = new Car();
        car.setCarId(1);;
        car.setCustomer(new Customer());
        car.setBrand("Benz");
        car.setRegistrationState("Mumbai");
       car.setModel("AMG");
        car.setVariant("43");
        LocalDate date=LocalDate.of(2019,11,05);
       car.setRegistrationYear(date);
        
       Car car2 = new Car();
       car2.setCarId(2);;
       car2.setCustomer(new Customer());
       car2.setBrand("BMW");
       car2.setRegistrationState("Pune");
      car2.setModel("X6");
       car2.setVariant("D5");
       LocalDate date2=LocalDate.of(2019,11,05);
      car2.setRegistrationYear(date2);
        List<Car> CarList = new ArrayList<>();
        CarList.add(car);
       CarList.add(car2);
        
        List<Car> filteredList = CarList.stream()
                .filter(Car->Car.getBrand().equals("BMW")).collect(Collectors.toList());
        
        Mockito.when(carRepository.findAll()).thenReturn(filteredList);
        assertThat(carService.getCarsByBrand("BMW")).isEqualTo(filteredList);
    }

    @Test
    void testupdateCar() throws CarException {
        Car car = new Car();
        car.setCarId(1);;
        car.setCustomer(new Customer());
        car.setBrand("Benz");
        car.setRegistrationState("Mumbai");
       car.setModel("AMG");
        car.setVariant("43");
        LocalDate date=LocalDate.of(2019,11,05);
       car.setRegistrationYear(date);
        
        
        Mockito.when(carRepository.findById((long) 1)).thenReturn(Optional.of(car));
        car.setBrand("BMW");
        Mockito.when(carRepository.save(car)).thenReturn(car);
        assertThat(carService.updateCar(1, car)).isEqualTo(car);
    }
    
    @Test
    void testremoveCar() throws CarException {
      Car car = new Car();
      car.setCarId(1);;
      car.setCustomer(new Customer());
      car.setBrand("Benz");
      car.setRegistrationState("Mumbai");
     car.setModel("AMG");
      car.setVariant("43");
      LocalDate date=LocalDate.of(2019,11,05);
     car.setRegistrationYear(date);
      
        
        Mockito.when(carRepository.findById((long) 1)).thenReturn(Optional.of(car));
        carService.removeCar(1);
        verify(carRepository,times(1)).deleteById((long) 1);
    }

}