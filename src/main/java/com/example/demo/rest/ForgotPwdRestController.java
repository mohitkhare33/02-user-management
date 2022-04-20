package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserRegistrationService;
@CrossOrigin("*")
@RestController

public class ForgotPwdRestController {

	@Autowired
	private UserRegistrationService service;
	@GetMapping("/forgotPwd/{emailId}")
	public ResponseEntity<String> forgotPwd(@PathVariable String emailId)
	{
		String forgotPwd = service.forgotPwd(emailId);
		return new ResponseEntity<>(forgotPwd,HttpStatus.OK);
		
	}
}
