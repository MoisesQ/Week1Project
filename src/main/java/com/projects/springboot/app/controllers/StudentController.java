package com.projects.springboot.app.controllers;

import com.projects.springboot.app.entity.Student;
import com.projects.springboot.app.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/api/students")
	public ResponseEntity<Student> postStudent(@RequestBody Student student) {
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(student));
	}

	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getStudents() {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
	}

	@GetMapping("/api/students/{studentId}")
	public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.findbyId(studentId));
	}

	@PatchMapping("/api/students/{studentId}")

	public ResponseEntity<Student> patchStudent(@PathVariable Long studentId, @RequestBody Student student) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.update(studentId, student));
	}

	@DeleteMapping("/api/students/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
		studentService.delete(studentId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

}