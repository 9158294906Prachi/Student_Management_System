package com.qsp.student_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.student_management_system.dto.Student;
import com.qsp.student_management_system.repo.StudentRepo;

@Repository
public class StudentDao {

	@Autowired
	private StudentRepo repo;
	
	public Student saveStudent(Student student) {
		return repo.save(student);
	}
	
	public List<Student> saveAllStudent(List<Student> list) {
		return repo.saveAll(list);
	}
	
	public Student findStudent(int id) {
		Optional<Student> optional=repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<Student> findAllStudents() {
		return repo.findAll();
	}
	
	public List<Student> findByName(String name){
		return repo.findByName(name);
	}
	
	public Student findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Student findByPhone(long phone) {
		return repo.findByPhone(phone);
	}
	
	public List<Student> findByAge(int age) {
		return repo.findByAge(age);
	}
	
	public List<Student> findByAddress(String address) {
		return repo.findByAddress(address);
	}
	
	public List<Student> findBySecuredMarksLessThan(int securedMarks){
		return repo.findBySecuredMarksLessThan(securedMarks);
	}
	
	public List<Student> findBySecuredMarksGreaterThan(int securedMarks){
		return repo.findBySecuredMarksGreaterThan(securedMarks);
	}
	
	public List<Student> findBySecuredMarksBetween(int securedMarks1, int securedMarks2){
		return repo.findBySecuredMarksBetween(securedMarks1, securedMarks2);
	}
	
	public List<Student> findByGrade(String grade){
		return repo.findByGrade(grade);
	}
	
	public Student updateStudentPhone(int id, long phone) {
		Optional<Student> optional=repo.findById(id);
		if (optional.isPresent()) {
			Student student=optional.get();
			student.setPhone(phone);
			return repo.save(student);
		} else {
			return null;
		}
	}
	
	public Student updateStudentEmail(int id, String email) {
		Optional<Student> optional=repo.findById(id);
		if (optional.isPresent()) {
			Student student=optional.get();
			student.setEmail(email);
			return repo.save(student);
		} else {
			return null;
		}
	}
	
	public Student deleteEmployee(int id) {
		Optional<Student> optional=repo.findById(id);
		if (optional.isPresent()) {
			repo.deleteById(id);
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Student deleteByPhone(long phone) {
		Student student= findByPhone(phone);
		if (student!=null) {
			repo.delete(student);
			return student;
		} else {
			return null;
		}
	}
	
	public Student deleteByEmail(String email) {
		Student student= findByEmail(email);
		if (student!=null) {
			repo.delete(student);
			return student;
		} else {
			return null;
		}
	}
}
