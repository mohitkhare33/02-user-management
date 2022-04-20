package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.LoginForm;
import com.example.demo.service.UserRegistrationService;
@CrossOrigin("*")
@RestController

public class LoginRestController {
	
	@Autowired UserRegistrationService  userRegistrationService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginForm form)
	{
		String status=userRegistrationService.loginCheck(form);
		return new ResponseEntity<String>(status,HttpStatus.OK);
		
		
	}
	
}
