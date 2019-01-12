package com.harbour.todomvc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/todos", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TodoController {
	
	private final TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping()
	public ResponseEntity<List<Todo>> getAllTodos() {
		List<Todo> list = todoService.findAll();
		return ResponseEntity.ok(list);
	}
	
    @PostMapping()
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoService.save(todo);
        return ResponseEntity.ok(savedTodo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable Long id) {
        Optional<Todo> optionalTodo = todoService.findById(id);

        if (!optionalTodo.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        todo.setId(id);
        todo = todoService.save(todo);

        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        Optional<Todo> optionalTodo = todoService.findById(id);

        if (!optionalTodo.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        todoService.delete(optionalTodo.get());

        return ResponseEntity.ok().build();
    }

}
