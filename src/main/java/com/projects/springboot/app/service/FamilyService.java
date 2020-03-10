package com.projects.springboot.app.service;

import com.projects.springboot.app.config.exception.NotFoundException;
import com.projects.springboot.app.entity.Family;
import com.projects.springboot.app.entity.Parent;
import com.projects.springboot.app.repository.FamilyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FamilyService {

	@Autowired
	FamilyRepository familyRepository;
	
	@Autowired
	ParentService parentService;

	public Family create(Family family) {
		Parent prnt = parentService.findbyId(family.getHeadOfFamilyParentId().getParentId());
		Family fm = familyRepository.save(family);
		fm.setHeadOfFamilyParentId(prnt);
//		return familyRepository.getOne(fm.getFamilyId());
		return fm;
	}

	public List<Family> findAll() {
		List<Family> families = familyRepository.findAll();
		return families;
	}

	public Family findbyId(Long id) {
		if (!familyRepository.existsById(id)) {
			throw new NotFoundException("Not found family with id: " + id);
		} else {
			Family fm = familyRepository.getOne(id);
			return fm;
		}
	}

	public Family update(Long id, Family family) {
		if (!familyRepository.existsById(id)) {
			throw new NotFoundException("Not found family with id: " + id);
		} else {
			family.setFamilyId(id);
			Family fm = familyRepository.save(family);
			return fm;
		}
	}

	public void delete(Long id) {
		if (!familyRepository.existsById(id)) {
			throw new NotFoundException("Not found family with id: " + id);
		} else {
			familyRepository.deleteById(id);
		}
	}

}
