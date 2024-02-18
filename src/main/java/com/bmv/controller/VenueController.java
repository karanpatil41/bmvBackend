package com.bmv.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;

import com.bmv.entities.User;
import com.bmv.entities.Venue;
import com.bmv.payloads.VenueResponse;
import com.bmv.services.VenueService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/venue")
public class VenueController {

	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

	@Autowired
	private VenueService venueService;

	@PostMapping("/createVenue")
	public ResponseEntity<Venue> createVenue(@RequestParam("image") MultipartFile image,
			@RequestParam("username") String username, @RequestParam("venueName") String venueName,
			@RequestParam("address") String address, @RequestParam("capacity") Integer capacity,
			@RequestParam("amount") Integer amount, @RequestParam("description") String description,
			@RequestParam("contactNumber") String contactNumber,@RequestParam("venueManagerId") User venueManagerId, Authentication authentication) {
		// Get the authenticated user's username
		String tokenUsername = authentication.getName();
		logger.info("Authenticated username= " + username);
		logger.info("Venue Controller: createVenue()");

		try {
			Venue venue = new Venue();
			venue.setVenueName(venueName);
			venue.setUsername(username);
			venue.setCreatedBy(tokenUsername);
//			venue.setLastUpdatedBy(tokenUsername);
			venue.setAddress(address);
			venue.setCapacity(capacity);
			venue.setAmount(amount);
			venue.setDescription(description);
			venue.setContactNumber(contactNumber);
			venue.setVenueManagerId(venueManagerId);
			venue.setImage(image.getBytes());
			
			// Set createDate and lastUpdatedDate with current LocalDateTime
			LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current date and time
			venue.setCreatedDate(currentDateTime); // Set createDate
//            venue.setLastUpdatedDate(currentDateTime); // Set lastUpdatedDate

			logger.info("Venue Controller: createVenue()" + venue.toString());
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
		System.out.println("In Veune Controller: getAllVenues()");
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

			// Convert image to base64
			String base64Image = Base64.getEncoder().encodeToString(venue.getImage());
			venueResponse.setImage(base64Image);

			return new ResponseEntity<>(venueResponse, HttpStatus.OK);
		} else {
			// Venue not found, return 404 Not Found status
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/search")
	public ResponseEntity<List<VenueResponse>> getVenueByAddressAndCapacity(@RequestParam("city") String address,
			@RequestParam("capacity") Integer capacity) {
		System.out.println("In Venue Controller: getVenueByCityAndCapacity()");
		List<Venue> allVenues = venueService.getVenueByAddressAndCapacity(address, capacity);
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

			venueResponses.add(venueResponse);// venueResponse added to the->list of venueResponses.
		}
		return new ResponseEntity<>(venueResponses, HttpStatus.OK);

	}

	@GetMapping("/getVenueByUsername")
	public ResponseEntity<List<VenueResponse>> getVenueByUsername(@RequestParam("username") String username) {
		logger.info("In Venue Controller: getVenueByUsername()");
		List<Venue> venueList = venueService.getVenueByUsername(username);
		List<VenueResponse> venueResponses = new ArrayList<>();

		for (Venue venue : venueList) {
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

			venueResponses.add(venueResponse);// venueResponse added to the->list of venueResponses.
		}
		return new ResponseEntity<>(venueResponses, HttpStatus.OK);
	}

	@PatchMapping("/updateVenue")
	public ResponseEntity<String> updateVenueById(@RequestParam("id") int id, @RequestBody Map<String, Object> updates) {
		logger.info("VenueController updateUser() updates="+updates);
//		logger.info("UserController's updateUser() updates="+updates.toString());
		System.out.println("In VenueController ");
		updates.entrySet().forEach(e-> System.out.println(e));
		Venue updatedVenue = venueService.updateVenueById(id, updates);
		logger.info("VenueController updateVenueById() updatedVenue =",updatedVenue );
		
		return new ResponseEntity<>("Venue updated successfully.", HttpStatus.OK);
	}
	@GetMapping("/findVenueAndUserData/{id}")
	public ResponseEntity<Object> getVenueAndUserData(@PathVariable Integer id){
		logger.info("VenueController--getVenueAndUserData()");
		Object venueAndUserDataList = venueService.getVenueAndUserData(id);
		logger.info("VenueController venueAndUserDataList=");
		return new ResponseEntity<Object>(venueAndUserDataList,HttpStatus.OK);
	}
}
