package com.qsp.student_management_system.exception;

public class IdNotFoundException extends RuntimeException{

	private String message;
	
	public IdNotFoundException(String message) {
		this.message=message;
	}
	
	public String get() {
		return message;
	}
}
