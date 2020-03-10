package com.projects.springboot.app.service;

import com.projects.springboot.app.config.exception.NotFoundException;
import com.projects.springboot.app.entity.Student;
import com.projects.springboot.app.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public Student create(Student student) {
		Student st = studentRepository.save(student);
		return st;
	}

	public List<Student> findAll() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

	public Student findbyId(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new NotFoundException("Not found student with id: " + id);
		} else {
			Student st = studentRepository.getOne(id);
			return st;
		}

	}

	public Student update(Long id, Student student) {
		if (!studentRepository.existsById(id)) {
			throw new NotFoundException("Not found student with id: " + id);
		} else {
			student.setStudentId(id);
			Student st = studentRepository.save(student);
			return st;
		}
	}

	public void delete(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new NotFoundException("Not found student with id: " + id);
		} else {
			studentRepository.deleteById(id);
		}
	}

}
