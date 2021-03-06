package com.cs203.project.users.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	public Employee findByEmail(String email);
	public List<Employee> findByFirmEmail(String email);
}
