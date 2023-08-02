package com.jsp.CloneApiBookMyShow.dto;

import com.jsp.CloneApiBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneApiBookMyShow.enums.ScreenStatus;
import com.jsp.CloneApiBookMyShow.enums.ScreenType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ScreenDto {

	private long screenId;
	private String screenName;
	private int noOfClassicSeat;
	private int noOfPlatinumSeat;
	private int noOfGoldSeat;


	// screen Type
	private ScreenType screenType;
	// screen availability
	private ScreenAvailability screenAvailability;
	// screen status
	private ScreenStatus screenStatus;

	
}
