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
import com.projects.springboot.app.controllers.ParentController;
import com.projects.springboot.app.entity.Parent;
import com.projects.springboot.app.service.ParentService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ParentController.class)
public class ParentTest {

	@Autowired
	private MockMvc mockTest;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private ParentService parentService;

	private Parent prtTest = new Parent(2L, "M", "Beltrán", "Mateo", "Quispe", "Nice father");
	
	@Test
	public void AllParentsIsOkTest() throws Exception {
		List<Parent> parents = Arrays.asList(prtTest);
		when(parentService.findAll()).thenReturn(parents);
		mockTest.perform(get("/api/parents")).andExpect(status().isOk());
	}
	
	@Test
	public void CreateParentIsOkTest() throws Exception {
		Parent parent = prtTest;
		when(parentService.create(parent)).thenReturn(parent);
		mockTest.perform(post("/api/parents").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parent))).andExpect(status().isCreated());
	}

	@Test
	public void FindOneParentIsOkTest() throws Exception {
		mockTest.perform(get("/api/parents/{id}", 2L)).andExpect(status().isOk());
	}

	@Test
	public void UpdateParentIsOkTest() throws Exception {
		Parent parent = new Parent(2L, "M", "Andrés", "Mateo", "Quispe", "Great father");
		when(parentService.update(2L, parent)).thenReturn(parent);
		mockTest.perform(patch("/api/parents/{id}", 2L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parent))).andExpect(status().isOk());
	}

	@Test
	public void DeleteParentIsOkTest() throws Exception {
		mockTest.perform(delete("/api/parents/{id}", 2L)).andExpect(status().isNoContent());
	}

}
