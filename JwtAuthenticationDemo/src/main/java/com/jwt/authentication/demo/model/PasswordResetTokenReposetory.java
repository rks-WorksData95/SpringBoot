package com.jwt.authentication.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenReposetory extends JpaRepository<PasswordResetToken, Long>{
	PasswordResetToken findByToken(String token);
	
	@Query("SELECT userTokenDetail FROM PasswordResetToken userTokenDetail where userTokenDetail.userInformation.id = :id")
	PasswordResetToken findByUserId(Long id);
}
