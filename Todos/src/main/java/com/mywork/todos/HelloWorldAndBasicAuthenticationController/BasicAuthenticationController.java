package com.mywork.todos.HelloWorldAndBasicAuthenticationController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthenticationController {
	
	@GetMapping(path = "/basicauth")
	public AuthenticationBean  isAuthenticateUser() {
		//throw new RuntimeException("Some Error has Hapended! Contact Support - ***_***");
		return new AuthenticationBean("You are authenticated");
	}
	
}
