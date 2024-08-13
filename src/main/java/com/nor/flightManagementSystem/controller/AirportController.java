package com.nor.flightManagementSystem.controller;

import java.util.List;

import com.nor.flightManagementSystem.exception.DatabaseException;
import com.nor.flightManagementSystem.exception.DuplicateAirportCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.nor.flightManagementSystem.bean.Airport;
import com.nor.flightManagementSystem.repository.AirportDao;

@ControllerAdvice
@RestController
public class AirportController {

    @Autowired
    private AirportDao airportDao;

    // Show the page to add a new airport
    @GetMapping("/addAirport")
    public ModelAndView showAddAirportPage() {
        ModelAndView mv = new ModelAndView("addAirport");
        mv.addObject("airportDetails", new Airport());
        return mv;
    }

    // Handle the submission of a new airport
    @PostMapping("/addAirport")
    public ModelAndView saveAirport(@ModelAttribute("airportDetails") Airport airport) {
        try {
            String airportCode = airport.getAirportCode().toUpperCase();
            if (airportDao.findAirportById(airportCode).isPresent()) {
                throw new DuplicateAirportCodeException("Airport with code " + airportCode + " already exists.");
            }
            airport.setAirportCode(airportCode);
            airport.setAirportLocation(airport.getAirportLocation().toUpperCase());
            airportDao.addAirport(airport);
            return new ModelAndView("redirect:/addAirport?message=Airport details added successfully");
        } catch (DuplicateAirportCodeException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Problem saving airport to the database", e);
        }
    }

    // Show a list of all airports
    @GetMapping("/viewAirports")
    public ModelAndView showAirportReportPage() {
        try {
            List<Airport> airportList = airportDao.findAllAirports();
            ModelAndView mv = new ModelAndView("viewAirportPage");
            mv.addObject("airportList", airportList);
            return mv;
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving airports from the database", e);
        }
    }

    // Show details of a single airport by ID
    @GetMapping("/viewAirports/{id}")
    public ModelAndView showSingleAirportPage(@PathVariable("id") String id) {
        try {
            Airport airport = airportDao.findAirportById(id)
                    .orElseThrow(() -> new DatabaseException("Airport with ID " + id + " not found"));
            ModelAndView mv = new ModelAndView("checkSingleAirport");
            mv.addObject("airport", airport);
            return mv;
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving the airport from the database", e);
        }
    }

    // Show the page to select an airport by code
    @GetMapping("/viewAirportCode")
    public ModelAndView showAirportSelectPage() {
        try {
            List<String> codeList = airportDao.findAllAirportCodes();
            ModelAndView mv = new ModelAndView("checkSingleAirport");
            mv.addObject("codeList", codeList);
            return mv;
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving airport codes from the database", e);
        }
    }

    // Show the page to modify an airport
    @GetMapping("/modifyAirport")
    public ModelAndView showModifyAirportPage() {
        try {
            List<Airport> airports = airportDao.findAllAirports();
            ModelAndView mv = new ModelAndView("modifyAirport");
            mv.addObject("airports", airports);
            return mv;
        } catch (Exception e) {
            throw new DatabaseException("Error retrieving airports from the database", e);
        }
    }

    // Handle airport deletion
    @PostMapping("/delete")
    public ModelAndView deleteAirport(@RequestParam("airportCode") String airportCode) {
        try {
            airportDao.deleteAirportByCode(airportCode);
            return new ModelAndView("redirect:/modifyAirport?message=Airport deleted successfully");
        } catch (Exception e) {
            throw new DatabaseException("Error deleting airport from the database", e);
        }
    }

    // Handle airport update
    @PostMapping("/updateAirport")
    public ModelAndView updateAirport(@RequestParam String airportCode,
                                      @RequestParam String airportLocation,
                                      @RequestParam String details) {
        try {
            Airport airport = airportDao.findAirportById(airportCode)
                    .orElseThrow(() -> new DatabaseException("Airport with code " + airportCode + " not found"));
            airport.setAirportLocation(airportLocation.toUpperCase());
            airport.setDetails(details);
            airportDao.updateAirport(airport);
            return new ModelAndView("redirect:/modifyAirport?message=Airport details updated successfully");
        } catch (Exception e) {
            throw new DatabaseException("Problem updating airport in the database", e);
        }
    }

    // Exception handling for duplicate airport codes
    @ExceptionHandler(DuplicateAirportCodeException.class)
    public ModelAndView handleDuplicateAirportCodeException(DuplicateAirportCodeException e) {
        ModelAndView mv = new ModelAndView("errorPage");
        mv.addObject("error", e.getMessage());
        return mv;
    }

    // Exception handling for database-related issues
    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleDatabaseException(DatabaseException e) {
        ModelAndView mv = new ModelAndView("errorPage");
        mv.addObject("error", e.getMessage());
        return mv;
    }

    // General exception handling
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception e) {
        ModelAndView mv = new ModelAndView("errorPage");
        mv.addObject("error", "An unexpected error occurred. Please try again later.");
        return mv;
    }
}
