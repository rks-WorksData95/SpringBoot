package com.jwt.authentication.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface user extends JpaRepository<userInformation, Long> {
	public userInformation findByUsername(String username);
	public boolean existsByUsername(String username);
	public boolean existsByEmailId(String emailId);
	userInformation findByEmailId(String emailId);
}
