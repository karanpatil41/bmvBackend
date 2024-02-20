package com.bmv.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.dto.VenueDTO;
import com.bmv.entities.Venue;
import com.bmv.exception.ResourceNotFoundException;
import com.bmv.repositories.VenueRepo;
import com.bmv.services.VenueService;

import jakarta.transaction.Transactional;

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

	@Override
	@Transactional
	public List<Venue> getVenueByUsername(String email) {
		logger.info("VenueServiceImpl getVenueByUsername()" + email);
		List<Venue> venueList = venueRepo.getVenueByUsername(email);

		logger.info("VenueServiceImpl getVenueByUsername()" + venueList.toString());
		for (Venue venue : venueList) {
			System.out.println(venue.toString());
		}
		return venueList;
	}

	@Override
	public Venue updateVenueById(int id, Map<String, Object> updates) {
//		logger.info("VenueServiceImpl--updateVenueById() id=" + id + " updates " + updates);
//		updates.entrySet().forEach(e -> System.out.println(e));
		System.out.println("VenueServiceImpl's updateVenueById()");
		Venue existingVenue = venueRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
//		System.out.println("VenueServiceImpl--updateVenueById() existingVenue=" + existingVenue);
//		logger.info("VenueServiceImpl--updateVenueById() existingVenue=" + existingVenue);

		for (Entry<String, Object> entry : updates.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			logger.info("key= " + key + " value= " + value);
			switch (key) {
			case "id":

				break;
			case "username":
				existingVenue.setLastUpdatedBy((String) value);
				break;
			case "venueName":
				existingVenue.setVenueName((String) value);
				break;
			case "address":
				existingVenue.setAddress((String) value);
				break;
			case "capacity":
				existingVenue.setCapacity(Integer.parseInt(value.toString()));
				break;
			case "amount":
				
				existingVenue.setAmount(Integer.parseInt(value.toString()));
				break;
			case "image":

				break;
			case "description":
				existingVenue.setDescription((String) value);
				break;
			case "contactNumber":
				existingVenue.setContactNumber((String) value);
				break;
			case "createdBy":
				
				break;
			case "createdDate":

				break;
			case "lastUpdatedBy":
//				existingVenue.setLastUpdatedBy((String) value);
				break;
			case "lastUpdatedDate":
				existingVenue.setLastUpdatedDate(LocalDateTime.now());
				break;
			case "password":
				
				break;
			case "confirmPassword":
				
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + key);
			}
		};
		return venueRepo.save(existingVenue);

	}

	@Override
	public Object getVenueAndUserData(Integer id) {
		logger.info("VenueServiceImpl--getVenueAndUserData()");
		Object venueAndUserDataList = venueRepo.findVenueAndUserData( id);
		logger.info("VenueServiceImpl--getVenueAndUserData()--venueAndUserDataList="+venueAndUserDataList);
		return venueAndUserDataList;
	}

	@Override
	public List<VenueDTO> findByVenueManagerIdId(int userid) {
		logger.info("VenueServiceImpl--findByVenueManagerIdId()="+userid);
		 List<VenueDTO> venueListByVenueManagerId = venueRepo.findByVenueManagerIdId(userid);
		 return venueListByVenueManagerId;
	}

}
