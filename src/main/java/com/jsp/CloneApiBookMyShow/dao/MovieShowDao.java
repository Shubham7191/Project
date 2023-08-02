package com.jsp.CloneApiBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneApiBookMyShow.entity.MovieShow;
import com.jsp.CloneApiBookMyShow.repository.MovieShowRepo;


@Repository
public class MovieShowDao {

	@Autowired
	private MovieShowRepo repo;

	public MovieShow addShow(MovieShow movieShow) {
	
		return repo.save(movieShow);
	}

	public MovieShow updateShow(long showId, MovieShow moviewShow) {
		Optional<MovieShow> optional=repo.findById(showId);
		if(optional.isPresent()) {
			moviewShow.setMovieId(showId);
			moviewShow.setTheatre(optional.get().getTheatre());
			return repo.save(moviewShow);
		}
			return null;
		}

		public MovieShow getShowById(long showId) {
		Optional<MovieShow> optional=repo.findById(showId);
		if(optional.isPresent()) {
			return optional.get();
		}
			return null;
		}

		public MovieShow deleteShowById(long showId) {
			
			Optional<MovieShow> optional=repo.findById(showId);
			if(optional.isPresent()) {
				MovieShow moviewShow=optional.get();
				
				// moviewShow.setTheatre(null); // no need 
				
				repo.delete(moviewShow);
				return optional.get();
			}
				return null;
		}

		
}
