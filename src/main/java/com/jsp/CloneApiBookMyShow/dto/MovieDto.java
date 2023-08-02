package com.jsp.CloneApiBookMyShow.dto;

import java.time.LocalDateTime;

import com.jsp.CloneApiBookMyShow.enums.Genre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {
	
	private long movieId;
	private String movieName;
	
	//Genres 
	private Genre genre;
	private Genre genre1;
	private Genre Genre2;
	
	private LocalDateTime movieDuration ;
	private String movieDescription ;
	private String language;
	
	
	
	


	
	
	

}
