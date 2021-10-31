package com.cs203.project.users.employee;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return ResponseEntity.ok(employees);
	}
	
	public ResponseEntity<Employee> getEmployeeByEmail(String email) {
		Employee employee = employeeRepository.findByEmail(email);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(employee);
	}
	
	public Employee addEmployee(Employee employee) {
		HttpStatus employeeExists = getEmployeeByEmail(employee.getEmail()).getStatusCode();
		if (employeeExists == HttpStatus.OK) {
			return null;
		}
		Employee savedEmployee = employeeRepository.save(employee.hashingPassword());
		return savedEmployee;
	}
	
	public ResponseEntity<Employee> updateEmployee(Employee employee) {
		HttpStatus employeeExists = getEmployeeByEmail(employee.getEmail()).getStatusCode();
		if (employeeExists == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Employee updatedEmployee = employeeRepository.save(employee.hashingPassword());
		return ResponseEntity.ok(updatedEmployee);
	}
	
	public ResponseEntity<Void> deleteEmployee(String email) {
		HttpStatus employeeExists = getEmployeeByEmail(email).getStatusCode();
		if (employeeExists == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		employeeRepository.deleteById(email);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
