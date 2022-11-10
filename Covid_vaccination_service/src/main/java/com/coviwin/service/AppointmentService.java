package com.coviwin.service;

public interface AppointmentService {

	public List<Appointment> allAppointment();
	
	public Appointment getAppointment(Long bookingId);
	
	public Appointment addAppointment(Appointment app);
	
	public Appointment updateAppointment(Appointment app);
	
	public Boolean deleteAppointment(Appointment app);
	
}
