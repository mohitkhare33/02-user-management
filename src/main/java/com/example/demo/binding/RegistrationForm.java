package com.example.demo.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class RegistrationForm {
	private String firstName;
	private String lastName;
	private String email;
	private Long userMobile;
	private String password;
	private String tempPswd;
	private LocalDate dob;
	private String gender;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	private String accStatus;
	
	
	
}
