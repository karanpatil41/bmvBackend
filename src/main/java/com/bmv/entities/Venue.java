package com.bmv.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Venue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String venueName;
	private String address;
	private Integer capacity;
	private Integer amount;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;
	private String description;
	private String contactNumber;
	private String createdBy;
	private LocalDateTime createdDate;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdatedDate;
	
	public Venue() {

	}

	
	

	public Venue(int id, String username, String venueName, String address, Integer capacity, Integer amount,
			byte[] image, String description, String contactNumber, String createdBy, LocalDateTime createdDate,
			String lastUpdatedBy, LocalDateTime lastUpdatedDate) {
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
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
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




	public byte[] getImage() {
		return image;
	}




	public void setImage(byte[] image) {
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




	public String getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public LocalDateTime getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}




	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}




	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}




	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}




	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}




	@Override
	public String toString() {
		return "Venue [id=" + id + ", username=" + username + ", venueName=" + venueName + ", address=" + address
				+ ", capacity=" + capacity + ", amount=" + amount + ", image=" + Arrays.toString(image)
				+ ", description=" + description + ", contactNumber=" + contactNumber + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDate="
				+ lastUpdatedDate + "]";
	}
}
