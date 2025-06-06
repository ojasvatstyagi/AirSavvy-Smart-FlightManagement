package com.nor.flightManagementSystem.service;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.repository.FlightUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightUserService implements UserDetailsService {

    @Autowired
    private FlightUserRepository repository;

    public void save(FlightUser user) {
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean userExists(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public FlightUser findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public String getRoleByUsername(String username) {
        return repository.findByUsername(username)
                .map(FlightUser::getRole)
                .orElse(null);
    }

    public FlightUser findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public void deleteUser(String username) {
        repository.deleteById(username);
    }
}