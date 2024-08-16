package com.nor.flightManagementSystem.controller;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.bean.VerificationToken;
import com.nor.flightManagementSystem.repository.VerificationTokenRepository;
import com.nor.flightManagementSystem.service.EmailService;
import com.nor.flightManagementSystem.service.FlightUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.UUID;

@RestController
public class VerificationController {

    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private FlightUserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/verify")
    public ModelAndView verifyUser(@RequestParam("token") String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null) {
            return new ModelAndView("errorPage")
                    .addObject("error", "Invalid verification token.");
        }

        // Check if the token is expired
        if (verificationToken.getExpiryTime().before(new Date())) {
            return new ModelAndView("errorPage")
                    .addObject("error", "Verification token expired. Please request a new verification email.");
        }

        // Explicitly fetch the user from the repository
        String username = verificationToken.getUser().getUsername();
        FlightUser user = userService.findByUsername(username);

        if (user == null) {
            return new ModelAndView("errorPage")
                    .addObject("error", "User associated with the token not found.");
        }
        if (user.isEnabled()) {
            return new ModelAndView("successPage");
        }

        // Enable user account
        user.setEnabled(true);
        userService.save(user); // Ensure this method is transactional

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

        // Send the email
        String verificationUrl = "http://localhost:9090/verify?token=" + token;
        emailService.sendVerificationEmail(user.getEmail(), verificationUrl);

        return new ModelAndView("verificationEmailSentPage");
    }
}

