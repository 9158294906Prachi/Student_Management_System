package com.qsp.student_management_system.exception;

public class StudentNameNotFoundException extends RuntimeException{

	private String message;
	
	public StudentNameNotFoundException(String message) {
		this.message=message;
	}
	
	public String get() {
		return message;
	}
}
