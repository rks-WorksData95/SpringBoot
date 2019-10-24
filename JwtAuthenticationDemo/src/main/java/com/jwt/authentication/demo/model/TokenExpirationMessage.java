package com.jwt.authentication.demo.model;

public class TokenExpirationMessage {
	
	private String expiredDate;
	private String message;
	
	public TokenExpirationMessage() {
		super();
	}

	public TokenExpirationMessage(String expiredDate, String message) {
		super();
		this.expiredDate = expiredDate;
		this.message = message;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TokenExpirationMessage [expiredDate=" + expiredDate + ", message=" + message + "]";
	}
	
}
