package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class ScreenIdNotFoundException extends RuntimeException {

	private String message;

	public ScreenIdNotFoundException(String message) {
		this.message = message;
	}

}
