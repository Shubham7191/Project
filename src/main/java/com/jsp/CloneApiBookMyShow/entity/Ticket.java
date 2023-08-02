package com.jsp.CloneApiBookMyShow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jsp.CloneApiBookMyShow.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tiketId;
	private double totalPrice;
	
	//ticket status 
	private TicketStatus ticketStatus;
	
	@ManyToOne
	private MovieShow movieShow;
	
	@OneToMany
	 private List<Booking> bookings;
	
	@ManyToOne
	private Customer customer;
	 

}
