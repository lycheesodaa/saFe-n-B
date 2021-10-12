package com.cs203.project.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class UserController {
    private UserRepository users;
    private BCryptPasswordEncoder encoder;

    public UserController(UserRepository users, BCryptPasswordEncoder encoder){
        this.users = users;
        this.encoder = encoder;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return users.findAll();
    }

    /**
    * Using BCrypt encoder to encrypt the password for storage 
    * @param user
     * @return
     */
    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return users.save(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        return users.save(user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id){
        Optional<User> user = users.findById(id);
        if (user.isPresent()){
            users.delete(user.get());
            return ("User deleted");
        }
        else{
            throw new RuntimeException("User not found");
        }
    }


    
    
    
   
}