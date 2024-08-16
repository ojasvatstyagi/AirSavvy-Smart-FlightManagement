package com.nor.flightManagementSystem.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.repository.FlightUserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FlightUserService implements UserDetailsService {

    @Autowired
    private FlightUserRepository repository;

    @Getter
    private String role;

    @Transactional
    public void save(FlightUser user) {
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FlightUser user = repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        role = user.getRole();
        return user;
    }

    public boolean userExists(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public FlightUser findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public String getRoleByUsername(String username) {
        return repository.findByUsername(username).get().getRole();
    }

    public FlightUser findByEmail(String email) {
        return repository.findByEmail(email).orElse(null); // Handle the case where user is not found
    }
}
