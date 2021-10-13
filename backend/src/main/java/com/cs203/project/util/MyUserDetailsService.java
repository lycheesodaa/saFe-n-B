package com.cs203.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cs203.project.account.Account;
import com.cs203.project.account.AccountRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account = accountRepository.findByEmail(username);
		if (account == null) {
			throw new UsernameNotFoundException("Not found: " + username);
		}
		return new MyUserDetails(account);
		
	}

}
