package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.UsersTable;
import com.example.repo.UserTableRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserTableRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repo.findByName(username);
	}
	
	
	public void saveUser(UsersTable user) {
		try {
			repo.save(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
