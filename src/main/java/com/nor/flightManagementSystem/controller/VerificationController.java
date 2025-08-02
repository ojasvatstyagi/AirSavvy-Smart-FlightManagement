package com.nor.flightManagementSystem.controller;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.bean.VerificationToken;
import com.nor.flightManagementSystem.repository.ProfileRepository;
import com.nor.flightManagementSystem.repository.VerificationTokenRepository;
import com.nor.flightManagementSystem.service.EmailService;
import com.nor.flightManagementSystem.service.FlightUserService;
import com.nor.flightManagementSystem.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.UUID;

@RestController
public class VerificationController {

    private final VerificationTokenRepository tokenRepository;
    private final FlightUserService userService;
    private final EmailService emailService;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    @Value("${app.base-url}")
    private String appBaseUrl;

    @Autowired
    public VerificationController(VerificationTokenRepository tokenRepository,
                                  FlightUserService userService,
                                  EmailService emailService,
                                  ProfileRepository profileRepository,
                                  ProfileService profileService) {
        this.tokenRepository = tokenRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    @GetMapping("/verify")
    public ModelAndView verifyUser(@RequestParam("token") String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null) {
            return new ModelAndView("errorPage").addObject("error", "Invalid verification token.");
        }

        if (verificationToken.getExpiryTime().before(new Date())) {
            return new ModelAndView("errorPage").addObject("error", "Verification token expired.");
        }

        FlightUser user = userService.findByUsername(verificationToken.getUser().getUsername());
        if (user == null) {
            return new ModelAndView("errorPage").addObject("error", "User not found.");
        }

        if (!user.isEnabled()) {
            user.setEnabled(true);
            userService.save(user);
            profileService.createEmptyProfileForUser(user);
        }

        return new ModelAndView("successPage");
    }

    @PostMapping("/resendVerificationEmail")
    public ModelAndView resendVerificationEmail(@RequestParam("email") String email) {
        FlightUser user = userService.findByEmail(email);
        if (user == null || user.isEnabled()) {
            return new ModelAndView("loginPage")
                    .addObject("message", "Your account is already verified or does not exist. Please log in.");
        }

        // Generate new token
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);

        // Send email
        String verificationUrl = appBaseUrl + "/verify?token=" + token;
        emailService.sendVerificationEmail(user.getEmail(), verificationUrl);

        return new ModelAndView("verificationEmailSentPage");
    }
}