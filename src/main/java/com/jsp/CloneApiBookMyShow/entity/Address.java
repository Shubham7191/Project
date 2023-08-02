package com.jsp.CloneApiBookMyShow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	private int flatNo;
	private String area;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private long pincode;
	
	/*
	  json ignore ka addres parat theatre la fetch kartoy unnessasary list madhe list yetya
	  so we use jsonignore
	 */
	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Theatre theatre;


	public long getAddressId() {
		return addressId;
	}


	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}


	public int getFlatNo() {
		return flatNo;
	}


	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getLandmark() {
		return landmark;
	}


	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public long getPincode() {
		return pincode;
	}


	public void setPincode(long pincode) {
		this.pincode = pincode;
	}


	public Theatre getTheatre() {
		return theatre;
	}


	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}


	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", flatNo=" + flatNo + ", area=" + area + ", landmark=" + landmark
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", pincode=" + pincode + ", theatre="
				+ theatre + "]";
	}


	
	
	

}
