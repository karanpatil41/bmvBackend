package com.bmv.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bmv.dto.BookingDto;
import com.bmv.dto.UserDto;
import com.bmv.dto.VenueDTO;
import com.bmv.entities.Booking;
import com.bmv.payloads.BookingRequestResponse;
import com.bmv.services.BookingService;
import com.bmv.services.VenueService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private VenueService venueService;
	
	
	@PostMapping("/book")
	public ResponseEntity<String> bookVenue(@RequestBody Booking booking){
		booking.setCreatedDate(LocalDateTime.now());
		booking.setBookingDate(LocalDateTime.now());
		logger.info("BookingController--bookVenue()--booking="+booking);	
		Booking savedBooking = bookingService.bookVenue(booking);
		logger.info("BookingController--bookVenue()--booking="+savedBooking);
		return new ResponseEntity<String>("Pay for confirmation",HttpStatus.OK);
	}

	


//3rd-query--	select u.address,u.contact_number,u.email,u.first_name,u.last_name,b.booking_id  from user u join booking b ON u.id=b.user_id where b.booking_id=14;
	//On booking_id ,fetch details of user
	@GetMapping("/request")
	public ResponseEntity<BookingRequestResponse> getBookingRequest(@RequestParam("userId") int userid){
		logger.info("BookingController--getBookingRequest()="+userid);
//		1st query--select id,venue_name,amount,address from venue where venue_manager_id=14; //You will get list of venues of userid=14
		// Retrieve venue list
		List<VenueDTO> venueListByVenueManagerId=venueService.findByVenueManagerIdId( userid);//You get in response --venueid,venueName,amount,address
		// Create the response object
		BookingRequestResponse response = new BookingRequestResponse();
		
		// Set the venue list in the response
	    response.setVenueList(venueListByVenueManagerId);
	    
	    // Initialize the booking list
	    response.setBookingList(new ArrayList<>());
	    response.setUserList(new ArrayList<>());

		for( VenueDTO venueDto : venueListByVenueManagerId) {
			 System.out.println("Venue ID: " + venueDto.getId());
			    System.out.println("Venue Name: " + venueDto.getVenueName());
			    System.out.println("Amount: " + venueDto.getAmount());
			    System.out.println("Address: " + venueDto.getAddress());
			    
//				2nd query--select booking_id,booking_date,checkin_date,checkout_date,no_of_guests,payment_status from booking where venue_id=8;//Iterate the list of venues 
				//and fetch booking id and its details
			    // Fetch booking list for the current venue
			    List<BookingDto> bookingListByVenueId = bookingService.getBookingsByVenueId(venueDto.getId());
			    
			 // Iterate over each booking and set the venueId
			    for(BookingDto bookingDto:bookingListByVenueId ) {
					System.out.println("Booking ID: " + bookingDto.getBookingId());
					System.out.println("Venue ID: " + bookingDto.getVenueId());
				    System.out.println("Booking Date: " + bookingDto.getBookingDate());
				    System.out.println("CheckinDate: " + bookingDto.getCheckinDate());
				    System.out.println("CheckoutDate: " + bookingDto.getCheckoutDate());
				    System.out.println("NoOfGuests: " + bookingDto.getNoOfGuests());
				    System.out.println("PaymentStatus: " + bookingDto.getPaymentStatus());
				    
				 // Call getUserDetailsByBookingId for each bookingId
				    List<UserDto> userDetailsByBookingId = bookingService.getUserDetailsByBookingId(bookingDto.getBookingId());
				    
				    for(UserDto userDto : userDetailsByBookingId) {
				    	 System.out.println("User ID: " + userDto.getId());
			                System.out.println("Address: " + userDto.getAddress());
			                System.out.println("Contact Number: " + userDto.getContactNumber());
			                System.out.println("Email: " + userDto.getEmail());
			                System.out.println("First Name: " + userDto.getFirstName());
			                System.out.println("Last Name: " + userDto.getLastName());
			                // Add user details to the response
			                response.getUserList().add(userDto);
				    }
				}
			 // Add all bookings for the current venue to the response
			    response.getBookingList().addAll(bookingListByVenueId);
		}
		
		return new ResponseEntity<BookingRequestResponse>(response,HttpStatus.OK);
	}
	
}
