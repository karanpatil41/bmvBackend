package com.bmv.services.impl;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.entities.Venue;
import com.bmv.repositories.VenueRepo;
import com.bmv.services.VenueService;

@Service
public class VenueServiceImpl implements VenueService {
	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private VenueRepo venueRepo;

	@Override
	public Venue createVenue(Venue venue) {
		logger.info("VenueServiceImpl createVenue(): " + venue.toString());
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

	@Override
	public List<Venue> getVenueByAddressAndCapacity(String address, Integer capacity) {
		List<Venue> allVenues = venueRepo.findByAddressAndCapacity(address, capacity);
		return allVenues;
	}

}
