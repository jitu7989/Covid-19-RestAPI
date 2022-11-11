package com.coviwin.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.coviwin.exception.ApppintmentException;
import com.coviwin.model.Appointment;
import com.coviwin.model.Member;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.repo.AppointmentRepo;
import com.coviwin.service.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepo appRepo;
	
	
	@Override
	public List<Appointment> allAppointment() throws ApppintmentException {

		List<Appointment> list = appRepo.findAll();
		
		if(list.isEmpty()) {
			throw new ApppintmentException("No Appointment found..");
		}
		
		return list;
	}


	@Override
	public Appointment getAppointment(Long bookingId) throws ApppintmentException {
		
	  return appRepo.findById(bookingId).orElseThrow(() -> new ApppintmentException("No appointment found with bookingId : "+ bookingId));
	}

	
	@Override
	public Appointment addAppointment(Appointment app) throws ApppintmentException {
		
		Optional<Appointment> opt = appRepo.findById(app.getBookingID());
		
		if(opt.isPresent()) {
			throw new ApppintmentException("Appointment is already present with bookingId : " + app.getBookingID());
		}
		
		Member member = app.getMember();
		
		member.getAppointments().add(app); // associating member with appointment
		
	    VaccinationCenter vacCenter = app.getVaccinationCenter();
	    
	    vacCenter.getAppointments().add(app); // associating VaccinationCenter with appointment
	    
		
		return appRepo.save(app);
	}

	
	@Override
	public Appointment updateAppointment(Appointment app) throws ApppintmentException {
		
        Optional<Appointment> opt = appRepo.findById(app.getBookingID());
		
		if(opt.isPresent()) {
			
			Member member = app.getMember();
	
			
			member.getAppointments().add(app); // associating member with appointment
			
		    VaccinationCenter vacCenter = app.getVaccinationCenter();
		    
		    vacCenter.getAppointments().add(app); // associating VaccinationCenter with appointment
		    
			
			return appRepo.save(app);
			
		}else
			throw new ApppintmentException("No appointment is present with appoinment details : " + app);
		
	
		
	}

	
	@Override
	public Boolean deleteAppointment(Appointment app) throws ApppintmentException {
		
        Optional<Appointment> opt = appRepo.findById(app.getBookingID());
		
		if(opt.isPresent()) {
			
			appRepo.delete(app);
			return true;
			
		}else
			throw new ApppintmentException("No appointment is present with appoinment details : " + app);
		
	}

}
