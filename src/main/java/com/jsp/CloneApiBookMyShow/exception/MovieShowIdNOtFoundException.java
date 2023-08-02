package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter

public class MovieShowIdNOtFoundException extends RuntimeException {

	
	
	private String message;

	public MovieShowIdNOtFoundException(String message) {
		
		this.message = message;
	}
	

}
