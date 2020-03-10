package com.projects.springboot.app.service;

import com.projects.springboot.app.config.exception.NotFoundException;
import com.projects.springboot.app.entity.FamilyMember;
import com.projects.springboot.app.repository.FamilyMemberRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FamilyMemberService {

	@Autowired
	FamilyMemberRepository familyMemberRepository;

	public FamilyMember create(FamilyMember familyMember) {
		FamilyMember fmem = familyMemberRepository.save(familyMember);
		return fmem;
	}

	public List<FamilyMember> findAll() {
		List<FamilyMember> fmembers = familyMemberRepository.findAll();
		return fmembers;
	}

	public FamilyMember findbyId(Long id) {
		if (!familyMemberRepository.existsById(id)) {
			throw new NotFoundException("Not found family member with id: " + id);
		} else {
			FamilyMember fmember = familyMemberRepository.getOne(id);
			return fmember;
		}
	}

	public FamilyMember update(Long id, FamilyMember familyMember) {
		if (!familyMemberRepository.existsById(id)) {
			throw new NotFoundException("Not found family member with id: " + id);
		} else {
			familyMember.setFamilyMemberId(id);
			FamilyMember fmember = familyMemberRepository.save(familyMember);
			return fmember;
		}
	}

	public void delete(Long id) {
		if (!familyMemberRepository.existsById(id)) {
			throw new NotFoundException("Not found family member with id: " + id);
		} else {
			familyMemberRepository.deleteById(id);
		}
	}
}
