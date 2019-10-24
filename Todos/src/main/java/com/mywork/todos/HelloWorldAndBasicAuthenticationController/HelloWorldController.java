package com.mywork.todos.HelloWorldAndBasicAuthenticationController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloWorldController {
	
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorldBean  helloWorldBean(@PathVariable String name) {
		//throw new RuntimeException("Some Error has Hapended! Contact Support - ***_***");
		return new HelloWorldBean("Hello World, "+name);
	}
}
