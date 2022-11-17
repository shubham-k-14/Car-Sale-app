package com.car.sale;

 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

 

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import com.car.sale.entities.Appointment;
import com.car.sale.entities.Customer;
import com.car.sale.entities.Payment;
import com.car.sale.exception.AppointmentException;
import com.car.sale.repository.AppointmentRepository;
import com.car.sale.service.AppointmentService;
@RunWith(SpringRunner.class)
@SpringBootTest

 

class AppointmentTest {
    
    @Autowired
    private AppointmentService appointmentService;
    
    @MockBean
    private AppointmentRepository appointmentRepository;
    @Test
    void testaddAppointment() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);;
        //appointment.setCustomer("Suraj","naiksaurjjjh@gmail.com","9847887585",2019-11-06);
        appointment.setCustomer(new Customer());
        appointment.setInspectionType("Available");
        appointment.setLocation("Mumbai");
        appointment.setPayment(new Payment());
        LocalDate date=LocalDate.of(2019,11,05);
        appointment.setPreferredDate(date);
        LocalTime localtime=LocalTime.of(10,43,0);
        appointment.setPreferredTime(localtime);
        
        Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
        
        assertThat(appointmentService.addAppointment(appointment)).isEqualTo(appointment);
    }
    
    @Test
    public void testgetAppointment() throws AppointmentException{
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);
        //appointment.setCustomer("Suraj","naiksaurjjjh@gmail.com","9847887585",2019-11-06);
        appointment.setCustomer(new Customer());
        appointment.setInspectionType("Available");
        appointment.setLocation("Mumbai");
        appointment.setPayment(new Payment());
        LocalDate date=LocalDate.of(2019,11,05);
        appointment.setPreferredDate(date);
        LocalTime localtime=LocalTime.of(10,43,0);
        appointment.setPreferredTime(localtime);
        //Optional<Appointment> appointmentoptional=Optional.of(appointment);
        Mockito.when(appointmentRepository.findById((long) 1)).thenReturn(Optional.of(appointment));
         assertThat(appointmentService.getAppointment(1)).isEqualTo(appointment);
         }
    
    @Test
    public void testgetAllAppointments() throws AppointmentException{
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);;
        //appointment.setCustomer("Suraj","naiksaurjjjh@gmail.com","9847887585",2019-11-06);
        appointment.setCustomer(new Customer());
        appointment.setInspectionType("Available");
        appointment.setLocation("Mumbai");
        appointment.setPayment(new Payment());
        LocalDate date=LocalDate.of(2019,11,05);
        appointment.setPreferredDate(date);
        LocalTime localtime=LocalTime.of(10,43,0);
        appointment.setPreferredTime(localtime);
        
        Appointment appointment2 = new Appointment();
        appointment.setAppointmentId(2);;
        //appointment.setCustomer("Suraj","naiksaurjjjh@gmail.com","9847887585",2019-11-06);
        appointment2.setCustomer(new Customer());
        appointment2.setInspectionType("Not Available");
        appointment2.setLocation("Delhi");
        appointment2.setPayment(new Payment());
        LocalDate date2=LocalDate.of(2017,10,07);
        appointment2.setPreferredDate(date);
        LocalTime localtime2=LocalTime.of(9,23,0);
        appointment2.setPreferredTime(localtime);
        //Optional<Appointment> appointmentoptional=Optional.of(appointment);
        List<Appointment> AppointmentList = new ArrayList<>();
        AppointmentList.add(appointment);
        AppointmentList.add(appointment2);
        
        Mockito.when(appointmentRepository.findAll()).thenReturn(AppointmentList);
        assertThat(appointmentService.getAllAppointments()).isEqualTo(AppointmentList);
    }
    
    @Test
    public void testgetOpenAppointments() throws AppointmentException{
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);;
        //appointment.setCustomer("Suraj","naiksaurjjjh@gmail.com","9847887585",2019-11-06);
        appointment.setCustomer(new Customer());
        appointment.setInspectionType("Available");
        appointment.setLocation("Mumbai");
        appointment.setPayment(new Payment());
        LocalDate date=LocalDate.of(2019,11,05);
        appointment.setPreferredDate(date);
        LocalTime localtime=LocalTime.of(10,43,0);
        appointment.setPreferredTime(localtime);
        
        Appointment appointment2 = new Appointment();
        appointment.setAppointmentId(2);;
        //appointment.setCustomer("Suraj","naiksaurjjjh@gmail.com","9847887585",2019-11-06);
        appointment2.setCustomer(new Customer());
        appointment2.setInspectionType("Not Available");
        appointment2.setLocation("Delhi");
        appointment2.setPayment(new Payment());
        LocalDate date2=LocalDate.of(2017,10,07);
        appointment2.setPreferredDate(date);
        LocalTime localtime2=LocalTime.of(9,23,0);
        appointment2.setPreferredTime(localtime);
        //Optional<Appointment> appointmentoptional=Optional.of(appointment);
        List<Appointment> AppointmentList = new ArrayList<>();
        AppointmentList.add(appointment);
        AppointmentList.add(appointment2);
        
        List<Appointment> filteredList = AppointmentList.stream()
                .filter(app -> app.getInspectionType().equals("Available")).collect(Collectors.toList());
        
        Mockito.when(appointmentRepository.findAll()).thenReturn(filteredList);
        assertThat(appointmentService.getOpenAppointments()).isEqualTo(filteredList);
    }
    
    @Test
    void testupdateAppointment() throws AppointmentException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);;
        //appointment.setCustomer("Suraj","naiksaurjjjh@gmail.com","9847887585",2019-11-06);
        appointment.setCustomer(new Customer());
        appointment.setInspectionType("Available");
        appointment.setLocation("Maharashtra");
        appointment.setPayment(new Payment());
        LocalDate date=LocalDate.of(2019,11,05);
        appointment.setPreferredDate(date);
        LocalTime localtime=LocalTime.of(10,43,0);
        appointment.setPreferredTime(localtime);
        
        Mockito.when(appointmentRepository.findById((long) 1)).thenReturn(Optional.of(appointment));
        appointment.setLocation("Vidharbha");
        Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
        assertThat(appointmentService.updateAppointment(1, appointment)).isEqualTo(appointment);
    }
    
    @Test
    void testremoveAppointment() throws AppointmentException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);;
        //appointment.setCustomer("Suraj","naiksaurjjjh@gmail.com","9847887585",2019-11-06);
        appointment.setCustomer(new Customer());
        appointment.setInspectionType("Available");
        appointment.setLocation("Maharashtra");
        appointment.setPayment(new Payment());
        LocalDate date=LocalDate.of(2019,11,05);
        appointment.setPreferredDate(date);
        LocalTime localtime=LocalTime.of(10,43,0);
        appointment.setPreferredTime(localtime);
        
        Mockito.when(appointmentRepository.findById((long) 1)).thenReturn(Optional.of(appointment));
        appointmentService.removeAppointment(1);
        verify(appointmentRepository,times(1)).deleteById((long) 1);
    }

}