package com.nor.flightManagementSystem.dao;
import com.nor.flightManagementSystem.bean.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    public  Profile findByFlightUser_Username(String username);
}