package com.todo.test.service.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.test.service.model.Todo;
import com.todo.test.service.reposetory.TodoReposetory;
import com.todo.test.service.services.TodoServices;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoController {

	@Autowired
	TodoServices todoServices;
	
	@Autowired
	TodoReposetory todoReposetory;
	
	@GetMapping(path = "users/{username}/todos")
	public List<Todo> retriveAllTodos(@PathVariable String username) {
		
		return todoReposetory.findByUsername(username);
		
	}
	
	@GetMapping(path = "users/{username}/todos/{id}")
	public Todo retriveTodoById(@PathVariable Long id) {
		
		return todoReposetory.findById(id).get();
		
	}
	
	@PutMapping(path = "users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable Long id, @RequestBody Todo todo) {
		
		Todo updatedTodo = todoReposetory.save(todo);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
		
	}
	
	@DeleteMapping(path = "users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Long id) {
		
		todoReposetory.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PostMapping(path = "users/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo createdTodo = todoReposetory.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
