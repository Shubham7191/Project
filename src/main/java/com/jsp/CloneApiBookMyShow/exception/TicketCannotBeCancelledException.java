package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class TicketCannotBeCancelledException extends RuntimeException {

	
	private String message;

	public TicketCannotBeCancelledException(String message) {
		super();
		this.message = message;
	}
	
	
}
