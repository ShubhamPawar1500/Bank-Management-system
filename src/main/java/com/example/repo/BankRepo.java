package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Accounts;

@Repository
public interface BankRepo extends JpaRepository<Accounts, Integer>{

}
