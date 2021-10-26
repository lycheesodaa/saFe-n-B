package com.cs203.project.account;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cs203.project.util.AuthenticationRequest;
import com.cs203.project.util.AuthenticationResponse;
import com.cs203.project.util.JwtUtil;
import com.cs203.project.util.MyUserDetails;
import com.cs203.project.util.MyUserDetailsService;

@CrossOrigin()
@RestController
public class AccountController {
	
	@Autowired MyUserDetailsService userDetailsService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@RequestMapping("/accounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	@PostMapping("/accounts/authenticate")
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
		
	@RequestMapping("/accounts/{email}")
	public ResponseEntity<Account> getAccount(@PathVariable String email) {
		return accountService.getAccountByEmail(email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/accounts")
	public ResponseEntity<?> addAccount(@RequestBody Account account) {
		Account savedAccount = accountService.addAccount(account);
		if (savedAccount != null) {
			final MyUserDetails userDetails = (MyUserDetails) userDetailsService
					.loadUserByUsername(savedAccount.getEmail());
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			AuthenticationResponse response = new AuthenticationResponse(jwt, userDetails);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/accounts")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
		return accountService.updateAccount(account);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/accounts/{email}")
	public ResponseEntity<Void> deleteAccount(@PathVariable String email) {
		return accountService.deleteAccount(email);
	}

	
}
