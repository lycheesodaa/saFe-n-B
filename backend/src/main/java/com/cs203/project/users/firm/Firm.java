package com.cs203.project.users.firm;



import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cs203.project.users.employee.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Firm")
public class Firm {
	
	@Id
	@Column(name="firm_email")
	private String email;
	
	private String password;
	
	private String typeOfOutlet;
	
	private String name;
	
	private String contact;
	
	@Column(name="registration_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  
	private Date registrationDate;
	
    @OneToMany(mappedBy="firm", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employeeList;
		
	
	public Firm hashingPassword(){
        String hashedPassword = Base64.getEncoder().encodeToString(this.getPassword().getBytes());
//        String hashedPassword = new BCryptPasswordEncoder().encode(this.getPassword());
        this.setPassword(hashedPassword);
        return this;
    }
	
	
}