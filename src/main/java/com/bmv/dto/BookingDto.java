package com.bmv.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookingDto {

	private int bookingId;
	private int venueId;
	private LocalDateTime bookingDate;
	private LocalDateTime checkinDate;
	private LocalDateTime checkoutDate;
	private int noOfGuests;
	private String paymentStatus;

}
