package com.jsp.CloneApiBookMyShow.dto;

import com.jsp.CloneApiBookMyShow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SeatDto {
	
		
		private long seatId;
		
		// seat type 
		private SeatType seatType;
	

}
