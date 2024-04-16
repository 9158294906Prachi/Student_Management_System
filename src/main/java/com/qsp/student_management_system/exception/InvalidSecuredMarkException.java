package com.qsp.student_management_system.exception;

public class InvalidSecuredMarkException extends RuntimeException{

	private String message;
	
	public InvalidSecuredMarkException(String message) {
		this.message=message;
	}
	
	public String get() {
		return message;
	}
}
