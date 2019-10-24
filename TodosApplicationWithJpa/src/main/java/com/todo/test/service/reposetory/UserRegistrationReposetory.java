package com.todo.test.service.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.test.service.model.UserRegistration;

@Repository
public interface UserRegistrationReposetory extends JpaRepository<UserRegistration, Long> {
	public UserRegistration findByUsernameAndPassword(String username, String password);
}
