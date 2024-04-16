package com.qsp.student_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.student_management_system.dto.Student;
import com.qsp.student_management_system.service.StudentService;
import com.qsp.student_management_system.uti.ResponceStructure;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;
	
	@PostMapping("/signup")
	public ResponceStructure<Student> saveStudent(@RequestBody Student student) {
		return service.saveStudent(student);
	}
	
	@PostMapping("/save/all")
	public ResponceStructure<List<Student>> saveAllStudent(@RequestBody List<Student> list) {
		return service.saveAllStudent(list);
	}
	
	@GetMapping("/find")
	public ResponceStructure<Student> findStudents(int id) {
		return service.findStudents(id);
	}
	
	@GetMapping("/find/all")
	public ResponceStructure<List<Student>> findAllStudents() {
		return service.findAllStudents();
	}
	
	@PutMapping("/update")
	public ResponceStructure<Student> updateStudent(int id,@RequestBody Student student) {
		return service.updateStudent(id, student);
	}
	
	@PatchMapping("update/marks")
	public ResponceStructure<Student> updateSecuredMarks(int id, int securedMarks) {
		return service.updateSecuredMarks(id, securedMarks);
	}
	
	@GetMapping("/find/name")
	public ResponceStructure<List<Student>> findByName(String name){
		return service.findByName(name);
	}
	
	@GetMapping("/find/email")
	public ResponceStructure<Student> findByEmail(String email) {
		return service.findByEmail(email);
	}
	
	@GetMapping("/find/phone")
	public ResponceStructure<Student> findByPhone(long phone) {
		return service.findByPhone(phone);
	}
	
	@GetMapping("/find/age")
	public ResponceStructure<List<Student>> findByAge(int age) {
		return service.findByAge(age);
	}
	
	@GetMapping("/find/address")
	public ResponceStructure<List<Student>> findByAddress(String address) {
		return service.findByAddress(address);
	}
	
	@GetMapping("/find/mark/lessthan")
	public ResponceStructure<List<Student>> findBySecuredMarksLessThan(int securedMarks){
		return service.findBySecuredMarksLessThan(securedMarks);
	}
	
	@GetMapping("/find/mark/greaterthan")
	public ResponceStructure<List<Student>> findBySecuredMarksGreaterThan(int securedMarks){
		return service.findBySecuredMarksGreaterThan(securedMarks);
	}
	
	@GetMapping("/find/mark/between")
	public ResponceStructure<List<Student>> findBySecuredMarksBetween(int securedMarks1, int securedMarks2){
		return service.findBySecuredMarksBetween(securedMarks1, securedMarks2);
	}
	
	@GetMapping("/find/grade")
	public ResponceStructure<List<Student>> findByGrade(String grade){
		return service.findByGrade(grade);
	}
	
	@PatchMapping("/update/phone")
	public ResponceStructure<Student> updateStudentPhone(int id, long phone) {
		return service.updateStudentPhone(id, phone);
	}
	
	@PatchMapping("/update/email")
	public ResponceStructure<Student> updateStudentEmail(int id, String email) {
		return service.updateStudentEmail(id, email);
	}
	
	@DeleteMapping("/delete")
	public ResponceStructure<Student> deleteEmployee(int id) {
		return service.deleteStudent(id);
	}
	
	@DeleteMapping("/delete/phone")
	public ResponceStructure<Student> deleteByPhone(long phone) {
		return service.deleteByPhone(phone);
	}
	
	@DeleteMapping("/delete/email")
	public ResponceStructure<Student> deleteByEmail(String email) {
		return service.deleteByEmail(email);
	}
}
