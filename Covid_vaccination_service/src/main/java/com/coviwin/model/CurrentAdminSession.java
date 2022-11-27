package com.coviwin.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentAdminSession {
	
	@Id
	@Column(unique = true)
	private Integer userId;  //it take the customerId. we have to give mannually
	private String uuid;
	private LocalDateTime localDateTime;

}
