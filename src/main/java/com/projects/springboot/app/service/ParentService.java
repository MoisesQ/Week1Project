package com.projects.springboot.app.service;

import com.projects.springboot.app.config.exception.NotFoundException;
import com.projects.springboot.app.entity.Parent;
import com.projects.springboot.app.repository.ParentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParentService {

	@Autowired
	ParentRepository parentRepository;

	public Parent create(Parent parent) {
		Parent prt = parentRepository.save(parent);
		return prt;
	}

	public List<Parent> findAll() {
		List<Parent> parents = parentRepository.findAll();
		return parents;
	}

	public Parent findbyId(Long id) {
		if (!parentRepository.existsById(id)) {
			throw new NotFoundException("Not found parent with id: " + id);
		} else {
			Parent prt = parentRepository.getOne(id);
			return prt;
		}
	}

	public Parent update(Long id, Parent parent) {
		if (!parentRepository.existsById(id)) {
			throw new NotFoundException("Not found parent with id: " + id);
		} else {
			parent.setParentId(id);
			Parent prt = parentRepository.save(parent);
			return prt;
		}
	}

	public void delete(Long id) {
		if (!parentRepository.existsById(id)) {
			throw new NotFoundException("Not found parent with id: " + id);
		} else {
			parentRepository.deleteById(id);
		}
	}

}
