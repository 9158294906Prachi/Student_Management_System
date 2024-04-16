package com.qsp.student_management_system.service;

import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.qsp.student_management_system.dao.StudentDao;
import com.qsp.student_management_system.dto.Student;
import com.qsp.student_management_system.exception.AddressNotFoundException;
import com.qsp.student_management_system.exception.AgeNotFoundException;
import com.qsp.student_management_system.exception.DataNotFoundException;
import com.qsp.student_management_system.exception.EmailNotFoundException;
import com.qsp.student_management_system.exception.IdNotFoundException;
import com.qsp.student_management_system.exception.InvalidSecuredMarkException;
import com.qsp.student_management_system.exception.PhoneNotFoundException;
import com.qsp.student_management_system.exception.StudentNameNotFoundException;
import com.qsp.student_management_system.uti.ResponceStructure;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao dao;
	
	public ResponceStructure<Student> saveStudent(Student student) {
		int totalMarks=student.getTotalMarks();
		int securedMarks= student.getSecuredMarks();
		if (securedMarks<=totalMarks) {
			double percentage=((securedMarks*100.0)/totalMarks);
			student.setPercentage(percentage);
			if (percentage>=85) {
				student.setGrade("Distinction");
			} else if (percentage>=60 && percentage<85) {
				student.setGrade("First Class");
			} else if (percentage>=49 && percentage<60) {
				student.setGrade("second Class");
			} else if (percentage>=35 && percentage<49) {
				student.setGrade("Pass");
			} else {
				student.setGrade("Fail");
			}
			dao.saveStudent(student);
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("signup success");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(student);
			return structure;
		} else {
			throw new InvalidSecuredMarkException("Secured marks id greater than total marks!");
		}
	}
	
	public ResponceStructure<List<Student>> saveAllStudent(List<Student> list) {
		for (Student student : list) {
			double totalMarks=student.getTotalMarks();
			double securedMarks= student.getSecuredMarks();
			if (securedMarks<=totalMarks) {
				double percentage=(securedMarks/totalMarks)*100;
				student.setPercentage(percentage);
				if (percentage>=85) {
					student.setGrade("Distinction");
				} else if (percentage>=60 && percentage<85) {
					student.setGrade("First Class");
				} else if (percentage>=49 && percentage<60) {
					student.setGrade("second Class");
				} else if (percentage>=35 && percentage<49) {
					student.setGrade("Pass");
				} else {
					student.setGrade("Fail");
				}
			} 			
		}
		List<Student> list2= dao.saveAllStudent(list);
		ResponceStructure<List<Student>> structure= new ResponceStructure<>();
		structure.setMessage("signup success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(list2);
		return structure;
	}
	
	public ResponceStructure<Student> findStudents(int id) {
		Student student= dao.findStudent(id);
		if (student!=null) {
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new IdNotFoundException("Student with id "+id+" is not found");
		}
	}
	
	public ResponceStructure<List<Student>> findAllStudents() {
		List<Student> student= dao.findAllStudents();
		if (student!=null) {
			ResponceStructure<List<Student>> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new DataNotFoundException("No data found");
		}
	}
	
	public ResponceStructure<Student> updateStudent(int id, Student student) {
		Student dbStudent= dao.findStudent(id);
		if (dbStudent!=null) {
			student.setId(id);
			int totalMarks=student.getTotalMarks();
			int securedMarks= student.getSecuredMarks();
			if (securedMarks<=totalMarks) {
				double percentage=((securedMarks*100.0)/totalMarks);
				student.setPercentage(percentage);
				if (percentage>=85) {
					student.setGrade("Distinction");
				} else if (percentage>=60 && percentage<85) {
					student.setGrade("First Class");
				} else if (percentage>=49 && percentage<60) {
					student.setGrade("second Class");
				} else if (percentage>=35 && percentage<49) {
					student.setGrade("Pass");
				} else {
					student.setGrade("Fail");
				}
				
				ResponceStructure<Student> structure= new ResponceStructure<>();
				structure.setMessage("updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dao.saveStudent(student));
				return structure;
			} else {
				throw new InvalidSecuredMarkException("Secured marks id greater than total marks!");
			}
		}else {
			throw new IdNotFoundException("Student with id "+id+" is not found");
		}
	}
	
	public ResponceStructure<Student> updateSecuredMarks(int id, int securedMarks) {
		Student student= dao.findStudent(id);
		if (student!=null) {
			student.setId(id);
			if (securedMarks<=student.getTotalMarks()) {
				student.setSecuredMarks(securedMarks);
				int totalMarks=student.getTotalMarks();
				double percentage=((securedMarks*100.0)/totalMarks);
				student.setPercentage(percentage);
				if (percentage>=85) {
					student.setGrade("Distinction");
				} else if (percentage>=60 && percentage<85) {
					student.setGrade("First Class");
				} else if (percentage>=49 && percentage<60) {
					student.setGrade("second Class");
				} else if (percentage>=35 && percentage<49) {
					student.setGrade("Pass");
				} else {
					student.setGrade("Fail");
				}
				ResponceStructure<Student> structure= new ResponceStructure<>();
				structure.setMessage("updated");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(student);
				return structure;
			}
			else {
				throw new InvalidSecuredMarkException("Secured marks id greater than total marks!");
			}
		}else {
			throw new IdNotFoundException("Student with id "+id+" is not found");
		}
	}
	
	public ResponceStructure<List<Student>> findByName(String name){
		List<Student> student= dao.findByName(name);
		if (student!=null) {
			ResponceStructure<List<Student>> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new StudentNameNotFoundException("Students with name "+name+" is not found");
		}
	}
	
	public ResponceStructure<Student> findByEmail(String email) {
		Student student= dao.findByEmail(email);
		if (student!=null) {
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new EmailNotFoundException("Student with email "+email+" is not found");
		}
	}
	
	public ResponceStructure<Student> findByPhone(long phone) {
		Student student= dao.findByPhone(phone);
		if (student!=null) {
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new PhoneNotFoundException("Student with phone "+phone+" is not found");
		}
	}
	
	public ResponceStructure<List<Student>> findByAge(int age) {
		List<Student> student= dao.findByAge(age);
		if (student!=null) {
			ResponceStructure<List<Student>> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new AgeNotFoundException("Student with age "+age+" is not found");
		}
	}
	
	public ResponceStructure<List<Student>> findByAddress(String address) {
		List<Student> student= dao.findByAddress(address);
		if (student!=null) {
			ResponceStructure<List<Student>> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new AddressNotFoundException("Student with address "+address+" is not found");
		}
	}
	
	public ResponceStructure<List<Student>> findBySecuredMarksLessThan(int securedMarks){
		List<Student> student= dao.findBySecuredMarksLessThan(securedMarks);
		if (student!=null) {
			ResponceStructure<List<Student>> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new DataNotFoundException("No data found");
		}
	}
	
	public ResponceStructure<List<Student>> findBySecuredMarksGreaterThan(int securedMarks){
		List<Student> student= dao.findBySecuredMarksGreaterThan(securedMarks);
		if (student!=null) {
			ResponceStructure<List<Student>> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new DataNotFoundException("No data found");
		}
	}
	
	public ResponceStructure<List<Student>> findBySecuredMarksBetween(int securedMarks1, int securedMarks2){
		List<Student> student= dao.findBySecuredMarksBetween(securedMarks1, securedMarks2);
		if (student!=null) {
			ResponceStructure<List<Student>> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new DataNotFoundException("No data found");
		}
	}
	
	public ResponceStructure<List<Student>> findByGrade(String grade){
		List<Student> student= dao.findByGrade(grade);
		if (student!=null) {
			ResponceStructure<List<Student>> structure= new ResponceStructure<>();
			structure.setMessage("found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(student);
			return structure;
		} else {
			throw new DataNotFoundException("No data found");
		}
	}
	
	public ResponceStructure<Student> updateStudentPhone(int id, long phone) {
		Student student= dao.updateStudentPhone(id, phone);
		if (student!=null) {
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(student);
			return structure;
		} else {
			throw new IdNotFoundException("Student with id "+id+" is not found");
		}
	}
	
	public ResponceStructure<Student> updateStudentEmail(int id, String email) {
		Student student= dao.updateStudentEmail(id, email);
		if (student!=null) {
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(student);
			return structure;
		} else {
			throw new IdNotFoundException("Student with id "+id+" is not found");
		}
	}
	
	public ResponceStructure<Student> deleteStudent(int id) {
		Student student = dao.deleteEmployee(id);
		if (student != null) {
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("successfully deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(student);
			return structure;

		} else {
			throw new IdNotFoundException("Student with id "+id+" is not found");
		}
	}
	
	public ResponceStructure<Student> deleteByPhone(long phone) {
		Student student = dao.deleteByPhone(phone);
		if (student != null) {
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("successfully deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(student);
			return structure;

		} else {
			throw new PhoneNotFoundException("Student with phone "+phone+" is not found");
		}
	}
	
	public ResponceStructure<Student> deleteByEmail(String email) {
		Student student = dao.deleteByEmail(email);
		if (student != null) {
			ResponceStructure<Student> structure= new ResponceStructure<>();
			structure.setMessage("successfully deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(student);
			return structure;

		} else {
			throw new EmailNotFoundException("Student with email "+email+" is not found");
		}
	}
}
