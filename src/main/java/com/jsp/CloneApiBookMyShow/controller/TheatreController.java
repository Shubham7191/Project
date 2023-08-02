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

import com.jsp.CloneApiBookMyShow.dto.TheatreDto;
import com.jsp.CloneApiBookMyShow.entity.Theatre;
import com.jsp.CloneApiBookMyShow.service.TheatreService;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

	@Autowired
	private TheatreService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestParam long ownerId,
			@RequestParam long addressId, @RequestBody TheatreDto theatreDto) {
		return service.saveTheatre(ownerId, addressId, theatreDto);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestParam long theatreId,
			@RequestBody TheatreDto theatreDto) {
		return service.updateTheatre(theatreId, theatreDto);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> getTheatre(@RequestParam long theatreId) {
		return service.getTheatre(theatreId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam long theatreId) {
		return service.deleteTheatreById(theatreId);
	}

}