package com.projects.springboot.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "parents")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Parent implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parent_id")
	private Long parentId;

	@NotEmpty
	private String gender;

	@NotEmpty
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty
	@Column(name = "middle_name")
	private String middleName;

	@NotEmpty
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "other_parent_details")
	private String otherParentDetails;

	@ManyToMany
	@JoinTable(name = "student_parents", joinColumns = @JoinColumn(columnDefinition = "student_id"), inverseJoinColumns = @JoinColumn(columnDefinition = "parent_id"))
	private List<Student> students;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<FamilyMember> familyMembers;

	public Parent() {
		students = new ArrayList<>();
		familyMembers = new ArrayList<>();
	};

	public Parent(Long parentId, String gender, String firstName, String middleName, String lastName,
			String otherParentDetails) {
		super();
		this.parentId = parentId;
		this.gender = gender;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.otherParentDetails = otherParentDetails;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(List<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOtherParentDetails() {
		return otherParentDetails;
	}

	public void setOtherParentDetails(String otherParentDetails) {
		this.otherParentDetails = otherParentDetails;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
