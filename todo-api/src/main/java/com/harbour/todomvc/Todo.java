package com.harbour.todomvc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

	@Id
	@GeneratedValue
	private Long id;
	
	private String text;
	
	private boolean done;
	
	public Todo(String text) {
		this.text = text;
	}
}
