package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Exception.IdGreaterThan40Exception;
import com.example.Exception.IdNotFoundException;
import com.example.model.Accounts;
import com.example.repo.BankRepo;

@Service
public class BankService implements Bank{
	
	@Autowired
	BankRepo br;

	@Override
	public List<Accounts> addAccount(Accounts acct) {
		
			br.save(acct);
			return br.findAll();
		

	}

	@Override
	public List<Accounts> showAccounts() {
		
		return br.findAll();
	}

	@Override
	public List<Accounts> deleteAccount(int id) {
		br.deleteById(id);
		return br.findAll();
	}

	@Override
	public ResponseEntity<Accounts> updateAccount(Accounts acct, int id) {
		Accounts acc = br.findById(id).orElseThrow(() ->
			new IdNotFoundException("account not found with id"+id));
		acc.setName(acct.getName());
		acc.setLastname(acct.getLastname());
		acc.setEmail(acct.getEmail());
		acc.setPhone(acct.getPhone());
		br.save(acc);
		return ResponseEntity.ok(acct);
		
	}
	
	public Accounts findById(int id){
		Accounts acct =  br.findById(id).orElseThrow(() -> 
			new IdNotFoundException("ID not found"));
		return acct;
	}

	

}
