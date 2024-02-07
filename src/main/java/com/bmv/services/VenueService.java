package com.bmv.services;

import java.util.List;
import java.util.Optional;

import com.bmv.entities.Venue;


public interface VenueService {

	Venue createVenue(Venue venue);
	
	List<Venue> getAllVenues();
	
	Optional<Venue> getVenueById(Integer id);
	
	List<Venue> getVenueByAddressAndCapacity(String address ,Integer capacity);
}
