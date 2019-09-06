package com.softland.todo.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static long idCounter = 0;
	
	static {
		todos.add(new Todo(++idCounter, "cylixx", "Learn english", new Date(), false));
		todos.add(new Todo(++idCounter, "cylixx", "Learn about Microservices", new Date(), false));
		todos.add(new Todo(++idCounter, "cylixx", "Learn about Angular", new Date(), false));
	}
	
	public List<Todo> findAll() {
		return todos;
	}
	
	public Todo save(Todo todo) {
		if(todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		
		if(todo == null) return null;
		if (todos.remove(todo))  return todo;
		
		return null;
	}

	public Todo findById(long id) {
		return todos.stream().filter(x -> x.getId() == id).findAny().orElse(null);
	}
	
}
