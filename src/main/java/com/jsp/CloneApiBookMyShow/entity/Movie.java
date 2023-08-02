package com.jsp.CloneApiBookMyShow.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jsp.CloneApiBookMyShow.enums.Genre;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movieId;
	private String movieName;

	@DateTimeFormat(style = "HH:mm")
	private LocalDateTime movieDuration;
	private String movieDescription;
	private String language;

	// Genres enum
	private Genre genre;
	private Genre genre1;
	private Genre Genre2;

	@ManyToOne
	@JsonIgnore
	@JoinColumn
	private ProductionHouse productionHouse;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ProductionHouse getProductionHouse() {
		return productionHouse;
	}

	public void setProductionHouse(ProductionHouse productionHouse) {
		this.productionHouse = productionHouse;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Genre getGenre1() {
		return genre1;
	}

	public void setGenre1(Genre genre1) {
		this.genre1 = genre1;
	}

	public Genre getGenre2() {
		return Genre2;
	}

	public void setGenre2(Genre genre2) {
		Genre2 = genre2;
	}

}
