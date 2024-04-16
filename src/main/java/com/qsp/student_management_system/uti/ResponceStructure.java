package com.qsp.student_management_system.uti;

import lombok.Data;

@Data
public class ResponceStructure<T> {

	private String message;
	private int status;
	private T data;
}
