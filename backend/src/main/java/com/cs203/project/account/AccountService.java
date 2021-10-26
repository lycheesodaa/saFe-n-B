package com.cs203.project.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return ResponseEntity.ok(accounts);
	}
	
	public ResponseEntity<Account> getAccountByEmail(String email) {
		Account account = accountRepository.findByEmail(email);
		if (account == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(account);
	}
	
	public Account addAccount(Account account) {
		HttpStatus accountExists = getAccountByEmail(account.getEmail()).getStatusCode();
		if (accountExists == HttpStatus.OK) {
			return null;
		}
		Account savedAccount = accountRepository.save(account.hashingPassword());
		return savedAccount;
	}
	
	public ResponseEntity<Account> updateAccount(Account account) {
		HttpStatus accountExists = getAccountByEmail(account.getEmail()).getStatusCode();
		if (accountExists == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Account updatedAccount = accountRepository.save(account.hashingPassword());
		return ResponseEntity.ok(updatedAccount);
	}
	
	public ResponseEntity<Void> deleteAccount(String email) {
		HttpStatus accountExists = getAccountByEmail(email).getStatusCode();
		if (accountExists == HttpStatus.NOT_FOUND) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		accountRepository.deleteById(email);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
