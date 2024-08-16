package com.nor.flightManagementSystem.controller;

import java.util.List;

import com.nor.flightManagementSystem.exception.DatabaseException;
import com.nor.flightManagementSystem.exception.DuplicateFlightNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.nor.flightManagementSystem.bean.Flight;
import com.nor.flightManagementSystem.bean.Route;
import com.nor.flightManagementSystem.repository.FlightDao;
import com.nor.flightManagementSystem.repository.RouteDao;
import com.nor.flightManagementSystem.service.FlightService;

@RestController
public class FlightController {

    @Autowired
    private FlightDao flightDao;

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private FlightService flightService;

    @GetMapping("/addFlight")
    public ModelAndView showAddFlightPage() {
        try {
            List<Route> routes = routeDao.findAllRoutes();
            return new ModelAndView("addFlight")
                    .addObject("flightRecord", new Flight())
                    .addObject("codeList", routes);
        } catch (Exception e) {
            throw new DatabaseException("Problem fetching routes for flight addition", e);
        }
    }

    @PostMapping("/addFlight")
    public ModelAndView saveFlight(@ModelAttribute("flightRecord") Flight flight,
                                   @RequestParam String returnDeparture,
                                   @RequestParam String returnArrival) {

            // Create return flight and save both flights
            Flight returnFlight = flightService.createReturnFlight(flight, returnDeparture, returnArrival);
            flightDao.addFlight(flight);
            flightDao.addFlight(returnFlight);

            return new ModelAndView("redirect:/addFlight?message=Flight details added successfully");
    }


    @GetMapping("/viewFlights")
    public ModelAndView showAllFlights() {
        try {
            List<Flight> flights = flightDao.showAllFlights();
            return new ModelAndView("viewFlightPage")
                    .addObject("flights", flights);
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving flights from the database", e);
        }
    }

    @GetMapping("/modifyFlight")
    public ModelAndView showModifyFlightPage() {
        try {
            List<Flight> flights = flightDao.showAllFlights();
            List<Route> routes = routeDao.findAllRoutes();
            return new ModelAndView("modifyFlight")
                    .addObject("flights", flights)
                    .addObject("routes", routes);
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving flights for modification", e);
        }
    }

//    @PostMapping("/deleteFlight")
//    public ModelAndView deleteFlight(@RequestParam Long flightNumber) {
//        try {
//            flightDao.deleteFlightByFlightNumber(flightNumber);
//            return new ModelAndView("redirect:/modifyFlight?message=Flight deleted successfully");
//        } catch (Exception e) {
//            throw new DatabaseException("Problem deleting flight from the database", e);
//        }
//    }

    @PostMapping("/updateFlight")
    public ModelAndView updateFlight(@RequestParam Long flightNumber,
                                     @RequestParam String flightName,
                                     @RequestParam String arrival,
                                     @RequestParam String departure,
                                     @RequestParam Long routeId,
                                     @RequestParam Integer seatCapacity) {
        Flight newFlight = new Flight(flightNumber, flightName, routeId, seatCapacity, departure, arrival);
        flightDao.updateFlight(newFlight);
        return new ModelAndView("redirect:/modifyFlight?message=Flight details updated successfully");
    }

    @ExceptionHandler(DuplicateFlightNumberException.class)
    public ModelAndView handleDuplicateFlightNumberException(DuplicateFlightNumberException e) {
        return new ModelAndView("errorPage")
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
                .addObject("error", "An unexpected error occurred. Please try again later.");
    }
}

