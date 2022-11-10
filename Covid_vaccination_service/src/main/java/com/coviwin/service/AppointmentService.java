package com.coviwin.service;

import java.util.List;

import com.coviwin.model.Appointment;

public interface AppointmentService {

//	Hello
	public List<Appointment> allAppointment();
	
	public Appointment getAppointment(Long bookingId);
	
	public Appointment addAppointment(Appointment app);
	
	public Appointment updateAppointment(Appointment app);
	
	public Boolean deleteAppointment(Appointment app);
	
}