package com.booking.app.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@Table(name = "userContactInformationTable")
@JsonIgnoreType
public class UserContactInformation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userContactInfo_Id;
	private String city;
	private String streetAddress;
	private String addressline2;
	private String province;
	private String country;
	private Long postalCode;
	
	@OneToOne 
	private User user;
	
	public Long getUserContactInfo_Id() {
		return userContactInfo_Id;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city.toUpperCase().trim();
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress.toUpperCase().trim();
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2.toUpperCase().trim();
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province.toUpperCase().trim();
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country.toUpperCase().trim();
	}
	public Long getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}
	
	public UserContactInformation(String city, String streetAddress, String addressline2,
			String province, String country, Long postalCode, User user) {
		
		this.city = city;
		this.streetAddress = streetAddress;
		this.addressline2 = addressline2;
		this.province = province;
		this.country = country;
		this.postalCode = postalCode;
		this.user = user;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserContactInformation() {
		
	}
	
	
	

	
	
	
	
	
}
