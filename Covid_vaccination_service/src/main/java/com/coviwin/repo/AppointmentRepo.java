package com.coviwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

}
