package com.jsp.CloneApiBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneApiBookMyShow.dao.MovieDao;
import com.jsp.CloneApiBookMyShow.dao.MovieShowDao;
import com.jsp.CloneApiBookMyShow.dao.ScreenDao;
import com.jsp.CloneApiBookMyShow.dao.TheatreDao;
import com.jsp.CloneApiBookMyShow.dto.MovieShowDto;
import com.jsp.CloneApiBookMyShow.entity.Movie;
import com.jsp.CloneApiBookMyShow.entity.MovieShow;
import com.jsp.CloneApiBookMyShow.entity.Screen;
import com.jsp.CloneApiBookMyShow.entity.Theatre;
import com.jsp.CloneApiBookMyShow.enums.ScreenAvailability;
import com.jsp.CloneApiBookMyShow.exception.MovieIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.MovieShowIdNOtFoundException;
import com.jsp.CloneApiBookMyShow.exception.ScreenAlreadyAlloted;
import com.jsp.CloneApiBookMyShow.exception.ScreenIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.TheatreIdNotFoundException;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@Service
public class MovieShowService {

	@Autowired
	private MovieShowDao showDao;
	@Autowired
	private TheatreDao theatreDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ScreenDao screenDao;
	@Autowired
	private MovieDao movieDao;

	public ResponseEntity<ResponseStructure<MovieShow>> addShow(long theatreId, MovieShowDto showDto) {
		
		Theatre dbTheatre = theatreDao.getTheatreById(theatreId);
		if (dbTheatre != null) {

			MovieShow moviewShow = this.modelMapper.map(showDto, MovieShow.class);

			long screenId = moviewShow.getScreenId();
			
			Screen dbScreen = screenDao.getScreenById(screenId);
			if (dbScreen != null) {
				if (dbScreen.getScreenAvailability().equals(ScreenAvailability.NOT_ALLOTED)) {
					// ADD Show
					long movieId = moviewShow.getMovieId();

					Movie dbMovie = movieDao.getMovieById(movieId);

					if (dbMovie != null) {
            		//  add movie to that show
						moviewShow.setMovieDescription(dbMovie.getMovieDescription());
						moviewShow.setMovieDuration(dbMovie.getMovieDuration());
						moviewShow.setMovieLanguage(dbMovie.getLanguage());
						moviewShow.setMovieName(dbMovie.getMovieName());
						moviewShow.setScreenName(dbScreen.getScreenName());
						moviewShow.setTheatre(dbTheatre);

						MovieShow dbShow = showDao.addShow(moviewShow);

						if (dbTheatre.getMovieShows().isEmpty()) {
							
           		//	this is the first show
							List<MovieShow> list = new ArrayList<MovieShow>();
							list.add(moviewShow);
							dbTheatre.setMovieShows(list);
							theatreDao.updateTheatre(theatreId, dbTheatre);
						} else {
							
            			// show already present
							List<MovieShow> list = dbTheatre.getMovieShows();
							list.add(moviewShow);
							dbTheatre.setMovieShows(list);
							theatreDao.updateTheatre(theatreId, dbTheatre);
						}
						
						
						
						
						ResponseStructure<MovieShow> structure= new ResponseStructure<>();
						structure.setMessage("added show sucessfully");
						structure.setStatus(HttpStatus.CREATED.value());
						structure.setData(dbShow);
						return new ResponseEntity<ResponseStructure<MovieShow>>(structure,HttpStatus.CREATED);

					} else {
						throw new MovieIdNotFoundException("Sorry failed to add show");
					}

				} else {
					throw new ScreenAlreadyAlloted("Sorry failed to add show");
				}
			} else {
				throw new ScreenIdNotFoundException("Sorry failed to add show");
			}

		} else {
			throw new TheatreIdNotFoundException("Sorry failed to add show");
		}

	}
	
	
	

	public ResponseEntity<ResponseStructure<MovieShow>> updateSHow(long showId, MovieShowDto showDto) {
		
		
		MovieShow movieShow=this.modelMapper.map(showDto, MovieShow.class);
		MovieShow  dbMovieShow=showDao.updateShow(showId,movieShow);
		
		if (dbMovieShow != null) {
			ResponseStructure<MovieShow> structure = new ResponseStructure<>();
			structure.setMessage("movie show updated sucessfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbMovieShow);
			
			return new ResponseEntity<ResponseStructure<MovieShow>>(structure,HttpStatus.CREATED);
		}else {
			throw new MovieShowIdNOtFoundException("Sorry failed to update Show");
		}
		
	}




	public ResponseEntity<ResponseStructure<MovieShow>> getShowById(long showId) {
		MovieShow dbMoviewShow=showDao.getShowById(showId);
		if(dbMoviewShow!=null) {
			ResponseStructure<MovieShow> structure=new ResponseStructure<MovieShow>();
    		structure.setMessage("show data fetched successfully");
    		structure.setStatus(HttpStatus.FOUND.value());
    		structure.setData(dbMoviewShow);
    		return new ResponseEntity<ResponseStructure<MovieShow>>(structure,HttpStatus.FOUND);
		}else {
			throw new MovieShowIdNOtFoundException("Sorry failed to get movie Show");
		}
	}

	public ResponseEntity<ResponseStructure<MovieShowDto>> deleteShowById(long showId) {
		MovieShow dbMoviewShow=showDao.deleteShowById(showId);
		if(dbMoviewShow!=null) {
			ResponseStructure<MovieShowDto> structure=new ResponseStructure<MovieShowDto>();
    		structure.setMessage("show data deleted successfully");
    		structure.setStatus(HttpStatus.FOUND.value());
    		structure.setData(dbMoviewShow);
    		return new ResponseEntity<ResponseStructure<MovieShowDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new MovieShowIdNOtFoundException("Sorry failed to Delete movie Show");
		}
	}
}