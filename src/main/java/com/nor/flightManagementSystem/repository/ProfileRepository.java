package com.nor.flightManagementSystem.repository;
import com.nor.flightManagementSystem.bean.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, BigInteger> {
    Profile findByFlightUser_Username(String username);
}
