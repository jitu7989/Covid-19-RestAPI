package com.coviwin.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.VaccineInventory;

public interface VaccineInventoryRepo extends JpaRepository<VaccineInventory, Integer> {

	public List<VaccineInventory> findBydate(LocalDate date);
	
}
