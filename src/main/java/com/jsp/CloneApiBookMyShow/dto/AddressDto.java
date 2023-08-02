package com.jsp.CloneApiBookMyShow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private long addressId;
	private int flatNo;
	@NotNull(message =  "area can not be null ")
	@NotBlank(message = "area can not be blank ")
	private String area;
	@NotNull(message = "Landamrak can't be null")
	@NotBlank(message = "landdmark cant be blank")
	private String landmark;
	private String city;
	private String state;
	private String country;
	private long pincode;

}
