package com.bmv.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class VenueResponse {
	private int id;
	private String username;
	private String venueName;
	private String address;
	private Integer capacity;
	private Integer amount;
	private String image;
	private String description;
	private String contactNumber;

	public VenueResponse() {

	}

	public VenueResponse(int id, String username, String venueName, String address, Integer capacity, Integer amount,
			String image, String description, String contactNumber) {
		super();
		this.id = id;
		this.username = username;
		this.venueName = venueName;
		this.address = address;
		this.capacity = capacity;
		this.amount = amount;
		this.image = image;
		this.description = description;
		this.contactNumber = contactNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "VenueResponse [id=" + id + ", username=" + username + ", venueName=" + venueName + ", address="
				+ address + ", capacity=" + capacity + ", amount=" + amount + ", image=" + image + ", description="
				+ description + ", contactNumber=" + contactNumber + "]";
	}
	
}
