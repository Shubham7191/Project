package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class TickerIdNotFoundException extends RuntimeException {

	
	public TickerIdNotFoundException(String message) {
		this.message = message;
	}

	private String message ;
}
