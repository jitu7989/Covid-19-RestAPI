package com.coviwin.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vaccine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineid;
	
	@NonNull
	private String vaccineName;
	private String description;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "vaccine")
	private VaccineCount vaccinecount;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccine")
	private List<Member> member;

	public Integer getVaccineid() {
		return vaccineid;
	}

	public void setVaccineid(Integer vaccineid) {
		this.vaccineid = vaccineid;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VaccineCount getVaccinecount() {
		return vaccinecount;
	}

	public void setVaccinecount(VaccineCount vaccinecount) {
		this.vaccinecount = vaccinecount;
	}

	public List<Member> getMember() {
		return member;
	}

	public void setMember(List<Member> member) {
		this.member = member;
	}

	
	
}
