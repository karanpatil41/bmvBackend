package com.bmv.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bmv.entities.Venue;


public interface VenueService {

	Venue createVenue(Venue venue);
	
	List<Venue> getAllVenues();
	
}
