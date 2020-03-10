package com.projects.springboot.app;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.springboot.app.controllers.StudentController;
import com.projects.springboot.app.entity.Student;
import com.projects.springboot.app.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(StudentController.class)
public class StudentTest {

	@Autowired
	private MockMvc mockTest;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private StudentService studentService;

	private Student stTest = new Student(1L, "M", "Alejandro", "Mateo", "Gonzales", "Student");

	@Test
	public void AllStudentsIsOkTest() throws Exception {
		List<Student> students = Arrays.asList(stTest);
		when(studentService.findAll()).thenReturn(students);
		mockTest.perform(get("/api/students")).andExpect(status().isOk());
	}

	@Test
	public void CreateStudentIsOkTest() throws Exception {
		Student student = stTest;
		when(studentService.create(student)).thenReturn(student);
		mockTest.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(student))).andExpect(status().isCreated());
	}

	@Test
	public void FindOneStudentIsOkTest() throws Exception {
		mockTest.perform(get("/api/students/{id}", 1L)).andExpect(status().isOk());	
	}

	@Test
	public void UpdateStudentIsOkTest() throws Exception {
		Student student = new Student(1L, "M", "Andrés", "Mateo", "Sarmiento", "Student");
		when(studentService.update(1L, student)).thenReturn(student);
		mockTest.perform(patch("/api/students/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(student))).andExpect(status().isOk());
	}

	@Test
	public void DeleteStudentIsOkTest() throws Exception {
		mockTest.perform(delete("/api/students/{id}", 1L)).andExpect(status().isNoContent());
	}

}
