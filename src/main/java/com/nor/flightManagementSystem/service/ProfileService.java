package com.nor.flightManagementSystem.service;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.bean.Profile;
import com.nor.flightManagementSystem.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final FlightUserService userService;

    public void createEmptyProfileForUser(FlightUser user) {
        Profile profile = new Profile();
        profile.setUser(user);
        profileRepository.save(profile);
    }

    public void updateProfile(String username, String firstName, String lastName, String phone, String address, Long aadharNumber) {
        Profile profile = profileRepository.findByUser_Username(username);

        if (profile == null) {
            FlightUser user = userService.findByUsername(username);
            profile = new Profile();
            profile.setUser(user);
        }

        if (aadharNumber != null && (aadharNumber < 100000000000L || aadharNumber > 999999999999L)) {
            throw new IllegalArgumentException("Aadhaar number must be exactly 12 digits.");
        }

        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setPhone(phone);
        profile.setAddress(address);
        profile.setAadharNumber(aadharNumber);

        profileRepository.save(profile);
    }

    public void uploadProfileImage(String username, byte[] imageBytes) {
        Profile profile = profileRepository.findByUser_Username(username);

        if (profile == null) {
            FlightUser user = userService.findByUsername(username);
            profile = new Profile();
            profile.setUser(user);
        }

        profile.setPhoto(imageBytes);
        profileRepository.save(profile);
    }

    public Profile findByUser_Username(String username) {
        return profileRepository.findByUser_Username(username);
    }
}