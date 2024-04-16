package com.qsp.student_management_system.exception;

public class AgeNotFoundException extends RuntimeException{

	private String message;
	
	public AgeNotFoundException(String message) {
		this.message=message;
	}
	
	public String get() {
		return message;
	}
}
