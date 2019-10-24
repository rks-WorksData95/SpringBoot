package com.jwt.authentication.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenVerificationReposetory extends JpaRepository<TokenVerification, Long> {
	TokenVerification findByConfirmToken(String confirmToken);
	
	@Query("SELECT userTokenDetail FROM TokenVerification userTokenDetail where userTokenDetail.userInformation.id = :id")
	TokenVerification findByUserId(Long id);
}
