package com.harbour.todomvc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private final TodoRepository todoRepository;
	
	@Autowired
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public List<Todo> findAll() {
		return todoRepository.findAll();
	}
	
	public Optional<Todo> findById(Long id) {
		return todoRepository.findById(id);
	}
	
	public Todo save(Todo todo) {
		return todoRepository.save(todo);
	}
	
	public void delete(Todo todo) {
		todoRepository.delete(todo);
	}
	

}
