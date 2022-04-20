package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserDetails;


public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
	
	public UserDetails  findByEmailAndPassword(String email, String password);
	
	public UserDetails  findByEmail(String emailId);

}
