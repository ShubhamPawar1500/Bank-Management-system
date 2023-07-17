package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.model.Accounts;

public interface Bank {
	
	public List<Accounts> addAccount(Accounts acct);
	public List<Accounts> showAccounts();
	public List<Accounts> deleteAccount(int id);
	ResponseEntity<Accounts> updateAccount(Accounts acct, int id);

}
