package com.jsp.CloneApiBookMyShow.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jsp.CloneApiBookMyShow.enums.BookingStatus;
import com.jsp.CloneApiBookMyShow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	
	private LocalDateTime bookingFromTIme;
	private LocalDateTime bookingTillTime;
	private long seatId;
	private double seatPrice;
	
	//seat type
	private SeatType seatType;
	//booking status
	private BookingStatus  bookingStatus;
	
	

}
