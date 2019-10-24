package com.todo.test.service.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.test.service.model.UserRegistration;
import com.todo.test.service.reposetory.UserRegistrationReposetory;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserRegistraionController {

	@Autowired
	UserRegistrationReposetory userRegistrationReposetory;
	
	@PostMapping(path = "users/registration")
	public ResponseEntity<Void> newUserRegistration(@RequestBody UserRegistration userRegistration){
		
		UserRegistration createdUser = userRegistrationReposetory.save(userRegistration);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	
}
