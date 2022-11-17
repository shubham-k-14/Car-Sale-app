package com.car.sale.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.sale.entities.Appointment;
import com.car.sale.exception.AppointmentException;
import com.car.sale.service.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;

	@PostMapping("/addAppointment")
	public ResponseEntity addAppointment(@Valid @RequestBody Appointment appointment,BindingResult br) {
    if(br.hasErrors())
    {
        String error = "";
        for(ObjectError err : br.getAllErrors())
        {
            error = error + "\n" + err.getDefaultMessage();
        }
        return new ResponseEntity<String>(error, HttpStatus.UNAUTHORIZED);
     }
    appointment = appointmentService.addAppointment(appointment);	
    ResponseEntity<Appointment> ra= new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
		return ra;
	}

	@GetMapping("/appointments/{id}")
	public ResponseEntity<Object> getAppointment(@PathVariable long id) {
		try {
			Appointment appointment = appointmentService.getAppointment(id);
			return new ResponseEntity<Object>(appointment, HttpStatus.OK);
		} catch (AppointmentException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/appointments")
	public ResponseEntity<Object> getAllAppointments() {
		try {
			List<Appointment> appointments = appointmentService.getAllAppointments();
			return new ResponseEntity<Object>(appointments, HttpStatus.OK);
		} catch (AppointmentException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/openAppointments")
	public ResponseEntity<Object> getOpenAppointments() {
		try {
			List<Appointment> appointments = appointmentService.getOpenAppointments();
			return new ResponseEntity<Object>(appointments, HttpStatus.OK);
		} catch (AppointmentException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeAppointment(@PathVariable long id) {
		try {
			Appointment appointment = appointmentService.removeAppointment(id);
			return new ResponseEntity<Object>(appointment, HttpStatus.OK);
		} catch (AppointmentException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateAppointment/{id}")
	public ResponseEntity<Object> updateAppointment(@Valid @PathVariable long id,
			@RequestBody Appointment appointment) {
		try {
			Appointment appointment2 = appointmentService.updateAppointment(id, appointment);
			return new ResponseEntity<Object>(appointment2, HttpStatus.OK);
		} catch (AppointmentException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
