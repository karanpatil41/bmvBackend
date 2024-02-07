package com.bmv.services.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmv.entities.Venue;
import com.bmv.repositories.VenueRepo;
import com.bmv.services.VenueService;

@Service
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueRepo venueRepo;

	@Override
	public Venue createVenue(Venue venue) {
		System.out.println("Create Venue Service: " + venue.toString());
		 Venue addedVenue = venueRepo.save(venue);
		return addedVenue;
	}

	@Override
	public List<Venue> getAllVenues() {
		List<Venue> allVenues = venueRepo.findAll();
		return allVenues;
	}

	@Override
	public Optional<Venue> getVenueById(Integer id) {
		Optional<Venue> venue = venueRepo.findById(id);
		return venue;
	}

}
