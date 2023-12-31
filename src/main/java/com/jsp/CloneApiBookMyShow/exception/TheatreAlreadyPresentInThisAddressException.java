package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class TheatreAlreadyPresentInThisAddressException extends RuntimeException {

	private String message;

	public TheatreAlreadyPresentInThisAddressException(String message) {
		super();
		this.message = message;
	}

}
