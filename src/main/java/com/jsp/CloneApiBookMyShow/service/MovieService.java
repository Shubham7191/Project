package com.jsp.CloneApiBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.CloneApiBookMyShow.dao.MovieDao;
import com.jsp.CloneApiBookMyShow.dao.ProductionHouseDao;
import com.jsp.CloneApiBookMyShow.dto.MovieDto;
import com.jsp.CloneApiBookMyShow.entity.Movie;
import com.jsp.CloneApiBookMyShow.entity.ProductionHouse;
import com.jsp.CloneApiBookMyShow.exception.MovieIdNotFoundException;
import com.jsp.CloneApiBookMyShow.exception.ProductionHouseIdNotFoundException;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@Service
public class MovieService {

	@Autowired
	private MovieDao movieDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductionHouseDao productionHouseDao;

	public ResponseEntity<ResponseStructure<MovieDto>> saveMovie(long houseId, MovieDto movieDto) {

		// check production house id is present or not
		ProductionHouse Houseid = productionHouseDao.getProductionHouseById(houseId);
		if (Houseid != null) {

			// setting map attribute production house
			Movie movie = this.modelMapper.map(movieDto, Movie.class);
			movie.setProductionHouse(Houseid);

			// saving movie

			Movie dbMovie = movieDao.saveMovie(movie);

			// now update production house as well

			if (Houseid.getMovies().isEmpty()) {
				List<Movie> list = new ArrayList<Movie>();
				list.add(movie);
				Houseid.setMovies(list);
			} else {
				List<Movie> list = Houseid.getMovies();
				list.add(movie);
				Houseid.setMovies(list);
			}
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie Saved successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new ProductionHouseIdNotFoundException("sorry failed to add movie ");
		}
	}

	public ResponseEntity<ResponseStructure<MovieDto>> updateMovie(long movieId, MovieDto movieDto) {
		Movie movie=this.modelMapper.map(movieDto, Movie.class);
		Movie dbMovie=movieDao.updateMovie(movieId,movie);
		if(dbMovie!=null) {
			ResponseStructure<MovieDto> structure=new ResponseStructure<MovieDto>();
			structure.setMessage("Movie updated successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure,HttpStatus.CREATED);
		}else{
			throw new MovieIdNotFoundException("Sorry failed to update movie");
		}
		
	}
	public ResponseEntity<ResponseStructure<MovieDto>> getMovieById(long movieId) {
		Movie dbMovie=movieDao.getMovieById(movieId);
		if(dbMovie!=null) {
			ResponseStructure<MovieDto> structure=new ResponseStructure<MovieDto>();
			structure.setMessage("Movie fetched successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure,HttpStatus.CREATED);
		}else{
			throw new MovieIdNotFoundException("Sorry failed to get movie");
		}
		
	}
	public ResponseEntity<ResponseStructure<MovieDto>> deleteMovieById(long movieId) {
		Movie dbMovie=movieDao.deleteMovieById(movieId);
		if(dbMovie!=null) {
			ResponseStructure<MovieDto> structure=new ResponseStructure<MovieDto>();
			structure.setMessage("Movie Deleted successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure,HttpStatus.FOUND);
		}else{
			throw new MovieIdNotFoundException("Sorry failed to delete movie");
		}
	}
}