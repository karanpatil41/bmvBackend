package com.bmv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmv.entities.Venue;

public interface VenueRepo extends JpaRepository<Venue, Integer> {
	

}
