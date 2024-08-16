package com.nor.flightManagementSystem.controller;

import com.nor.flightManagementSystem.bean.Contact;
import com.nor.flightManagementSystem.bean.Profile;
import com.nor.flightManagementSystem.bean.VerificationToken;
import com.nor.flightManagementSystem.exception.DatabaseException;
import com.nor.flightManagementSystem.exception.UserAlreadyExistsException;
import com.nor.flightManagementSystem.repository.ContactRepository;
import com.nor.flightManagementSystem.repository.ProfileRepository;
import com.nor.flightManagementSystem.repository.VerificationTokenRepository;
import com.nor.flightManagementSystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.nor.flightManagementSystem.bean.FlightUser;
import com.nor.flightManagementSystem.service.FlightUserService;

import java.util.UUID;

@ControllerAdvice
@RestController
public class LoginController {

    private final FlightUserService userService;
    private final ProfileRepository profileRepository;
    private final ContactRepository contactRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final VerificationTokenRepository tokenRepository;

    @Autowired
    public LoginController(FlightUserService userService, ProfileRepository profileRepository, ContactRepository contactRepository, PasswordEncoder passwordEncoder, EmailService emailService, VerificationTokenRepository tokenRepository) {
        this.userService = userService;
        this.profileRepository = profileRepository;
        this.contactRepository = contactRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/fms")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }

    @GetMapping({"/index", "/"})
    public ModelAndView indexPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return new ModelAndView("redirect:/loginpage");
        }

        String username = authentication.getName();
        String userRole = userService.getRoleByUsername(username);

        String indexPage = userRole.equalsIgnoreCase("Admin") ? "indexAdm" : "indexCust";
        ModelAndView mv = new ModelAndView(indexPage);
        mv.addObject("username", username);
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView registrationPage() {
        return new ModelAndView("signup")
                .addObject("userRecord", new FlightUser());
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("userRecord") FlightUser user) {
        if (userService.userExists(user.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists. Please choose a different username.");
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);

            // Generate the verification token
            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = new VerificationToken(token, user);
            tokenRepository.save(verificationToken);

            // Send the verification email
            String verificationUrl = "http://localhost:9090/verify?token=" + token;
            emailService.sendVerificationEmail(user.getEmail(), verificationUrl);

            return new ModelAndView("registrationSuccessPage");
        } catch (MailException e) {
            return new ModelAndView("errorPage").addObject("error", "There was an error sending the verification email.");
        } catch (Exception e) {
            throw new DatabaseException("Problem saving user to the database");
        }
    }


    @GetMapping("/profile")
    public ModelAndView userProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Profile profile = profileRepository.findByFlightUser_Username(username);
        FlightUser flightUser = userService.findByUsername(username);

        return new ModelAndView("myProfile")
                .addObject("profile", profile)
                .addObject("flightuser", flightUser);
    }

    @GetMapping("/loginpage")
    public ModelAndView showLoginPage(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("login");
        if (error != null) {
            mv.addObject("error", "Invalid username or password.");
        }
        return mv;
    }

    @GetMapping("/about")
    public ModelAndView showAboutPage() {
        return new ModelAndView("about")
                .addObject("contact", new Contact());
    }

    @PostMapping("/about")
    public ModelAndView saveContact(@ModelAttribute("contact") Contact contact) {
        try {
            contactRepository.save(contact);
            return new ModelAndView("about");
        } catch (Exception e) {
            throw new DatabaseException("Problem saving contact to the database");
        }
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ModelAndView("signup")
                .addObject("userRecord", new FlightUser())
                .addObject("error", e.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleDatabaseException(DatabaseException e) {
        return new ModelAndView("errorPage")
                .addObject("error", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception e) {
        return new ModelAndView("errorPage")
                .addObject("error", "An unexpected problem occurred. Please try again later.");
    }
}

