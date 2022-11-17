package com.car.sale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.sale.entities.Appointment;
import com.car.sale.exception.AppointmentException;
import com.car.sale.repository.AppointmentRepository;
import java.util.stream.Collectors;

@Transactional
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;

	@Override
	public Appointment addAppointment(Appointment appointment) {

		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment removeAppointment(long id) throws AppointmentException {
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
		if (appointmentOptional.isEmpty()) {
			throw new AppointmentException("This id doesn't exists");
		}
		appointmentRepository.deleteById(id);
		return appointmentOptional.get();

	}

	@Override
	public Appointment updateAppointment(long id, Appointment appointment) throws AppointmentException {
		Appointment oldAppointment = appointmentRepository.findById(id).orElse(null);
		oldAppointment.setCustomer(appointment.getCustomer());
		oldAppointment.setInspectionType(appointment.getInspectionType());
		oldAppointment.setLocation(appointment.getLocation());
		// oldAppointment.setPayment(appointment.getPayment());
		oldAppointment.setPreferredDate(appointment.getPreferredDate());
		oldAppointment.setPreferredTime(appointment.getPreferredTime());
		return appointmentRepository.save(oldAppointment);
	}

	@Override
	public Appointment getAppointment(long id) throws AppointmentException {
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
		if (appointmentOptional.isEmpty()) {
			throw new AppointmentException("This Appointment id doesn't exists");
		}
		return appointmentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Appointment> getAllAppointments() throws AppointmentException {
		List<Appointment> appointmentOptional = appointmentRepository.findAll();
		if (appointmentOptional.isEmpty()) {
			throw new AppointmentException("No Appointment exists");
		}
		return appointmentRepository.findAll();
	}

	@Override
	public List<Appointment> getOpenAppointments() throws AppointmentException {
		List<Appointment> appointments = appointmentRepository.findAll();
		List<Appointment> filteredList = appointments.stream()
				.filter(app -> app.getInspectionType().equals("Available")).collect(Collectors.toList());
		if (filteredList.isEmpty()) {
			throw new AppointmentException("No Open Appointment exists");
		}
		return filteredList;
	}

}
