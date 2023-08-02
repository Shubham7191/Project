package com.jsp.CloneApiBookMyShow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.CloneApiBookMyShow.dto.MovieShowDto;
import com.jsp.CloneApiBookMyShow.entity.MovieShow;
import com.jsp.CloneApiBookMyShow.service.MovieShowService;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;


@RestController
@RequestMapping("/show")
public class MovieShowController {
	@Autowired
    private MovieShowService service;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MovieShow>> saveShow(@RequestParam long theatreId,@RequestBody MovieShowDto showDto){
		return service.addShow(theatreId,showDto);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<MovieShow>> updateMovieShow(@RequestParam long showId,@RequestBody MovieShowDto showDto){
		return service.updateSHow(showId,showDto);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<MovieShow>> getShowById(@RequestParam long showId){
		return service.getShowById(showId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MovieShowDto>> deleteShowById(@RequestParam long showId){
		return service.deleteShowById(showId);
	}
}