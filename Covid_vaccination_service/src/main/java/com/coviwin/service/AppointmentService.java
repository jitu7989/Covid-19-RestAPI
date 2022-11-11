package com.coviwin.service;

import java.util.List;

import com.coviwin.model.Appointment;

public interface AppointmentService {

	public List<Appointment> allAppointment();
	
	public Appointment getAppointment(Long bookingId); //m
	
	public Appointment addAppointment(Appointment app); //m
	
	public Appointment updateAppointment(Appointment app); //m
	
	public Boolean deleteAppointment(Appointment app); //m
	
}