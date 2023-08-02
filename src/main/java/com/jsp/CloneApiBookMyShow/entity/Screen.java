package com.jsp.CloneApiBookMyShow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jsp.CloneApiBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneApiBookMyShow.enums.ScreenStatus;
import com.jsp.CloneApiBookMyShow.enums.ScreenType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long screenId;
	private String screenName;

	private int noOfClassicSeat;
	private int noOfPlatinumSeat;
	private int noOfGoldSeat;
	

	// screen Type ==> enum
	private ScreenType screenType;
	// screen availability ==> enum
	private ScreenAvailability screenAvailability;
	// screen status ==> enum
	private ScreenStatus screenStatus;
	
	
	
	
	@OneToMany(mappedBy = "screen" ,cascade = CascadeType.ALL)
	private List<Seat> seat ;

	@ManyToOne
	@JoinColumn
	private Theatre theatre;
}
