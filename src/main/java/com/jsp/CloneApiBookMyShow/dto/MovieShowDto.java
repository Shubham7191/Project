package com.jsp.CloneApiBookMyShow.dto;

import java.time.LocalDateTime;

import com.jsp.CloneApiBookMyShow.enums.ShowStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieShowDto {

	private long showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
	private String showLocation;
	private long movieId;
	private String movieName;
	private LocalDateTime movieDuration;
	private String movieDescription;
	private String movieLanguage;

	private long screenId;
	private String screenName;
	private double classicSeatPrice;
	private double goldenSeatPrice;
	private double premimumSeatPrice;
	
	

	// show status

	private ShowStatus showStatus;

	// genre // this is all enum

	private String Genre;

	

}
