package com.cs203.project.User;


import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository users;
    
    public UserService(UserRepository users) {
        this.users = users;
    }
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
        return users.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }
    public UserDetails loadUserByID(long id)throws UsernameNotFoundException{
        return users.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User '" + id + "' not found"));
    }

    
    
}
