package com.nor.flightManagementSystem.controller;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.bean.VerificationToken;
import com.nor.flightManagementSystem.repository.VerificationTokenRepository;
import com.nor.flightManagementSystem.service.FlightUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class VerificationController {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private FlightUserService userService;

    @GetMapping("/verify")
    public ModelAndView verifyUser(@RequestParam("token") String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null) {
            return new ModelAndView("errorPage")
                    .addObject("error", "Invalid verification token.");
        }

        FlightUser user = verificationToken.getUser();
        if (user.isEnabled()) {
            return new ModelAndView("loginPage")
                    .addObject("message", "Your account is already verified. Please log in.");
        }

        // Enable user account
        user.setEnabled(true);
        userService.save(user);

        return new ModelAndView("loginPage")
                .addObject("message", "Your account has been verified. Please log in.");
    }
}
