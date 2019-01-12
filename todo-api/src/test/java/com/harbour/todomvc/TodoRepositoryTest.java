package com.harbour.todomvc;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryTest {
	
	@Autowired
	TodoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;

	@Before
	public void setUp() throws Exception {
		String[] names = {"badminton", "swimming", "yoga"};

		for(String name: names) {
			Todo todo = new Todo();
			todo.setText(name);
			entityManager.persist(todo);
		}	
	}

	@Test
	public void testFindAll() {
		List<Todo> actual = repository.findAll();
		for(Todo todo: actual) {
			System.out.println(todo);
		}
		assertThat(actual).as("should contain 3 text")
						.extracting("text")
						.contains("badminton", "swimming", "yoga");
	}
	
	@Test
	public void testFindByText() {
		Optional<List<Todo>> actual = repository.findByText("badminton");

		assertThat(actual).as("should present something")
						  .isPresent();

		assertThat(actual.get()).as("should contain only badminton")
								.extracting("text")
								.containsOnly("badminton");
		
	}

}
