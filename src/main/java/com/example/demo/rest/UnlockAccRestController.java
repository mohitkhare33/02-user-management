package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.AccUnlockForm;
import com.example.demo.service.UserRegistrationService;
@CrossOrigin("*")
@RestController
public class UnlockAccRestController {

	@Autowired
	private UserRegistrationService service;
	
	@PostMapping("/unlock")
	public ResponseEntity<String> unlockAcc(@RequestBody AccUnlockForm unlock)
	{
		String unlockAcc = service.unlockAcc(unlock);
		return new ResponseEntity<>(unlockAcc,HttpStatus.OK);
		
	}
}
