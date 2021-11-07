package com.cs203.project.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	UserDetailsService userDetailsService;
	
	JwtRequestFilter jwtRequestFilter;
	
	public SecurityConfiguration(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}
	
	//Authentication Setup
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		
	}
	
	//Authorization setup
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.antMatcher("/#/login")
                .httpBasic().disable()
                .formLogin().disable()
		.csrf().disable()
		.authorizeRequests().antMatchers("/accounts","/accounts/*").permitAll().
				anyRequest().authenticated().
				// and().csrf().ignoringAntMatchers("/#/login").
				and().exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

// 	@Override
// public void configure(WebSecurity web) throws Exception {
//     web.ignoring().antMatchers("/#/**");
// }


	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	// @SuppressWarnings("deprecation")
	// @Bean
	// public PasswordEncoder getPasswordEncoder() {
	// 	return NoOpPasswordEncoder.getInstance();
	// }
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
