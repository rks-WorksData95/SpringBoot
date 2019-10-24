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
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	private Date createdDate;
	
	@OneToOne(targetEntity = userInformation.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "userId")
	private userInformation userInformation;
	
	public PasswordResetToken() {
		super();
	}

	public PasswordResetToken(com.jwt.authentication.demo.model.userInformation userInformation) {
		super();
		this.userInformation = userInformation;
		createdDate = new Date();
		token = UUID.randomUUID().toString();
	}

	public PasswordResetToken(Long id, String token, Date createdDate,
			com.jwt.authentication.demo.model.userInformation userInformation) {
		super();
		this.id = id;
		this.token = token;
		this.createdDate = createdDate;
		this.userInformation = userInformation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
		return "PasswordResetToken [id=" + id + ", token=" + token + ", createdDate=" + createdDate
				+ ", userInformation=" + userInformation + "]";
	}
	
}
