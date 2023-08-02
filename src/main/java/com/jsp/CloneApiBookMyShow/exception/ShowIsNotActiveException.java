package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class ShowIsNotActiveException extends RuntimeException {

	
	private String message;

	public ShowIsNotActiveException(String message) {
		this.message = message;
	}

	
}
