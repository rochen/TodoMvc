package com.harbour.todomvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceTest {

	@Autowired
	TodoService todoService;
	
	@MockBean
	TodoRepository todoRepository;
	
	@Test
	public void testFindAll() {
		// given
		List<Todo> all = Lists.newArrayList(new Todo("badminton"), new Todo("swimming"));
		given(todoRepository.findAll()).willReturn(all);
		
		// when
		List<Todo> todos = todoService.findAll();
				
		// then
		SoftAssertions.assertSoftly(softly -> {
		      softly.assertThat(todos)
		      			.as("should contain only two todos")
		        		.hasSize(2);
		      softly.assertThat(todos)
		      			.as("should contain badmintion and swimming")
		      			.extracting("text")
		      			.contains("badminton", "swimming");
		});
	}
	
	@Test
	public void testFindById() {
		// given
		Todo value = new Todo(1177L, "badminton", false);
		given(todoRepository.findById(1177L)).willReturn(Optional.of(value));
		
		// when
		Optional<Todo> actual = todoService.findById(1177L);
		
		// then
		SoftAssertions.assertSoftly(softly -> {
		      softly.assertThat(actual)
		      			.as("should contain something")
		        		.isPresent();
		      softly.assertThat(actual.get())
		      			.as("should contain badmintion")
		      			.extracting("text")
		      			.contains("badminton");
		});
		
	}
	
	@Test
	public void testSave() {
		// given
		given(todoRepository.save(any(Todo.class)))
			.willAnswer(answer -> {
				Todo fake = (Todo) answer.getArgument(0);
				fake.setId(1177L);
				return fake;
			});
		
		// when
		Todo input = new Todo(null, "badminton", false);
		Todo todo = todoService.save(input);
		System.out.println(todo);
		
		// then
		then(todo.getId()).as("should contain id")
						  .isNotNull();
		then(todo.getText()).as("should be badminton")
							.isEqualTo("badminton");
	}
	
	@Test
	public void testDelete() {
		
	}
}
