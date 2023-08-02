package com.jsp.CloneApiBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.CloneApiBookMyShow.entity.Screen;
import com.jsp.CloneApiBookMyShow.repository.ScreenRepo;

@Repository
public class ScreenDao {

	@Autowired
	private ScreenRepo repo;

	public Screen saveScreen(Screen screen) {
		return repo.save(screen);
	}

	public Screen updateScreen(long screenId, Screen screen) {
		Optional<Screen> optional = repo.findById(screenId);
		if (optional.isPresent()) {
			screen.setScreenId(screenId);
			screen.setSeat(optional.get().getSeat());
			screen.setTheatre(optional.get().getTheatre());
			return repo.save(screen);

		}
		return null;
	}

	public Screen getScreenById(long screenId) {
		Optional<Screen> optional = repo.findById(screenId);
		if (optional.isPresent()) {
			return optional.get();

		}
		return null;
	}

	public Screen deleteScreenById(long screenId) {
		Optional<Screen> optional = repo.findById(screenId);
		if (optional.isPresent()) {
			Screen screen = optional.get();
			screen.setTheatre(null); // before doing this we need to make theater screen as null
			repo.delete(screen);
			return screen;
		}
		return null;
	}

}
