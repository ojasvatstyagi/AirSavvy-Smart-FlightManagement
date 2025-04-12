package com.nor.flightManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.nor.flightManagementSystem.service.FlightUserService;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FlightUserService service;

	@Autowired
	private EncoderConfig config;

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder authority) throws Exception {
		authority.userDetailsService(service).passwordEncoder(config.passwordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests
						.antMatchers("/register", "/images/**", "/fms", "/about", "/assets/**","/verify")
						.permitAll()
						.anyRequest()
						.authenticated())
				.formLogin(login -> login
						.loginPage("/loginpage")
						.failureUrl("/loginpage?error=true")
						.loginProcessingUrl("/login")
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/index"));
		http.csrf(AbstractHttpConfigurer::disable);
	}
}