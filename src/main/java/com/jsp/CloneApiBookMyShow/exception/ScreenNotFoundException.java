package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class ScreenNotFoundException extends RuntimeException {
	private String message ;

	public ScreenNotFoundException(String message) {
		super();
		this.message = message;
	}
	

}
