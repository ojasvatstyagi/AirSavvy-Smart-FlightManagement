package com.nor.flightManagementSystem.controller;

import com.nor.flightManagementSystem.bean.Profile;
import com.nor.flightManagementSystem.dao.ProfileRepository;
import com.nor.flightManagementSystem.exception.DatabaseException;
import com.nor.flightManagementSystem.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.service.FlightUserService;


@ControllerAdvice
@RestController
public class LoginController {

    @Autowired
    private FlightUserService userService;
    
    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/fms")
    public ModelAndView showHomePage() {
        return new ModelAndView("home");
    }

    @GetMapping("/register")
    public ModelAndView showUserRegistrationPage() {
        FlightUser user = new FlightUser();
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("userRecord", user);
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView saveUserRegistrationPage(@ModelAttribute("userRecord") FlightUser user) {

        if (userService.userExists(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists. Please choose a different username.");
        }
        try {
            String encodedPassword = bCrypt.encode(user.getPassword()); // encrypts the password
            FlightUser newUser = new FlightUser();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(encodedPassword);
            newUser.setType(user.getType());
            newUser.setEmail(user.getEmail());
            userService.save(newUser);
            return new ModelAndView("loginPage");
        } catch (Exception e) {
            throw new DatabaseException("Error saving user to the database", e);
        }
    }

    @GetMapping("/profile")
    public ModelAndView showUserProfile() {
        // Get the logged-in username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Fetch the profile and flight user details
        Profile profile = profileRepository.findByFlightUser_Username(username);
        FlightUser flightUser = userService.findByUsername(username);

        ModelAndView mv = new ModelAndView("myProfile");
        // Add details to the model
        mv.addObject("profile", profile);
        mv.addObject("flightuser", flightUser);

        return mv; // This should be the name of your JSP page
    }


    @GetMapping("/loginpage")
    public ModelAndView showLoginPage() {
        return new ModelAndView("loginPage");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        FlightUser user = new FlightUser();
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("userRecord", user);
        mv.addObject("error", e.getMessage());
        return mv;
    }

    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleDatabaseException(DatabaseException e) {
        ModelAndView mv = new ModelAndView("errorPage");
        mv.addObject("error", "A database error occurred. Please try again later.");
        return mv;
    }
}
