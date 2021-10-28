package com.cs203.project.employee;


import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cs203.project.firm.Firm;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Employee")
public class Employee {
	
	@Id
	private String email;
	
	private String password;
	
	private String dateOfBirth;
	
	@ManyToOne
	@JoinColumn(name="firm_email")
	@JsonBackReference
	private Firm firm;
	
	public Employee() {
		
	}
	
	public Employee(String email, String password, String dateOfBirth) {
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public Employee hashingPassword(){
        String hashedPassword = Base64.getEncoder().encodeToString(this.getPassword().getBytes());
        Employee firm = new Employee(this.email, hashedPassword, this.dateOfBirth);
        return firm;
    }

	
	
}