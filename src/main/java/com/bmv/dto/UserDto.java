package com.bmv.dto;

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
public class UserDto {
	private int id;
	private String address;
	private String contactNumber;
	private String email;
	private String firstName;
	private String lastName;
	private int bookingId;
}
