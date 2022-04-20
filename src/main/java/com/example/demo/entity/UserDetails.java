package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USER_EMAIL")
	private String email;

	@Column(name = "USER_PSWD")
	private String password;

	@Column(name = "USER_MOBILE")
	private Long userMobile;

	@Column(name = "DOB")
	private LocalDate dob;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "COUNTERY_ID")
	private Integer countryId;

	@Column(name = "STATE_ID")
	private Integer stateId;

	@Column(name = "CITY_ID")
	private Integer cityId;

	@Column(name = "ACC_STATUS")
	private String accStatus;

	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;

	@Column(name = "UPDATED_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;

	public UserDetails() {
		super();
	}

	
	

}
