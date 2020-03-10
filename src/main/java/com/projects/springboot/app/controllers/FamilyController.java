package com.projects.springboot.app.controllers;

import com.projects.springboot.app.entity.Family;
import com.projects.springboot.app.service.FamilyService;
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
public class FamilyController {

	@Autowired
	FamilyService familyService;

	@PostMapping("/api/families")
	public ResponseEntity<Family> postFamily(@RequestBody Family family) {
		return ResponseEntity.status(HttpStatus.CREATED).body(familyService.create(family));
	}

	@GetMapping("/api/families")
	public ResponseEntity<List<Family>> getFamilies() {
		return ResponseEntity.status(HttpStatus.OK).body(familyService.findAll());
	}

	@GetMapping("/api/families/{familyId}")
	public ResponseEntity<Family> getFamily(@PathVariable Long familyId) {
		return ResponseEntity.status(HttpStatus.OK).body(familyService.findbyId(familyId));
	}

	@PatchMapping("/api/families/{familyId}")
	public ResponseEntity<Family> patchFamily(@PathVariable Long familyId, @RequestBody Family family) {
		return ResponseEntity.status(HttpStatus.OK).body(familyService.update(familyId, family));
	}
	
	@DeleteMapping("/api/families/{familyId}")
	public ResponseEntity<Void> deleteFamily(@PathVariable Long familyId) {
		familyService.delete(familyId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

}