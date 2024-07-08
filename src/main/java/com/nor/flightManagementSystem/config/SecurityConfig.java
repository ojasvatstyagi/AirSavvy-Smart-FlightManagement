package com.nor.flightManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.nor.flightManagementSystem.service.FlightUserService;


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
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/register", "/about", "/fms", "/assets/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/loginpage")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index", true)
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/index")
                .permitAll();

        // Disable CSRF for simplicity
        http.csrf().disable();
    }
	
}