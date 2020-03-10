package com.projects.springboot.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.springboot.app.controllers.FamilyMemberController;
import com.projects.springboot.app.entity.Family;
import com.projects.springboot.app.entity.FamilyMember;
import com.projects.springboot.app.entity.Parent;
import com.projects.springboot.app.entity.Student;
import com.projects.springboot.app.service.FamilyMemberService;

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

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(FamilyMemberController.class)
public class FamilyMemberTest {

	@Autowired
	private MockMvc mockTest;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private FamilyMemberService familyMemberService;

	// Parent to create test
	private Parent parentTest = new Parent(6L, "F", "Janet", "Luna", "Gonzales", "Parent");

	// Student to create test
	private Student studentTest = new Student(7L, "M", "Julio", "Alvaro", "Gonzales", "Student");

	// Family to create test
	private Family familyTest = new Family(7L, parentTest, "Los Gonzales");

	// FamilyMember student to create and update test
	private FamilyMember familyMemberTest = new FamilyMember(7L, "Parent", null, parentTest, familyTest);
	private FamilyMember familyMemberTest2 = new FamilyMember(8L, "Student", studentTest, null, familyTest);

	@Test
	public void AllFamilyMembersIsOkTest() throws Exception {
		List<FamilyMember> familyMembers = Arrays.asList(familyMemberTest, familyMemberTest2);
		when(familyMemberService.findAll()).thenReturn(familyMembers);
		mockTest.perform(get("/api/familymembers")).andExpect(status().isOk());
	}

	@Test
	public void CreateFamilyMemberIsOkTest() throws Exception {
		FamilyMember familyMember = familyMemberTest;
		when(familyMemberService.create(familyMember)).thenReturn(familyMember);
		mockTest.perform(post("/api/familymembers").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(familyMember))).andExpect(status().isCreated());
	}

	@Test
	public void FindOneFamilyMemberIsOkTest() throws Exception {
		mockTest.perform(get("/api/familymembers/{id}", 7L)).andExpect(status().isOk());
	}

	@Test
	public void UpdateFamilyMemberIsOkTest() throws Exception {

		FamilyMember familyMember = new FamilyMember(7L, "Student", studentTest, null, familyTest);

		when(familyMemberService.update(7L, familyMember)).thenReturn(familyMember);
		mockTest.perform(patch("/api/familymembers/{id}", 7L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(familyMember))).andExpect(status().isOk());
	}

	@Test
	public void DeleteFamilyMemberIsOkTest() throws Exception {
		mockTest.perform(delete("/api/familymembers/{id}", 7L)).andExpect(status().isNoContent());
	}

}
