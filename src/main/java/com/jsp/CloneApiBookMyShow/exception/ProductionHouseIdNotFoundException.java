package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class ProductionHouseIdNotFoundException extends RuntimeException {
	
	private String  message;
	

	public ProductionHouseIdNotFoundException(String message) {
		
		super();
		this.message= message;
	}
	
	

}
