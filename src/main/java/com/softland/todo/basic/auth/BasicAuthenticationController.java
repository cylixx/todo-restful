package com.softland.todo.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.softland.todo.restfulwebservices.helloworld.HelloWorldBean;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BasicAuthenticationController { 

//	@GetMapping(path="/hello-world-bean")
//	public HelloWorldBean helloWorldBean() {
//		return new HelloWorldBean("Hello World");
////		throw new RuntimeException("Some Error has Happened! Contact Support at ***_***");
//	}
	
//	@GetMapping(path="/hello-world/path-variable/{name}")
//	public HelloWorldBean helloWorlPathVariable(@PathVariable String name) {
//		return new HelloWorldBean(String.format("Hello World, %s", name));
//	}
	
	@GetMapping(path="/basicauth")
//	public AuthenticationBean helloWorlPathVariable(@PathVariable String name) {
	public AuthenticationBean helloWorlPathVariable() {
		return new AuthenticationBean("You are authenticated");
	}
}
