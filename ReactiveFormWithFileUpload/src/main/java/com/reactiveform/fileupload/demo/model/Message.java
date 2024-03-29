package com.reactiveform.fileupload.demo.model;

public class Message {
	
	private String message;
	
	public Message() {
		super();
	}

	public Message(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + "]";
	}
	
}
