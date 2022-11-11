package com.coviwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coviwin.model.VaccineInventory;

public interface VaccineInventoryRepo extends JpaRepository<VaccineInventory, Integer> {

}
