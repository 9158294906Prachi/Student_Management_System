package com.qsp.student_management_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.student_management_system.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

	Student findByEmail(String email);

	Student findByPhone(long phone);

	List<Student> findByName(String name);

	List<Student> findByAge(int age);

	List<Student> findByAddress(String address);

	@Query("Select s from Student s where s.securedMarks<=?1")
	List<Student> findBySecuredMarksLessThan(int securedMarks);

	List<Student> findBySecuredMarksGreaterThan(int securedMarks);

	List<Student> findByGrade(String grade);

//	@Query("Select s from Student s where s.securedMarks>=?1 and s.securedMarks<=?2")
	@Query("Select s from Student s where s.securedMarks Between ?1 And ?2")
	List<Student> findBySecuredMarksBetween(int securedMarks1, int securedMarks2);

}
