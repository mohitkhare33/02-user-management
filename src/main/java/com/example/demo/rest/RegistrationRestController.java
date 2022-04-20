package com.example.demo.rest;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.RegistrationForm;
import com.example.demo.service.UserRegistrationService;
@CrossOrigin("*")
@RestController

public class RegistrationRestController {

	@Autowired
	private UserRegistrationService  userRegistrationService;
	
	@GetMapping("/emailcheck/{email}")
	public ResponseEntity<String> emailCheck(@PathVariable("email") String emailId)
	{
		String status = userRegistrationService.emailCheck(emailId);
		return new ResponseEntity<>(status,HttpStatus.OK);
	
		
	}
	
	@GetMapping("/countries")
	public ResponseEntity<Map<Integer, String>> getCountries()
	{
		Map<Integer, String> countriesMap = userRegistrationService.getAllCountryNames();
		return new ResponseEntity<>(countriesMap,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/states/{countryId}")
	public ResponseEntity<Map<Integer, String>>getStates(@PathVariable("countryId") Integer countryId)
	{
		 Map<Integer, String> statesMap = userRegistrationService.getAllStateNames(countryId);
		return new ResponseEntity<>(statesMap,HttpStatus.OK);
		
	}
	
	@GetMapping("/cities/{stateid}")
	public ResponseEntity<Map<Integer, String>>getCities(@PathVariable("stateid") Integer stateId)
	{
		 Map<Integer, String> citiesMap = userRegistrationService.getAllCityNames(stateId);
		return new ResponseEntity<>(citiesMap,HttpStatus.OK);
		
	}
	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody RegistrationForm user)
	{
		String status = userRegistrationService.saveUserRegistrationData(user);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
		
	}
}
