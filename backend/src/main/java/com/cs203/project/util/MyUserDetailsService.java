package com.cs203.project.util;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cs203.project.employee.Employee;
import com.cs203.project.employee.EmployeeRepository;
import com.cs203.project.firm.Firm;
import com.cs203.project.firm.FirmRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	
	FirmRepository firmRepository;
	
	EmployeeRepository employeeRepository;
	
	public MyUserDetailsService(FirmRepository firmRepository, EmployeeRepository employeeRepository) {
		this.firmRepository = firmRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Firm firm = firmRepository.findByEmail(username);
		if (firm == null) {
			Employee employee = employeeRepository.findByEmail(username);
			if (employee == null) {
				throw new UsernameNotFoundException("Not found: " + username);
			}
			return new MyUserDetails(employee);
		}
		return new MyUserDetails(firm);
		
	}

}
