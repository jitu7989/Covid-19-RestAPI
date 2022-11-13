package com.coviwin.service;

import java.util.List;

import com.coviwin.exception.ApppintmentException;
import com.coviwin.exception.MemberException;
import com.coviwin.model.Appointment;

public interface AppointmentService {

	public List<Appointment> allAppointment()throws ApppintmentException;
	

	public Appointment getAppointment(Long bookingId)throws ApppintmentException;
	
	public Appointment addAppointment( Integer memid,  Appointment app)throws ApppintmentException, MemberException ;
	
	public Appointment updateAppointment(Appointment app)throws ApppintmentException;
	
	public Boolean deleteAppointment(Appointment app)throws ApppintmentException; 
	
}