<<<<<<< HEAD
package com.cs203.project.users.employee;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs203.project.users.MyUserDetails;
import com.cs203.project.users.MyUserDetailsService;
import com.cs203.project.util.AuthenticationRequest;
import com.cs203.project.util.AuthenticationResponse;
import com.cs203.project.util.JwtUtil;

@CrossOrigin()
@RestController
public class EmployeeController {
	
	private MyUserDetailsService userDetailsService;

	private EmployeeService employeeService;

	private AuthenticationManager authenticationManager;
	
	private JwtUtil jwtTokenUtil;
	
	public EmployeeController(MyUserDetailsService userDetailsService, EmployeeService employeeService, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
		this.userDetailsService = userDetailsService;
		this.employeeService = employeeService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}
	
	@RequestMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@PostMapping("/employees/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getHashedPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect email or password", e);
		}
		
		final MyUserDetails userDetails = (MyUserDetails) userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse response = new AuthenticationResponse(jwt, userDetails);
		return ResponseEntity.ok(response);
		}
		
	@RequestMapping("/employees/{email}")
	public ResponseEntity<Employee> getEmployee(@PathVariable String email) {
		return employeeService.getEmployeeByEmail(email);
	}
	
	@RequestMapping("/employees/firm/{email}")
	public ResponseEntity<?> getEmployees(@PathVariable String email) {
		return employeeService.getEmployeesByFirmEmail(email);
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/employees/firm/{email}")
	public ResponseEntity<?> addEmployee(@RequestBody Employee Employee, @PathVariable String email) {
		Employee savedEmployee = employeeService.addEmployee(Employee, email);
		if (savedEmployee != null) {
			final MyUserDetails userDetails = (MyUserDetails) userDetailsService
					.loadUserByUsername(savedEmployee.getEmail());
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			AuthenticationResponse response = new AuthenticationResponse(jwt, userDetails);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees/{email}/temperature")
	public ResponseEntity<?> addTemperature(@RequestBody Temperature temperature, @PathVariable String email) {
		return employeeService.addTemperature(temperature, email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees/{email}/art")
	public ResponseEntity<?> addART(@RequestBody ART art, @PathVariable String email) {
		return employeeService.addART(art, email);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee Employee) {
		return employeeService.updateEmployee(Employee);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employees/{email}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String email) {
		return employeeService.deleteEmployee(email);
	}

	
}
=======
package com.cs203.project.users.employee;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs203.project.users.MyUserDetails;
import com.cs203.project.users.MyUserDetailsService;
import com.cs203.project.util.AuthenticationRequest;
import com.cs203.project.util.AuthenticationResponse;
import com.cs203.project.util.JwtUtil;

@CrossOrigin()
@RestController
public class EmployeeController {
	
	private MyUserDetailsService userDetailsService;

	private EmployeeService employeeService;

	private AuthenticationManager authenticationManager;
	
	private JwtUtil jwtTokenUtil;
	
	public EmployeeController(MyUserDetailsService userDetailsService, EmployeeService employeeService, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
		this.userDetailsService = userDetailsService;
		this.employeeService = employeeService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}
	
	@RequestMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@PostMapping("/employees/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getHashedPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect email or password", e);
		}
		
		final MyUserDetails userDetails = (MyUserDetails) userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse response = new AuthenticationResponse(jwt, userDetails);
		return ResponseEntity.ok(response);
		}
		
	@RequestMapping("/employees/{email}")
	public ResponseEntity<Employee> getEmployee(@PathVariable String email) {
		return employeeService.getEmployeeByEmail(email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees")
	public ResponseEntity<?> addEmployee(@RequestBody Employee Employee) {
		Employee savedEmployee = employeeService.addEmployee(Employee);
		if (savedEmployee != null) {
			final MyUserDetails userDetails = (MyUserDetails) userDetailsService
					.loadUserByUsername(savedEmployee.getEmail());
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			AuthenticationResponse response = new AuthenticationResponse(jwt, userDetails);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees/{email}/temperature")
	public ResponseEntity<?> addTemperature(@RequestBody Temperature temperature, @PathVariable String email) {
		return employeeService.addTemperature(temperature, email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees/{email}/art")
	public ResponseEntity<?> addART(@RequestBody ART art, @PathVariable String email) {
		return employeeService.addART(art, email);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee Employee) {
		return employeeService.updateEmployee(Employee);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employees/{email}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String email) {
		return employeeService.deleteEmployee(email);
	}

	
}
>>>>>>> 5edcc10ffe0cb908ab82af6dae873676e6872857
