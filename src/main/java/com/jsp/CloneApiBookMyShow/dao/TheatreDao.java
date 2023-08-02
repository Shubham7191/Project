package com.jsp.CloneApiBookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneApiBookMyShow.entity.MovieShow;
import com.jsp.CloneApiBookMyShow.entity.Theatre;
import com.jsp.CloneApiBookMyShow.repository.TheatreRepo;

@Repository
public class TheatreDao {

	@Autowired
	private TheatreRepo repo;

	public Theatre saveTheatre(Theatre theatre) {
		return repo.save(theatre);

	}

	public Theatre updateTheatre(long theatreId, Theatre theatre) {

		Optional<Theatre> optional = repo.findById(theatreId);
		
		if (optional.isPresent()) {
			// setting all mapping
			Theatre oldTheatre = optional.get();
			theatre.setTheatreId(theatreId);
			theatre.setAddress(oldTheatre.getAddress());
			theatre.setMovieShows(oldTheatre.getMovieShows());
			theatre.setOwner(oldTheatre.getOwner());
			theatre.setScreen(oldTheatre.getScreen());
			repo.save(theatre);
			return theatre;
		}
		return null;
	}

	public Theatre getTheatreById(long theatreId) {
		Optional<Theatre> optional = repo.findById(theatreId);

		if (optional.isPresent()) {

			return optional.get();

		}
		return null;
	}

	public Theatre deleteTheatreById(long theatreId) {
			Optional<Theatre> optional =repo.findById(theatreId);
			
			// now we set mapping attribute as null because we deleting only theater data
			
			if (optional.isPresent()) {
				Theatre theatre = optional.get();
				theatre.setOwner(null);
				theatre.setAddress(null);
				// now we need to unmap movie show 
				List<MovieShow> list=optional.get().getMovieShows();
				
				for(MovieShow show:list) {
					show.setTheatre(null);
				}
				repo.deleteById(theatreId);
				return optional.get();
				
			}
		return null;
	}

}
