package com.bmv.payloads;

import java.util.List;

import com.bmv.dto.BookingDto;
import com.bmv.dto.UserDto;
import com.bmv.dto.VenueDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookingRequestResponse {
	private List<VenueDTO> venueList;
	private List<BookingDto> bookingList;
	private List<UserDto> userList;
	
}
