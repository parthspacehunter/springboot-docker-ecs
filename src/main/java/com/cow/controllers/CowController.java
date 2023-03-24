package com.cow.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cow.exception.CowNotFoundException;
import com.cow.models.Cow;
import com.cow.services.CowService;

@RestController
@RequestMapping("/cows")
public class CowController {
	
	@Autowired
	CowService cowService;

	@RequestMapping(method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Object> getAllcows() {
		List<Cow> cows = this.cowService.getAll();
		return ResponseEntity.ok(cows);
	}
	
	@RequestMapping(method = RequestMethod.POST,consumes = "application/json" ,produces = "application/json")
	public ResponseEntity<Object> addCow(@RequestBody Cow cow) {
		this.validateCowRequest(cow);
		Cow created = this.cowService.add(cow);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@RequestMapping(method = RequestMethod.PUT,path="/{id}", consumes = "application/json" ,produces = "application/json")
	public ResponseEntity<Object> updateCow(@RequestBody Cow cow,@PathVariable String id) {
			this.validateCowRequest(cow);
			cow.setId(id);
			Cow updated = this.cowService.update(cow);
			if(updated==null) 
				throw new CowNotFoundException("id - " + id );
			return ResponseEntity.ok(updated);
		
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/getCow/{id}",produces = "application/json")
	public ResponseEntity<Object> getByCowById(@PathVariable String id) {
		Cow cow = this.cowService.getCowById(id);
		if(cow==null)
			throw new CowNotFoundException("id - " + id );
		
	    return ResponseEntity.ok(cow);

		
	}
	
	private void validateCowRequest(Cow cow) {
		if(cow.getCollarId() == null)
			throw new RuntimeException("Collar Id cannot be null");
		if(cow.getCowNumber() == null)
			throw new RuntimeException("Cow number cannot be null");
		if(cow.getCollarStatus() != null || cow.getLastLocation()!=null)
			throw new RuntimeException("Collar status and Last Location can only be inserted from backend");

	}
	
}
