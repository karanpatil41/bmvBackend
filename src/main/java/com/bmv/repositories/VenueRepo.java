package com.bmv.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmv.entities.Venue;

public interface VenueRepo extends JpaRepository<Venue, Integer> {
	
	List<Venue> findByAddressAndCapacity(String address, Integer capacity);

}
