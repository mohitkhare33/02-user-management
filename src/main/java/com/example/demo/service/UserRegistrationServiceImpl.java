package com.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.AccUnlockForm;
import com.example.demo.binding.LoginForm;
import com.example.demo.binding.RegistrationForm;
import com.example.demo.entity.CityMaster;
import com.example.demo.entity.CountryMaster;
import com.example.demo.entity.StateMaster;
import com.example.demo.entity.UserDetails;
import com.example.demo.repository.CityMasterRepository;
import com.example.demo.repository.CountyMasterRepository;
import com.example.demo.repository.StateMasterRepository;
import com.example.demo.repository.UserDetailsRepository;
import com.example.demo.utils.EmailUtils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private CountyMasterRepository countryMasterRepository;

	@Autowired
	private StateMasterRepository stateMasterRepository;

	@Autowired
	private CityMasterRepository cityMasterRepository;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String loginCheck(LoginForm loginForm) {
		// TODO Auto-generated method stub
		UserDetails userAcc = userDetailsRepository.findByEmailAndPassword(loginForm.getEmail(),
				loginForm.getPassword());
		if (userAcc == null) {
			return "Invalid Credentials";
		}
		if (userAcc.getAccStatus().equals("LOCKED")) {
			return "Your Accound Locked";
		}
		return "SUCCESS";

	}

	@Override
	public String emailCheck(String emailId) {
		// TODO Auto-generated method stub
		UserDetails userAcc = userDetailsRepository.findByEmail(emailId);
		if (userAcc == null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> getAllCountryNames() {
		// TODO Auto-generated method stub
		List<CountryMaster> findAll = countryMasterRepository.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		findAll.forEach(country -> {
			countryMap.put(country.getCountryId(), country.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> getAllStateNames(Integer countryId) {
		// TODO Auto-generated method stub
		List<StateMaster> states = stateMasterRepository.findByCountryId(countryId);
		Map<Integer, String> statesMap = new HashMap<>();
		states.forEach(state -> {
			statesMap.put(state.getStateId(), state.getStateName());
		});
		return statesMap;
	}

	@Override
	public Map<Integer, String> getAllCityNames(Integer stateId) {
		// TODO Auto-generated method stub
		List<CityMaster> cities = cityMasterRepository.findByStateId(stateId);
		Map<Integer, String> cityMap = new HashMap<>();
		cities.forEach(city -> {

			cityMap.put(city.getCityId(), city.getCityName());

		});

		return cityMap;
	}

	@Override
	public String saveUserRegistrationData(RegistrationForm registrationForm) {
		// TODO Auto-generated method stub
		registrationForm.setPassword(generateRandomPwd(6));
		UserDetails user=new UserDetails();
		BeanUtils.copyProperties(registrationForm, user);
		user.setAccStatus("LOCKED");
		userDetailsRepository.save(user);
		String subject="User Registration -Mohit IT";
		String body = readUnlockAccEmailBody(registrationForm);
		emailUtils.sendEmail(registrationForm.getEmail(), subject, body);
		return "SUCCESS";
	}

	
	@Override
	public String unlockAcc(AccUnlockForm accUnlockForm) {
		// TODO Auto-generated method stub
		UserDetails userAcc = userDetailsRepository.findByEmailAndPassword(accUnlockForm.getEmail(), accUnlockForm.getTempPassword());
		if(userAcc==null)
		{
			return "Invalid Temporary Pwd";
		}
		userAcc.setPassword(accUnlockForm.getNewPassword());
		userAcc.setAccStatus("UNLOCKED");
		userDetailsRepository.save(userAcc);
		return "Unlock your Account Successfully";
	}

	@Override
	public String forgotPwd(String emailId) {
		// TODO Auto-generated method stub
		UserDetails userAcc = userDetailsRepository.findByEmail(emailId);
		if(userAcc==null)
		{
			return "Invalid Email";
		}
		String subject ="Recover Password -Mohit IT";
		String body=readForGotEmailBody(userAcc);
		emailUtils.sendEmail(emailId, subject, body);
		return "Password sent to your registered mail";
	}

private	static String generateRandomPwd(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
     
       private String readUnlockAccEmailBody(RegistrationForm user)
       {
    	   String body ="";
    	   StringBuffer buffer =new StringBuffer();
    	   Path filePath=   Paths.get("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
    	
    	   try(Stream<String>stream=Files.lines(filePath))
    	   {
    	   stream.forEach(line->
    	   {
    		   buffer.append(line);
    	   });
    	   
    	   body=buffer.toString();
    	   body=body.replace("{FNAME}", user.getFirstName());
    	   body=body.replace("{LNAME}", user.getLastName());
    	   body=body.replace("{TEMP-PWD}", user.getPassword());
    	   body=body.replace("{EMAIL}", user.getEmail());
    	   
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    	   }
    	   
		return body;
    	   
       }
       
       private String readForGotEmailBody(UserDetails entity)
       {
    	   String body ="";
    	   StringBuffer buffer =new StringBuffer();
    	   Path filePath=   Paths.get("RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE.txt");
    	
    	   try(Stream<String>stream=Files.lines(filePath))
    	   {
    	   stream.forEach(line->
    	   {
    		   buffer.append(line);
    	   });
    	   
    	   body=buffer.toString();
    	   body=body.replace("{FNAME}", entity.getFirstName());
    	   body=body.replace("{LNAME}", entity.getLastName());
    	   body=body.replace("{PWD}", entity.getPassword());
    	  
    	   
    	   }
    	   catch(Exception e)
    	   {
    		   e.printStackTrace();
    	   }
    	   
		return body;
    	   
       }
       
       
}
