package com.jsp.CloneApiBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneApiBookMyShow.dao.BookingDao;
import com.jsp.CloneApiBookMyShow.dao.CustomerDao;
import com.jsp.CloneApiBookMyShow.dao.MovieShowDao;
import com.jsp.CloneApiBookMyShow.dao.SeatDao;
import com.jsp.CloneApiBookMyShow.dao.TicketDao;
import com.jsp.CloneApiBookMyShow.entity.Booking;
import com.jsp.CloneApiBookMyShow.entity.Customer;
import com.jsp.CloneApiBookMyShow.entity.MovieShow;
import com.jsp.CloneApiBookMyShow.entity.Seat;
import com.jsp.CloneApiBookMyShow.entity.Ticket;
import com.jsp.CloneApiBookMyShow.enums.BookingStatus;
import com.jsp.CloneApiBookMyShow.enums.SeatType;
import com.jsp.CloneApiBookMyShow.enums.ShowStatus;
import com.jsp.CloneApiBookMyShow.enums.TicketStatus;
import com.jsp.CloneApiBookMyShow.exception.CustomerIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.MovieShowIdNOtFoundException;
import com.jsp.CloneApiBookMyShow.exception.SeatIdNotFoundEXception;
import com.jsp.CloneApiBookMyShow.exception.ShowIsNotActiveException;
import com.jsp.CloneApiBookMyShow.exception.TickerIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.TicketAlreadyCancelledException;
import com.jsp.CloneApiBookMyShow.exception.TicketAlreadyExpiredException;
import com.jsp.CloneApiBookMyShow.exception.TicketCannotBeCancelledException;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@Service
public class TicketService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private MovieShowDao showDao;

	@Autowired
	private SeatDao seatDao;
	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private TicketDao ticketDao;

	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(long customerId, long showId, long seatId) {
		Customer dbCustomer = customerDao.getCustomerById(customerId);

		Ticket ticket = new Ticket();
		if (dbCustomer != null) {
			ticket.setCustomer(dbCustomer);

		} else {
			throw new CustomerIdNotFoundException("sorry failed to book ticket");
		}

		MovieShow dbMovieShow = showDao.getShowById(showId);

		if (dbMovieShow != null) {
			if (dbMovieShow.getShowStatus().equals(ShowStatus.ACTIVE)) {
				ticket.setMovieShow(dbMovieShow);

			} else {
				throw new ShowIsNotActiveException("sorry failed to book tiket already active");

			}

		} else {
			throw new MovieShowIdNOtFoundException("Soryy failed to book ticket");
		}

		List<Booking> bookings = new ArrayList<Booking>();
		List<Seat> seats = new ArrayList<>();

		double totalprice = 0;
		// now we setting seat and its enum and mapped entity
		Seat dbSeat = seatDao.getSeatById(seatId);
		if (dbSeat != null) {
			Booking booking = new Booking();
			booking.setSeatId(dbSeat.getSeatId());
			booking.setSeatType(dbSeat.getSeatType());
			booking.setBookingStatus(BookingStatus.ACTIVE);
			booking.setBookingFromTIme(dbMovieShow.getShowStartTime());
			booking.setBookingTillTime(dbMovieShow.getShowEndTime());

			SeatType seatType = booking.getSeatType();
			switch (seatType) {
			case CLASSIC:
				booking.setSeatPrice(dbMovieShow.getClassicSeatPrice());
				totalprice += dbMovieShow.getClassicSeatPrice();
				break;

			case GOLD:
				booking.setSeatPrice(dbMovieShow.getGoldenSeatPrice());
				totalprice += dbMovieShow.getGoldenSeatPrice();
				break;

			case PLATINUM:
				booking.setSeatPrice(dbMovieShow.getPremimumSeatPrice());
				totalprice += dbMovieShow.getPremimumSeatPrice();
				break;

			}
			bookings.add(booking);
			seats.add(dbSeat);
			bookingDao.saveBookig(booking);

			ticket.setBookings(bookings);
			ticket.setTotalPrice(totalprice);
			ticket.setTicketStatus(TicketStatus.ACTIVE);
			Ticket dbTicket = ticketDao.saveTticket(ticket);

			ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();
			structure.setMessage("Ticket Booked successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbTicket);
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);

		} else {
			throw new SeatIdNotFoundEXception("sorry failed to book ticket");
		}

	}
	
	

	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(long ticketId) {
		Ticket dbTicket = ticketDao.getTicketById(ticketId);
		if (dbTicket != null) {
			// do other things 
			// we checking how many ways we can cancel ticket
			

			 if(dbTicket.getMovieShow().getShowStatus().equals(ShowStatus.ON_GOING)) {
		    	  throw new TicketCannotBeCancelledException("Sorry failed to cancel ticket");
		      }else {
		    	  if(dbTicket.getTicketStatus().equals(TicketStatus.EXPIRED)) {
		    		  throw new TicketAlreadyExpiredException("Sorry failed to cancel ticket");
		    	
		    	  }else {
		    		  if(dbTicket.getTicketStatus().equals(TicketStatus.CANCELLED)) {
		    			  throw new TicketAlreadyCancelledException("Sorry failed to cancel ticket");
		    		  }else {
				
				List<Booking> bookings = dbTicket.getBookings();
				for(Booking booking: bookings)
				{
					// we can't delete booking we can cancel booking
					
					booking.setBookingStatus(BookingStatus.CANCELLED);
					bookingDao.saveBookig(booking);
				}
				dbTicket.setTicketStatus(TicketStatus.CANCELLED);
				// we need to re save or update 
				ticketDao.saveTticket(dbTicket);
				
				ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();
				structure.setMessage("Ticket cancelled  successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbTicket);
				return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.FOUND);

			}

		}
		
		      }
		}
		else {
			throw new TickerIdNotFoundException("sorry failed to cancel ticket");
		}
		
	}
}