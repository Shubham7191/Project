package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class MovieIdNotFoundException extends RuntimeException {

	private String message;

	public MovieIdNotFoundException(String message) {
		this.message = message;
	}

}
