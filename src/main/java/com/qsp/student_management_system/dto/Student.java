package com.qsp.student_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	@Column(unique = true)
	private long phone;
	@Column(unique = true)
	private String email;
	private String address;
	private int totalMarks;
	private int securedMarks;
	private double percentage;
	private String grade;
}
