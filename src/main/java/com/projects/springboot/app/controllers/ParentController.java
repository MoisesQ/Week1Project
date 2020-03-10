package com.projects.springboot.app.controllers;

import com.projects.springboot.app.entity.Parent;
import com.projects.springboot.app.service.ParentService;
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
public class ParentController {

	@Autowired
	ParentService parentService;

	@PostMapping("/api/parents")
	public ResponseEntity<Parent> postParent(@RequestBody Parent parent) {
		return ResponseEntity.status(HttpStatus.CREATED).body(parentService.create(parent));
	}

	@GetMapping("/api/parents")
	public ResponseEntity<List<Parent>> getParents() {
		return ResponseEntity.status(HttpStatus.OK).body(parentService.findAll());
	}

	@GetMapping("/api/parents/{parentId}")
	public ResponseEntity<Parent> getParent(@PathVariable Long parentId) {
		return ResponseEntity.status(HttpStatus.OK).body(parentService.findbyId(parentId));
	}

	@PatchMapping("/api/parents/{parentId}")
	public ResponseEntity<Parent> patchParent(@PathVariable Long parentId, @RequestBody Parent parent) {
		return ResponseEntity.status(HttpStatus.OK).body(parentService.update(parentId, parent));
	}

	@DeleteMapping("/api/parents/{parentId}")
	public ResponseEntity<Void> deleteParent(@PathVariable Long parentId) {
		parentService.delete(parentId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
