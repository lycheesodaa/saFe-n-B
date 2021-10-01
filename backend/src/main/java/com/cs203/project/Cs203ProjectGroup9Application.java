package com.cs203.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cs203.project.user.User;
import com.cs203.project.user.UserRepository;

@SpringBootApplication
@EnableScheduling
public class Cs203ProjectGroup9Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Cs203ProjectGroup9Application.class, args);

		// JPA user repository init
		UserRepository users = ctx.getBean(UserRepository.class);
		BCryptPasswordEncoder encoder = ctx.getBean(BCryptPasswordEncoder.class);
		System.out.println("[Add user]: "
				+ users.save(new User("admin", encoder.encode("goodpassword"), "ROLE_ADMIN")).getUsername());
	}

}
