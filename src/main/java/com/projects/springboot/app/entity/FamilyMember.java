package com.projects.springboot.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "family_members")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FamilyMember implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "family_member_id")
	private Long familyMemberId;

	@NotEmpty
	@Column(name = "parent_or_student_member")
	private String parentOrStudentMember;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Parent parent;

	@ManyToOne
	@JoinColumn(name = "family_id")
	private Family family;

	public FamilyMember() {
		super();
	}

	public FamilyMember(Long familyMemberId, String parentOrStudentMember, Student student, Parent parent,
			Family family) {
		super();
		this.familyMemberId = familyMemberId;
		this.parentOrStudentMember = parentOrStudentMember;
		this.student = student;
		this.parent = parent;
		this.family = family;
	}

	public Long getFamilyMemberId() {
		return familyMemberId;
	}

	public void setFamilyMemberId(Long familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	public String getParentOrStudentMember() {
		return parentOrStudentMember;
	}

	public void setParentOrStudentMember(String parentOrStudentMember) {
		this.parentOrStudentMember = parentOrStudentMember;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
