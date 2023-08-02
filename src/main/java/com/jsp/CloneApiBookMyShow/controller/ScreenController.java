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

import com.jsp.CloneApiBookMyShow.dto.ScreenDto;
import com.jsp.CloneApiBookMyShow.service.ScreenService;
import com.jsp.CloneApiBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/screens")
public class ScreenController {
	@Autowired
	private ScreenService service;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(@RequestParam long theatreID, @RequestBody ScreenDto screenDto)
	{
		return service.addScreen(theatreID,screenDto);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(@RequestParam long screenId,@RequestBody ScreenDto screenDto)
	{
		return service.updateScreen(screenId,screenDto);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> getScreen(@RequestParam long screenId)
	{
		return service.getScreen(screenId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(@RequestParam long screenId)
	{
		return service.deleteScreenById(screenId);
	}
}
