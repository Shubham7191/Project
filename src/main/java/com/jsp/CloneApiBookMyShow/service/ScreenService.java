package com.jsp.CloneApiBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneApiBookMyShow.dao.ScreenDao;
import com.jsp.CloneApiBookMyShow.dao.TheatreDao;
import com.jsp.CloneApiBookMyShow.dto.ScreenDto;
import com.jsp.CloneApiBookMyShow.entity.Screen;
import com.jsp.CloneApiBookMyShow.entity.Seat;
import com.jsp.CloneApiBookMyShow.entity.Theatre;
import com.jsp.CloneApiBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneApiBookMyShow.enums.ScreenStatus;
import com.jsp.CloneApiBookMyShow.enums.SeatType;
import com.jsp.CloneApiBookMyShow.exception.ScreenIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.ScreenNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@Service
public class ScreenService {

	@Autowired
	private TheatreDao theatreDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ScreenDao screenDao;

	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(long theatreId, ScreenDto screenDto) {
		
		Theatre theatre = theatreDao.getTheatreById(theatreId);
		if (theatre != null) {
			// add screen and seat
			Screen screen = this.modelMapper.map(screenDto, Screen.class);

			// screen variable you are having no of classic seat ,gold,premium seat
			// screen is having seat object???not present and i want to add it
			// screen is having theater ?no but we are having theater object then i will
			// set it(theater)

			// in screen we map seat as list so we making list type
			List<Seat> seats = new ArrayList<>();
			for (int a = screen.getNoOfClassicSeat(); a > 0; a--) {
				// seat class we are adding
				Seat seat = new Seat();
				seat.setSeatType(SeatType.CLASSIC);
				seat.setScreen(screen);
				seats.add(seat);
			}

			for (int b = screen.getNoOfGoldSeat(); b > 0; b--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.GOLD);
				seat.setScreen(screen);
				seats.add(seat);
			}
			for (int b = screen.getNoOfPlatinumSeat(); b > 0; b--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.PLATINUM);
				seat.setScreen(screen);
				seats.add(seat);

			}

			screen.setTheatre(theatre);
			screen.setSeat(seats);
			screen.setScreenAvailability(ScreenAvailability.NOT_ALLOTED);
			screen.setScreenStatus(ScreenStatus.AVAILABLE);
			Screen dbsScreen = screenDao.saveScreen(screen);

			// update the theater as well

			if (theatre.getScreen().isEmpty()) {
				List<Screen> screens = new ArrayList<Screen>();
				screens.add(dbsScreen);
				theatre.setScreen(screens);
				theatreDao.updateTheatre(theatreId, theatre);
			} else {
				List<Screen> screens = theatre.getScreen();
				screens.add(dbsScreen);
				theatre.setScreen(screens);
				theatreDao.updateTheatre(theatreId, theatre);
			}

			ScreenDto dto = this.modelMapper.map(dbsScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.CREATED);

		} else {
			throw new TheatreIdNotFoundException("failed to add screen");
		}

	}

	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(long screenId, ScreenDto screenDto) {

		Screen screen = this.modelMapper.map(screenDto, Screen.class);
		Screen dbscreen = screenDao.updateScreen(screenId, screen);
		if (dbscreen != null) {
			ScreenDto dto = this.modelMapper.map(dbscreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure = new ResponseStructure<>();
			structure.setMessage("screen updated successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.CREATED);

		} else {
			throw new ScreenNotFoundException("sorry failed to update screen");
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> getScreen(long screenId) {
		Screen dbScreen = screenDao.getScreenById(screenId);
		if (dbScreen != null) {
			ScreenDto dto = this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure = new ResponseStructure<>();
			structure.setMessage("screen fetch successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.FOUND);

		} else {
			throw new ScreenNotFoundException("sorry failed to fetch screen");
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(long screenId) {
		Screen dbScreen = screenDao.deleteScreenById(screenId);
		if(dbScreen!=null) {
			ScreenDto dto1=this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure=new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto1);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new ScreenIdNotFoundException("Sorry failed to Delete screen");
		}
	}
}
