package com.coviwin.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdCard {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	
	@NotNull(message = "Member name can not be null")
	@NotBlank(message = "Member name is Mandatory")
	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dob;

	@NotNull(message = "Gender can not be null")
	@Pattern(regexp = "^[A-Z][a-z]*")
	private String gender;
	
	@NotNull(message = "Address can not be null")
	private String address;
	
	@NotNull(message = "City can not be null")
	private String city;
	
	@NotNull(message = "State may not be null")
	private String state;
	
	@NotNull(message = "Pincode may not be null")
	@Size(min = 6, max = 8)
	private String pincode;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "idCard")
	private Member member;

//	@OneToOne(mappedBy = "idcard")
	@Embedded
	AdharCard adharcard;

//	@OneToOne(mappedBy = "idcard")
	@Embedded
	PanCard pancard;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public AdharCard getAdharcard() {
		return adharcard;
	}

	public void setAdharcard(AdharCard adharcard) {
		this.adharcard = adharcard;
	}

	public PanCard getPancard() {
		return pancard;
	}

	public void setPancard(PanCard pancard) {
		this.pancard = pancard;
	}

}
