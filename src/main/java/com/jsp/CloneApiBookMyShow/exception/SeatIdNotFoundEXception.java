package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class SeatIdNotFoundEXception extends RuntimeException {

	
	public SeatIdNotFoundEXception(String message) {
		this.message = message;
	}

	private String message;
	
}
