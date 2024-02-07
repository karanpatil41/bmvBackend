package com.bmv.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bmv.entities.Venue;
import com.bmv.payloads.VenueResponse;
import com.bmv.services.VenueService;

@RestController
@CrossOrigin
@RequestMapping("/api/venue")
public class VenueController {

	@Autowired
	private VenueService venueService;

	@PostMapping("/createVenue")
	public ResponseEntity<Venue> createVenue(@RequestParam("image") MultipartFile image,
			@RequestParam("username") String username, @RequestParam("venueName") String venueName,
			@RequestParam("address") String address, @RequestParam("capacity") Integer capacity,
			@RequestParam("amount") Integer amount, @RequestParam("description") String description,
			@RequestParam("contactNumber") String contactNumber) {

		System.out.println("In Venue Controller.");
		try {
			Venue venue = new Venue();
			venue.setVenueName(venueName);
			venue.setUsername(username);
			venue.setAddress(address);
			venue.setCapacity(capacity);
			venue.setAmount(amount);
			venue.setDescription(description);
			venue.setContactNumber(contactNumber);
			venue.setImage(image.getBytes());
			System.out.println("Venue Controller= " + venue.toString());
			Venue addedVenue = this.venueService.createVenue(venue);
			String msg = "User added successfully";
			return new ResponseEntity<Venue>(addedVenue, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllVenues")
	public ResponseEntity<List<VenueResponse>> getAllVenues() {
		System.out.println("In Veune Controller: ");
		List<Venue> allVenues = this.venueService.getAllVenues();
		List<VenueResponse> venueResponses = new ArrayList<>();

		for (Venue venue : allVenues) {
			VenueResponse venueResponse = new VenueResponse();
			venueResponse.setId(venue.getId());
			venueResponse.setVenueName(venue.getVenueName());
			venueResponse.setCapacity(venue.getCapacity());
			venueResponse.setUsername(venue.getUsername());
			venueResponse.setAddress(venue.getAddress());
			venueResponse.setAmount(venue.getAmount());
			venueResponse.setContactNumber(venue.getContactNumber());
			venueResponse.setDescription(venue.getDescription());

			String base64Image = Base64.getEncoder().encodeToString(venue.getImage());
			venueResponse.setImage(base64Image);
			venueResponses.add(venueResponse);
		}

		return new ResponseEntity<>(venueResponses, HttpStatus.OK);
	}

	@GetMapping("/venueDetails")
	public ResponseEntity<VenueResponse> getVenueById(@RequestParam("id") int id) {
		System.out.println("In Venue Controller: getVenueById()");
		Optional<Venue> optionalVenue = venueService.getVenueById(id);

		// Check if the optional contains a value
		if (optionalVenue.isPresent()) {

			Venue venue = optionalVenue.get();// Retrieve the venue object from the optional
			// Convert venue to response object
			VenueResponse venueResponse = new VenueResponse();
			venueResponse.setId(venue.getId());
			venueResponse.setVenueName(venue.getVenueName());
			venueResponse.setCapacity(venue.getCapacity());
			venueResponse.setUsername(venue.getUsername());
			venueResponse.setAddress(venue.getAddress());
			venueResponse.setAmount(venue.getAmount());
			venueResponse.setContactNumber(venue.getContactNumber());
			venueResponse.setDescription(venue.getDescription());
			
			//Convert image to base64
			String base64Image =Base64.getEncoder().encodeToString(venue.getImage());
			venueResponse.setImage(base64Image);
			
			return new ResponseEntity<>(venueResponse, HttpStatus.OK);
		}else {
			// Venue not found, return 404 Not Found status
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
