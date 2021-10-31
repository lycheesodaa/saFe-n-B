package com.cs203.project.users.firm;



import java.util.Base64;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cs203.project.users.employee.Employee;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Firm")
public class Firm {
	
	@Id
	@Column(name="firm_email")
	private String email;
	
	private String password;
	
	@Column(name="registration_date")
	private String dateOfBirth;
	
    @OneToMany(mappedBy="firm", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employeeList;
	
	
	public Firm() {
		
	}
	
	public Firm(String email, String password, String dateOfBirth) {
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public Firm hashingPassword(){
        String hashedPassword = Base64.getEncoder().encodeToString(this.getPassword().getBytes());
//        String hashedPassword = new BCryptPasswordEncoder().encode(this.getPassword());
        Firm firm = new Firm(this.email, hashedPassword, this.dateOfBirth);
        return firm;
    }
	
	
}