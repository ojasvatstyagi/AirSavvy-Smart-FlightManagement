package com.nor.flightManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.dao.FlightUserRepository;

@Service
public class FlightUserService implements UserDetailsService {

    @Autowired
    private FlightUserRepository repository;


    private String type;

    // to save user details in database
    public void save(FlightUser user) {
        user.setPassword(user.getPassword());
        repository.save(user);
    }

    public String getType() {
        return type;
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
}
