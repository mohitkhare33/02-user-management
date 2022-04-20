package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.binding.AccUnlockForm;
import com.example.demo.binding.LoginForm;
import com.example.demo.binding.RegistrationForm;



public interface UserRegistrationService {

	
	// login screen related methods
		public String loginCheck(LoginForm loginForm);

		// Registration screen related methods
		public String emailCheck(String emailId);

		public Map<Integer, String> getAllCountryNames();

		public Map<Integer, String> getAllStateNames(Integer countryId);

		public Map<Integer, String> getAllCityNames(Integer stateId);
		
		public String saveUserRegistrationData(RegistrationForm registrationForm);

		// unlock account screen related methods
		public String unlockAcc(AccUnlockForm accUnlockForm);

		// forgot pwd screen related methods
		public String forgotPwd(String emailId);
	

	
}
