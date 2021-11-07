package com.cs203.project.users.employee;


import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cs203.project.users.firm.Firm;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employee")
public class Employee {
	
	@Id
	private String email;
	
	private String password;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  
	private Date dateOfBirth;

	private String name;

	private String nric;

	private String address;

	private String contact;

	private boolean vaccinated;

	@OneToMany(mappedBy = "employee", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
	private List<ART> artList;

	@OneToMany(mappedBy = "employee", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
	private List<Temperature> tempList;
	
	@ManyToOne
	@JoinColumn(name="firm_email")
	@JsonBackReference
	private Firm firm;
	
	
	public void addTemperature(Temperature temperature) {
		this.tempList.add(temperature);
	}
	
	
	public Employee hashingPassword(){
        String hashedPassword = Base64.getEncoder().encodeToString(this.getPassword().getBytes());
        this.setPassword(hashedPassword);
        return this;
    }

	
	
}