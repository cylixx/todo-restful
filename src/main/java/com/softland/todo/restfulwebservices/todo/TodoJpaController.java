package com.softland.todo.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softland.todo.restfulwebservices.helloworld.HelloWorldBean;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoJpaController {

	@Autowired
	private TodoHardcodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	
//	@GetMapping(path="/hello-world/path-variable/{name}")
//	public HelloWorldBean helloWorlPathVariable(@PathVariable String name) {
//		return new HelloWorldBean(String.format("Hello World, %s", name));
//	}
	
	@GetMapping("/jpa/users/{username}/todos") 
	public List<Todo> getAllTodos(@PathVariable String username) {
//		return todoService.findAll();
		return todoJpaRepository.findByUsername(username);
	}
	
	@GetMapping("/jpa/users/{username}/todos/{id}") 
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
//		return todoService.findById(id);
		return todoJpaRepository.findById(id).get();
	}
	
	
	//DELETE /users/{username}/todos/{id}
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(
			@PathVariable String username,
			@PathVariable long id 
			) {
		
//		Todo todo = todoService.deleteById(id);
		todoJpaRepository.deleteById(id);
//		return ResponseEntity.notFound().build();
		return ResponseEntity.noContent().build();
	}
	
	// Edit/Updated a Todo
	//PUT /users/{username}/todos/{id}
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable String username,
			@PathVariable long id,
			@RequestBody Todo todo
			) {
		
		//Todo todoUpdated = todoService.save(todo);
		Todo todoUpdated = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK);
	}
	
	//POST /users/{username}/todos
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username,
			@RequestBody Todo todo
			) {
		
//		Todo createdTodo = todoService.save(todo);
		todo.setUsername(username);
		Todo createdTodo = todoJpaRepository.save(todo);
		
		//Location
		//Get current resource url
		//{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
