package com.todo.test.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.test.service.reposetory.TodoReposetory;

@Service
public class TodoServices {

	@Autowired
	TodoReposetory todoReposetory;
	
	
}
