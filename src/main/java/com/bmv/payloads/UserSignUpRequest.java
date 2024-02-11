package com.bmv.payloads;

import com.bmv.entities.Role;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

public class UserSignUpRequest {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNumber;
	private String address ;
	private String password;
	private String confirmPassword; 
	private String roleName;
	private int roleId;
	
	public UserSignUpRequest() {
		
	}

	public UserSignUpRequest(int id, String firstName, String lastName, String email, String contactNumber,
			String address, String password, String confirmPassword, String roleName,int roleId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.address = address;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.roleName = roleName;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserSignUpRequest [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", contactNumber=" + contactNumber + ", address=" + address + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", roleName=" + roleName + ", roleId=" + roleId + "]";
	}

	
	
}
