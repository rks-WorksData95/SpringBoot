package com.jwt.authentication.demo.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TokenVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String confirmToken;
	private Date createdDate;
	
	@OneToOne(targetEntity = userInformation.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "userId")
	private userInformation userInformation;

	public TokenVerification() {
		super();
	}

	public TokenVerification(com.jwt.authentication.demo.model.userInformation userInformation) {
		super();
		this.userInformation = userInformation;
		createdDate = new Date();
		confirmToken = UUID.randomUUID().toString();
	}

	public TokenVerification(Long id, String confirmToken, Date createdDate,
			com.jwt.authentication.demo.model.userInformation userInformation) {
		super();
		this.id = id;
		this.confirmToken = confirmToken;
		this.createdDate = createdDate;
		this.userInformation = userInformation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfirmToken() {
		return confirmToken;
	}

	public void setConfirmToken(String confirmToken) {
		this.confirmToken = confirmToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public userInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(userInformation userInformation) {
		this.userInformation = userInformation;
	}

	@Override
	public String toString() {
		return "TokenVerification [id=" + id + ", confirmToken=" + confirmToken + ", createdDate=" + createdDate
				+ ", userInformation=" + userInformation + "]";
	}
	
}
