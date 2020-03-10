package com.projects.springboot.app.controllers;

import com.projects.springboot.app.entity.FamilyMember;
import com.projects.springboot.app.service.FamilyMemberService;
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
public class FamilyMemberController {

	@Autowired
	FamilyMemberService familyMemberService;
	
	@PostMapping("/api/familymembers")
	public ResponseEntity<FamilyMember> postFamilyMember(@RequestBody FamilyMember familyMember) {
		return ResponseEntity.status(HttpStatus.CREATED).body(familyMemberService.create(familyMember));
	}

	@GetMapping("/api/familymembers")
	public ResponseEntity<List<FamilyMember>> getFamilyMembers() {
		return ResponseEntity.status(HttpStatus.OK).body(familyMemberService.findAll());
	}

	@GetMapping("/api/familymembers/{familyMemberId}")
	public ResponseEntity<FamilyMember> getFamilyMember(@PathVariable Long familyMemberId) {
		return ResponseEntity.status(HttpStatus.OK).body(familyMemberService.findbyId(familyMemberId));
	}

	@PatchMapping("/api/familymembers/{familyMemberId}")
	public ResponseEntity<FamilyMember> patchFamilyMember(@PathVariable Long familyMemberId, @RequestBody FamilyMember familyMember) {
		return ResponseEntity.status(HttpStatus.OK).body(familyMemberService.update(familyMemberId, familyMember));
	}
	
	@DeleteMapping("/api/familymembers/{familyMemberId}")
	public ResponseEntity<Void> deleteFamilyMember(@PathVariable Long familyMemberId) {
		familyMemberService.delete(familyMemberId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
}
