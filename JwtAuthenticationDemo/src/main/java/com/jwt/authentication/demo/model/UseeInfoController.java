package com.jwt.authentication.demo.model;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UseeInfoController {
	
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('USER')")
	public String forUser() {

		return "welcome user";
		
	}

	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String forAdmin() {

		return "welcome admin";
		
	}
	
}
