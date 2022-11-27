package com.coviwin.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviwin.exception.ApppintmentException;
import com.coviwin.exception.MemberException;
import com.coviwin.model.Appointment;
import com.coviwin.model.Member;
import com.coviwin.model.VaccinationCenter;
import com.coviwin.model.VaccineRegistration;
import com.coviwin.repo.AppointmentRepo;
import com.coviwin.repo.MemberRepo;
import com.coviwin.repo.VaccinationCenterRepo;
import com.coviwin.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepo appRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private VaccinationCenterRepo vaccinationCenterRepo;
	
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
	public Appointment addAppointment(Integer memid  , Appointment app) throws ApppintmentException, MemberException {
		
		Member member = memberRepo.findById(memid).orElseThrow( ()->new MemberException( "No member found with that id" ) ); 
		
		app.setMember(member);
		
		if(member.getAppointments()==null) member.setAppointments( new ArrayList<>() );
		member.getAppointments().add(app);
		
		if(app.getBookingID() != null) {
			
			Optional<Appointment> opt = appRepo.findById(app.getBookingID());
			
			if(opt.isPresent()) throw new ApppintmentException("Appointment is already present with bookingId : " + app.getBookingID());
				
		}
		
		
		VaccinationCenter vacCenter;
		
		if(app.getVaccinationCenter()!=null) {
			vacCenter = app.getVaccinationCenter();
			if(vacCenter.getAppointments() != null)
			vacCenter.getAppointments().add(app); // associating VaccinationCenter with appointment
		}
		else {
			
			List<VaccinationCenter> vCenters = vaccinationCenterRepo.findAll();
			int x = vCenters.size();
			int n = (int) (Math.random(  )*x);
			vacCenter =  vCenters.get(n);
			if(vacCenter.getAppointments() != null)
			vacCenter.getAppointments().add(app);
			
		}
		
		app.setVaccinationCenter(vacCenter);
		

//			
//		VaccineRegistration vRegistration =  new VaccineRegistration();
//			
//		vRegistration.setDateofregistration( LocalDate.now() );
//		
//		if(vRegistration.getMembers()==null)  vRegistration.setMembers( new ArrayList<Member>());
//		
//		vRegistration.getMembers().add(member);
//		
//		vRegistration.setMobileno( Long.parseLong(app.getMobileNo()) );
//			
//		member.setVaccineRegistration(vRegistration);

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
