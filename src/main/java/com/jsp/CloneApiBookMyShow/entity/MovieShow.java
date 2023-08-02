package com.jsp.CloneApiBookMyShow.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jsp.CloneApiBookMyShow.enums.ShowStatus;

@Entity

public class MovieShow{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
	private String showLocation;
    
    
    // this all from movie // we took only movie id so we can take other attribute in service
	private long movieId;
    private String movieName;
    private LocalDateTime movieDuration;
	private String movieDescription;
	private String movieLanguage;

	// this is from screen
	private long screenId;
	private String screenName;
	//
	private double classicSeatPrice;
	private double goldenSeatPrice;
	private double premimumSeatPrice;
	

	// show status enum
	private ShowStatus showStatus;

	// genre // this is all enum
	private String Genre;

	

	@ManyToOne
	@JoinColumn
	private Theatre theatre;
	
	
	
	
	

	public long getShowId() {
		return showId;
	}

	public void setShowId(long showId) {
		this.showId = showId;
	}

	public LocalDateTime getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(LocalDateTime showStartTime) {
		this.showStartTime = showStartTime;
	}

	public LocalDateTime getShowEndTime() {
		return showEndTime;
	}

	public void setShowEndTime(LocalDateTime showEndTime) {
		this.showEndTime = showEndTime;
	}

	public String getShowLocation() {
		return showLocation;
	}

	public void setShowLocation(String showLocation) {
		this.showLocation = showLocation;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public LocalDateTime getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(LocalDateTime movieDuration) {
		this.movieDuration = movieDuration;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

	public String getMovieLanguage() {
		return movieLanguage;
	}

	public void setMovieLanguage(String movieLanguage) {
		this.movieLanguage = movieLanguage;
	}

	public long getScreenId() {
		return screenId;
	}

	public void setScreenId(long screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public double getClassicSeatPrice() {
		return classicSeatPrice;
	}

	public void setClassicSeatPrice(double classicSeatPrice) {
		this.classicSeatPrice = classicSeatPrice;
	}

	public double getGoldenSeatPrice() {
		return goldenSeatPrice;
	}

	public void setGoldenSeatPrice(double goldenSeatPrice) {
		this.goldenSeatPrice = goldenSeatPrice;
	}

	public double getPremimumSeatPrice() {
		return premimumSeatPrice;
	}

	public void setPremimumSeatPrice(double premimumSeatPrice) {
		this.premimumSeatPrice = premimumSeatPrice;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}
	

	public ShowStatus getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}

	
}
