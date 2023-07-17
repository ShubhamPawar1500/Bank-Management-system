package com.example.model;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(unique = true)
	@NotNull(message = "accountNo cannot be blank")
	private int accountNo;
	
	@Column
	@NotBlank(message = "name cannot be blank")
	private String name;
	@Column
	@NotBlank(message = "lastname cannot be blank")
	private String lastname;
	@Column(unique = true)
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "email is invalid")
	private String email;
	@Column
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "Enter valid phone no")
	private String phone;
	
	public Accounts(int id, int accountNo, String name, String lastname, String email, String phone) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
