package com.projects.springboot.app.entity;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "families")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Family implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "family_id")
	private Long familyId;

	@JoinColumn(unique = true, name = "head_of_family_parent_id")
	@OneToOne(cascade = CascadeType.MERGE)
	private Parent headOfFamilyParentId;

	@NotEmpty
	@Column(name = "family_name")
	private String familyName;

	@OneToMany(mappedBy = "family", fetch = FetchType.LAZY)
	private List<FamilyMember> familyMembers;

	public Family() {
		familyMembers = new ArrayList<>();
	}

	public Family(Long familyId, Parent headOfFamilyParentId, String familyName) {
		super();
		this.familyId = familyId;
		this.headOfFamilyParentId = headOfFamilyParentId;
		this.familyName = familyName;
	}

	public List<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(List<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}

	public Long getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Long familyId) {
		this.familyId = familyId;
	}

	public Parent getHeadOfFamilyParentId() {
		return headOfFamilyParentId;
	}

	public void setHeadOfFamilyParentId(Parent headOfFamilyParentId) {
		this.headOfFamilyParentId = headOfFamilyParentId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
