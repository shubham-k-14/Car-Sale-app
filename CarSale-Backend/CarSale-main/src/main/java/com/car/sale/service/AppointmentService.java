package com.car.sale.service;

import java.util.List;

import com.car.sale.entities.*;
import com.car.sale.exception.AppointmentException;

public interface AppointmentService {
	public Appointment addAppointment(Appointment appointment);

	public Appointment removeAppointment(long id) throws AppointmentException;

	public Appointment updateAppointment(long id, Appointment appointment) throws AppointmentException;

	public Appointment getAppointment(long id) throws AppointmentException;

	public List<Appointment> getAllAppointments() throws AppointmentException;

	public List<Appointment> getOpenAppointments() throws AppointmentException;
}
