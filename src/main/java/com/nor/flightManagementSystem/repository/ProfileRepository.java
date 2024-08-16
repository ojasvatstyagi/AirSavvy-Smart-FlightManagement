package com.nor.flightManagementSystem.repository;
import com.nor.flightManagementSystem.bean.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    @Query("{ 'flight_users.username': ?0 }")
    Profile findByUser_Username(String username); // Use 'user' to reference FlightUser
}
