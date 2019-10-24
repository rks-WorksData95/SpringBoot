package com.h2.database.demo.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	USerReposetory userRepposetory;
	
	@GetMapping(path = "/users")
	public List<User> retriveAllUsers() {
		return userRepposetory.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retriveUser(@PathVariable int id) {
		return userRepposetory.findById(id).get();
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		userRepposetory.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepposetory.deleteById(id);
	}
	
}
