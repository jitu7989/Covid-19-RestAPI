package com.coviwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.VaccineRegistration;

public interface VaccineRegistrationRepo extends JpaRepository<VaccineRegistration, Long> {

}
