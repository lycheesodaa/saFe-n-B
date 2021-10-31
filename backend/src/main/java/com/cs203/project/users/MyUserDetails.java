package com.cs203.project.users;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cs203.project.users.employee.Employee;
import com.cs203.project.users.firm.Firm;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String dateOfBirth;
	// private List<GrantedAuthority> authorities;

	public MyUserDetails(Firm firm) {
		this.email = firm.getEmail();
		this.password = firm.getPassword();
		this.dateOfBirth = firm.getDateOfBirth();
	}

	public MyUserDetails(Employee employee) {
		this.email = employee.getEmail();
		this.password = employee.getPassword();
		this.dateOfBirth = employee.getDateOfBirth();
	}

	public MyUserDetails() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
