package com.nor.flightManagementSystem.repository;

import java.util.Optional;

import com.nor.flightManagementSystem.bean.FlightUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightUserRepository extends MongoRepository<FlightUser, String> {
	Optional<FlightUser> findByUsername(String username);
	Optional<FlightUser> findByEmail(String email);
}
