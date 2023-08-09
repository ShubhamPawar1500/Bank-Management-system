package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Exception.IdNotFoundException;
import com.example.model.Accounts;
import com.example.model.UsersTable;
import com.example.repo.UserTableRepo;
import com.example.service.BankService;


@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	BankService bs;
	
	@Autowired
	UserTableRepo userrepo;
	
	@Autowired
	PasswordEncoder encode;
	
	@PostMapping("/save")
	public List<Accounts> addAccount(@RequestBody Accounts acct){
		return bs.addAccount(acct);
	}
	
	@GetMapping("/fetch")
	public List<Accounts> showAccounts(){
		return bs.showAccounts();
	}
	
	@DeleteMapping("/del/{id}")
	public List<Accounts> deleteAccount(@PathVariable("id") int id) {
		bs.deleteAccount(id);
		return bs.deleteAccount(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Accounts> updateAccount(@PathVariable("id") int id, @RequestBody Accounts acct){
		return bs.updateAccount(acct, id);
	}
	
	@GetMapping("/fetch/{id}")
	public Accounts findById(@PathVariable int id){
		return bs.findById(id);
	}
	
	@GetMapping("/user/{name}")
	public UserDetails fetchUser(@PathVariable("name") String name) {
		return userrepo.findByName(name);
	}
	
	@PutMapping("/changepwd/{id}/{pwd}")
	public String changePassword(@PathVariable("id") int id,@PathVariable("pwd") String pwd) {
		UsersTable user = userrepo.findById(id).orElseThrow(() ->
		new IdNotFoundException("User not found with Id"+id));
		user.setPasscode(encode.encode(pwd));
		userrepo.save(user);
		return "password changed successfully";
	}
}
