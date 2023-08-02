package com.jsp.CloneApiBookMyShow.exception;

public class ScreenAlreadyAlloted extends RuntimeException {

	private String message;
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public ScreenAlreadyAlloted(String message) {
		this.message = message;
	}

}
