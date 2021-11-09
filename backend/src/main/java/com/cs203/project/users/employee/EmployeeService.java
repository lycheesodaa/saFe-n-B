package com.cs203.project.users.employee;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cs203.project.users.firm.Firm;
import com.cs203.project.users.firm.FirmRepository;


@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	private TemperatureRepository temperatureRepository;
	private ARTRepository artRepository;
	private FirmRepository firmRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository, TemperatureRepository temperatureRepository, ARTRepository artRepository, FirmRepository firmRepository) {
		this.employeeRepository = employeeRepository;
		this.temperatureRepository = temperatureRepository;
		this.artRepository = artRepository;
		this.firmRepository = firmRepository;
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
	
	public ResponseEntity<List<Employee>> getEmployeesByFirmEmail(String email) {
		List<Employee> employees = employeeRepository.findByFirmEmail(email);
		if (employees == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(employees);
	}
	
	public Employee addEmployee(Employee employee, String email) {
		HttpStatus employeeExists = getEmployeeByEmail(employee.getEmail()).getStatusCode();
		if (employeeExists == HttpStatus.OK) {
			return null;
		}
		Firm firm = firmRepository.findByEmail(email);
		if (firm == null) {
			return null;
		}
		employee.setFirm(firm);
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

	public ResponseEntity<Employee> addTemperature(Temperature temperature, String email) {
		Employee employee = employeeRepository.findByEmail(email);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		temperature.setEmployee(employee);
		temperatureRepository.save(temperature);
		return ResponseEntity.ok(employeeRepository.findByEmail(email));
	}
	
	public ResponseEntity<Employee> addART(ART art, String email) {
		Employee employee = employeeRepository.findByEmail(email);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		art.setEmployee(employee);
		artRepository.save(art);
		return ResponseEntity.ok(employeeRepository.findByEmail(email));
	}

}
