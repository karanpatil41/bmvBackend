package com.bmv.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "venueId")
	private Venue venue;
	
	private LocalDateTime bookingDate;
	private LocalDateTime checkinDate;
	private LocalDateTime checkoutDate;
	private Integer noOfGuests;
	private String status;
	private Integer amount;
	private String createdBy;
	private LocalDateTime createdDate;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdatedDate;
	
}
