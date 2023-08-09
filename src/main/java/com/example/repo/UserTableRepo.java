package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.model.UsersTable;

@Repository
public interface UserTableRepo extends JpaRepository<UsersTable, Integer> {

	public UserDetails findByName(String name);

}
