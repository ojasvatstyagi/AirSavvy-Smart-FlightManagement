package com.nor.flightManagementSystem.service;

import com.nor.flightManagementSystem.bean.VerificationToken;
import com.nor.flightManagementSystem.exception.UserAlreadyExistsException;
import com.nor.flightManagementSystem.repository.VerificationTokenRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.repository.FlightUserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightUserService  implements UserDetailsService {

    @Autowired
    private FlightUserRepository repository;


    @Getter
    private String type;

    // to save user details in database
    public void save(FlightUser user) {
        user.setPassword(user.getPassword());
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FlightUser user = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        type = user.getType();
        return user;
    }

    public boolean userExists(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public FlightUser findByUsername(String username) {
        return repository.findByUsername(username).get();
    }

    public String getTypeByUsername(String username) {
        return repository.findByUsername(username).get().getType();
    }
}