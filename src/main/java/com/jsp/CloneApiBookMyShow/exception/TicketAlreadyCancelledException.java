package com.jsp.CloneApiBookMyShow.exception;

import lombok.Getter;

@Getter
public class TicketAlreadyCancelledException extends RuntimeException {

	private String message;
	
	public TicketAlreadyCancelledException(String message) {
		super();
		this.message = message;
	}

}
