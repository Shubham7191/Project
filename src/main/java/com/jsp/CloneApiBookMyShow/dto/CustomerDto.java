package com.jsp.CloneApiBookMyShow.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CustomerDto {
	
	
	private long customerId;
	private String customerName;
	@Min(600000001)
	@Max(999999999)
	private long customerPhone;
	private String customerEmail;

	
	

}
