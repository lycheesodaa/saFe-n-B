package com.cs203.project.users.firm;

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
public class FirmController {

	private MyUserDetailsService userDetailsService;

	private FirmService firmService;

	private AuthenticationManager authenticationManager;

	private JwtUtil jwtTokenUtil;

	public FirmController(MyUserDetailsService userDetailsService, FirmService firmService,
			AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
		this.userDetailsService = userDetailsService;
		this.firmService = firmService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@RequestMapping("/firms")
	public ResponseEntity<List<Firm>> getAllFirms() {
		return firmService.getAllFirms();
	}

	@PostMapping("/test")
	public String testing(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		return authenticationRequest.getHashedPassword();
		// try {
		// authenticationManager.authenticate(new
		// UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
		// authenticationRequest.getHashedPassword()));
		// } catch (BadCredentialsException e) {
		// throw new Exception("Incorrect email or password", e);
		// }
		//

	}

	@PostMapping("/firms/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getHashedPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect email or password", e);
		}

		final MyUserDetails userDetails = (MyUserDetails) userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse response = new AuthenticationResponse(jwt, userDetails);
		return ResponseEntity.ok(response);
	}

	@RequestMapping("/firms/{email}")
	public ResponseEntity<Firm> getFirm(@PathVariable String email) {
		return firmService.getFirmByEmail(email);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/firms")
	public ResponseEntity<?> addFirm(@RequestBody Firm firm) {
		Firm savedFirm = firmService.addFirm(firm);
		if (savedFirm != null) {
			final MyUserDetails userDetails = (MyUserDetails) userDetailsService
					.loadUserByUsername(savedFirm.getEmail());
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			AuthenticationResponse response = new AuthenticationResponse(jwt, userDetails);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/firms")
	public ResponseEntity<Firm> updateFirm(@RequestBody Firm firm) {
		return firmService.updateFirm(firm);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/firms/{email}")
	public ResponseEntity<Void> deleteFirm(@PathVariable String email) {
		return firmService.deleteFirm(email);
	}

}
