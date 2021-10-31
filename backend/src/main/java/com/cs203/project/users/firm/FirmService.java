package com.cs203.project.users.firm;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class FirmService {
	
	private FirmRepository firmRepository;
	
	public FirmService(FirmRepository firmRepository) {
		this.firmRepository = firmRepository;
	}
	
	public ResponseEntity<List<Firm>> getAllFirms() {
		List<Firm> firms = firmRepository.findAll();
		return ResponseEntity.ok(firms);
	}
	
	public ResponseEntity<Firm> getFirmByEmail(String email) {
		Firm firm = firmRepository.findByEmail(email);
		if (firm == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(firm);
	}
	
	public Firm addFirm(Firm firm) {
		HttpStatus firmExists = getFirmByEmail(firm.getEmail()).getStatusCode();
		if (firmExists == HttpStatus.OK) {
			return null;
		}
		Firm savedfirm = firmRepository.save(firm.hashingPassword());
		return savedfirm;
	}
	
	public ResponseEntity<Firm> updateFirm(Firm firm) {
		HttpStatus firmExists = getFirmByEmail(firm.getEmail()).getStatusCode();
		if (firmExists == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Firm updatedfirm = firmRepository.save(firm.hashingPassword());
		return ResponseEntity.ok(updatedfirm);
	}
	
	public ResponseEntity<Void> deleteFirm(String email) {
		HttpStatus firmExists = getFirmByEmail(email).getStatusCode();
		if (firmExists == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		firmRepository.deleteById(email);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
