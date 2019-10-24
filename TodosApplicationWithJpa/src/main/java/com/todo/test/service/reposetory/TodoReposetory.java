package com.todo.test.service.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.test.service.model.Todo;

@Repository
public interface TodoReposetory extends JpaRepository<Todo, Long>{

	List<Todo> findByUsername(String username);
	
}
