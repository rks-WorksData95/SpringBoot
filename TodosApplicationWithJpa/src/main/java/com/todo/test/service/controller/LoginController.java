package com.todo.test.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.test.service.model.AuthenticationBean;
import com.todo.test.service.model.Login;
import com.todo.test.service.model.UserRegistration;
import com.todo.test.service.reposetory.UserRegistrationReposetory;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
	
	@Autowired
	UserRegistrationReposetory userRegistrationReposetory;
	
	@PostMapping(path = "/basicauth")
	public ResponseEntity<AuthenticationBean> authenticateUser(@RequestBody Login login) {
		UserRegistration loginUserDetails = userRegistrationReposetory.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		if(loginUserDetails == null) {
			return new ResponseEntity<AuthenticationBean>(new AuthenticationBean("User Not Authenticated."), HttpStatus.UNAUTHORIZED);
		}else {
			return new ResponseEntity<AuthenticationBean>(new AuthenticationBean("User Authenticated."), HttpStatus.OK);
		}
	}
	
}
