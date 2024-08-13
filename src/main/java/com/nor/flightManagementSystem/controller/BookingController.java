package com.nor.flightManagementSystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nor.flightManagementSystem.exception.*;
import com.nor.flightManagementSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nor.flightManagementSystem.bean.*;
import com.nor.flightManagementSystem.repository.*;

@Controller
public class BookingController {

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private AirportDao airportDao;

    @Autowired
    private FlightDao flightDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private PassengerDao passengerDao;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/searchFlight")
    public ModelAndView searchAllFlights() {
        try {
            List<Flight> flights = flightDao.showAllFlights();
            List<Airport> airports = airportDao.findAllAirports();
            return new ModelAndView("searchFlight")
                    .addObject("airports", airports)
                    .addObject("flights", flights);
        } catch (Exception e) {
            throw new DatabaseException("Error retrieving flights and airports", e);
        }
    }

    @PostMapping("/searchFlight")
    public ModelAndView searchFlights(@RequestParam String sourceAirport,
                                      @RequestParam String destinationAirport) {
        try {
            String fromAirportCode = String.valueOf(airportDao.findAirportCodeByLocation(sourceAirport));
            String toAirportCode = String.valueOf(airportDao.findAirportCodeByLocation(destinationAirport));

            throw new InvalidAirportCodeException("Invalid source or destination airport code.");

        } catch (InvalidAirportCodeException | RouteNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Problem searching flights", e);
        }
    }

    @GetMapping("/bookFlight")
    public ModelAndView showBookingPage(@RequestParam Long flightNumber,
                                        @RequestParam String flightName,
                                        @RequestParam Long routeId,
                                        @RequestParam Double price) {
        return new ModelAndView("booking")
                .addObject("flightNumber", flightNumber)
                .addObject("flightName", flightName)
                .addObject("routeId", routeId)
                .addObject("price", price);
    }

    @PostMapping("/bookFlight")
    public ModelAndView bookFlight(@RequestParam Long routeId,
                                   @RequestParam Long flightNumber,
                                   @RequestParam String flightName,
                                   @RequestParam Double price,
                                   @RequestParam("passengerName") List<String> passengerNames,
                                   @RequestParam("passengerDob") List<String> passengerDobs) {
        try {
            if (passengerNames.isEmpty() || passengerNames.size() != passengerDobs.size()) {
                throw new IncompletePassengerDetailsException("Passenger details are incomplete.");
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Long ticketNumber = ticketDao.findLastTicketNumber() + 1; // Ensure unique ticket number

            Ticket ticket = new Ticket();
            ticket.setTicketNumber(ticketNumber);
            ticket.setRouteId(routeId);
            ticket.setFlightNumber(flightNumber);
            ticket.setFlightName(flightName);
            ticket.setUsername(username);

            double totalAmount = 0.0;
            int passengersCount = passengerNames.size();

            for (int i = 0; i < passengersCount; i++) {
                TicketPassengerEmbed embed = new TicketPassengerEmbed(ticketNumber, i + 1);

                Passenger passenger = new Passenger();
                passenger.setEmbeddedId(embed);
                passenger.setPassengerName(passengerNames.get(i));
                passenger.setPassengerDob(passengerDobs.get(i));

                double passengerPrice = ticketService.calculatePassengerPrice(price, passengerDobs.get(i));
                passenger.setPrice(passengerPrice);

                totalAmount += passengerPrice;
                passengerDao.save(passenger);
            }

            ticket.setTotalAmount(totalAmount);
            ticketDao.save(ticket);

            Flight flight = flightDao.viewFlight(flightNumber);
            flight.setSeatsBooked((flight.getSeatsBooked() != null ? flight.getSeatsBooked() : 0) + passengersCount);
            flightDao.addFlight(flight);

            List<Passenger> bookedPassengers = passengerDao.findByTicketNumber(ticketNumber);

            return new ModelAndView("ticket")
                    .addObject("ticket", ticket)
                    .addObject("passengers", bookedPassengers);
        } catch (IncompletePassengerDetailsException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Problem booking flight", e);
        }
    }

    @GetMapping("/viewBooking")
    public ModelAndView viewBookings() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            List<Ticket> tickets = ticketDao.findTicketsByUsername(username);

            Map<Long, List<Passenger>> passengerDetails = new HashMap<>();
            for (Ticket ticket : tickets) {
                List<Passenger> passengers = passengerDao.findByTicketNumber(ticket.getTicketNumber());
                passengerDetails.put(ticket.getTicketNumber(), passengers);
            }

            return new ModelAndView("viewTicket")
                    .addObject("tickets", tickets)
                    .addObject("passengerDetails", passengerDetails);
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving bookings", e);
        }
    }

    @PostMapping("/cancelBooking")
    public ModelAndView cancelBooking(@RequestParam Long ticketNumber) {
        try {
            Ticket ticket = ticketDao.findTicketByTicketNumber(ticketNumber);
            if (ticket == null) {
                throw new TicketNotFoundException("Ticket not found");
            }

            Flight flight = flightDao.findByFlightNumber(ticket.getFlightNumber());
            int passengerCount = passengerDao.findByTicketNumber(ticketNumber).size();
            int updatedSeats = Math.max(0, (flight.getSeatsBooked() != null ? flight.getSeatsBooked() : 0) - passengerCount);

            flight.setSeatsBooked(updatedSeats);
            flightDao.addFlight(flight);

            ticketDao.deleteByTicketNumber(ticketNumber);

            return new ModelAndView("redirect:/index");
        } catch (TicketNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Problem cancelling booking", e);
        }
    }

    @GetMapping("/viewTickets")
    public ModelAndView showTicketReportPage() {
        try {
            List<Ticket> tickets = ticketDao.findAllTickets();
            return new ModelAndView("viewTickets")
                    .addObject("tickets", tickets);
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving tickets", e);
        }
    }

    @GetMapping("/viewPassengers")
    public ModelAndView showPassengerReportPage() {
        try {
            List<Passenger> passengers = passengerDao.findAllPassengers();
            return new ModelAndView("viewPassenger")
                    .addObject("passengers", passengers);
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving passengers", e);
        }
    }

    @ExceptionHandler(InvalidAirportCodeException.class)
    public ModelAndView handleInvalidAirportCodeException(InvalidAirportCodeException e) {
        return new ModelAndView("errorPage")
                .addObject("error", e.getMessage());
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ModelAndView handleRouteNotFoundException(RouteNotFoundException e) {
        return new ModelAndView("errorPage")
                .addObject("error", e.getMessage());
    }

    @ExceptionHandler(IncompletePassengerDetailsException.class)
    public ModelAndView handleIncompletePassengerDetailsException(IncompletePassengerDetailsException e) {
        return new ModelAndView("errorPage")
                .addObject("error", e.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleDatabaseException(DatabaseException e) {
        return new ModelAndView("errorPage")
                .addObject("error", e.getMessage());
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ModelAndView handleTicketNotFoundException(TicketNotFoundException e) {
        return new ModelAndView("errorPage")
                .addObject("error", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception e) {
        return new ModelAndView("errorPage")
                .addObject("error", "An unexpected error occurred. Please try again later.");
    }
}
