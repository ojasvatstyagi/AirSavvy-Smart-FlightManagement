package com.nor.flightManagementSystem.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nor.flightManagementSystem.bean.Airport;
import com.nor.flightManagementSystem.bean.Flight;
import com.nor.flightManagementSystem.bean.Passenger;
import com.nor.flightManagementSystem.bean.Route;
import com.nor.flightManagementSystem.bean.Ticket;
import com.nor.flightManagementSystem.bean.TicketPassengerEmbed;
import com.nor.flightManagementSystem.dao.AirportDao;
import com.nor.flightManagementSystem.dao.FlightDao;
import com.nor.flightManagementSystem.dao.PassengerDao;
import com.nor.flightManagementSystem.dao.RouteDao;
import com.nor.flightManagementSystem.dao.TicketDao;

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

    @GetMapping("/searchFlight")
    public ModelAndView searchAllFlights() {
        List<Flight> flights = flightDao.showAllFlights();
        List<Airport> airports = airportDao.findAllAirports();
        ModelAndView mv = new ModelAndView("searchFlight");
        mv.addObject("airports", airports);
        mv.addObject("flights", flights);
        return mv;
    }
 
    @PostMapping("/searchFlight")
    public ModelAndView searchFlights(@RequestParam("sourceAirport") String sourceAirport, @RequestParam("destinationAirport") String destinationAirport) {
        String fromAirport = airportDao.findAirportCodeByLocation(sourceAirport);
        String toAirport = airportDao.findAirportCodeByLocation(destinationAirport);

        if (fromAirport == null || toAirport == null) {
            ModelAndView mv = new ModelAndView("errorPage");
            mv.addObject("message", "Invalid source or destination airport code.");
            return mv;
        }

        if (fromAirport.equalsIgnoreCase(toAirport)) {
            ModelAndView mv = new ModelAndView("errorPage");
            mv.addObject("message", "Source and destination airport codes cannot be the same.");
            return mv;
        }

        Route route = routeDao.findRouteBySourceAndDestination(fromAirport, toAirport);

        if (route == null) {
            ModelAndView mv = new ModelAndView("errorPage");
            mv.addObject("message", "No route found between the specified airports.");
            return mv;
        }

        List<Flight> flights = flightDao.findFlightsByRouteId(route.getRouteId());

        ModelAndView mv = new ModelAndView("searchedFlights");
        mv.addObject("flights", flights);
        mv.addObject("fromAirport", fromAirport);
        mv.addObject("toAirport", toAirport);
        mv.addObject("price", route.getPrice());
        return mv;
    }

    @GetMapping("/bookFlight")
    public ModelAndView showBookingPage(@RequestParam Long flightNumber, 
                                  @RequestParam String flightName, 
                                  @RequestParam Long routeId, 
                                  @RequestParam Double price) {
        ModelAndView mv = new ModelAndView("booking");
        mv.addObject("flightNumber", flightNumber);
        mv.addObject("flightName", flightName);
        mv.addObject("routeId", routeId);
        mv.addObject("price", price);
        return mv;
    }

    @PostMapping("/bookFlight")
    public ModelAndView bookFlight(@RequestParam("routeId") Long routeId,
                                   @RequestParam("flightNumber") Long flightNumber,
                                   @RequestParam("flightName") String flightName,
                                   @RequestParam("price") Double price,
                                   @RequestParam("passengerName") List<String> passengerNames,
                                   @RequestParam("passengerDob") List<String> passengerDobs) {

        if (passengerNames.isEmpty() || passengerNames.size() != passengerDobs.size()) {
            ModelAndView mv = new ModelAndView("errorPage");
            mv.addObject("message", "Passenger details are incomplete.");
            return mv;
        }

        Long ticketNumber = ticketDao.findLastTicketNumber();

        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setRouteId(routeId);
        ticket.setFlightNumber(flightNumber);
        ticket.setFlightName(flightName);
        double totalAmount = 0.0;
        int passengersCount = passengerNames.size();

        for (int i = 0; i < passengersCount; i++) {
            TicketPassengerEmbed ticketPassengerEmbed = new TicketPassengerEmbed();
            ticketPassengerEmbed.setTicketNumber(ticketNumber);
            ticketPassengerEmbed.setSerialNumber(i + 1);

            Passenger passenger = new Passenger();
            passenger.setEmbeddedId(ticketPassengerEmbed);
            passenger.setPassengerName(passengerNames.get(i));
            passenger.setPassengerDob(passengerDobs.get(i));

            double passengerPrice = price;

            // Apply discount based on age
            int age = calculateAge(passengerDobs.get(i));
            if (age > 60) {
                passengerPrice *= 0.7;
            } else if (age < 14) {
                passengerPrice *= 0.5;
            }

            passenger.setPrice(passengerPrice);
            totalAmount += passengerPrice;
            passengerDao.save(passenger);
        }

        ticket.setTotalAmount(totalAmount);
        ticketDao.save(ticket);

        // Update the seat count
        Flight flight = flightDao.viewFlight(flightNumber);
        int currentSeatsBooked = (flight.getSeatsBooked() != null) ? flight.getSeatsBooked() : 0;
        flight.setSeatsBooked(currentSeatsBooked + passengersCount);
        flightDao.addFlight(flight);

        ModelAndView mv = new ModelAndView("ticket");
        mv.addObject("ticket", ticket);
        mv.addObject("passengers", passengerDao.findByTicketNumber(ticketNumber));
        return mv;
    }

    private int calculateAge(String dob) {
        LocalDate birthDate = LocalDate.parse(dob);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    
 // This method shows the initial form page
    @GetMapping("/viewBooking")
    public ModelAndView showBookingForm() {
        return new ModelAndView("viewTicket");
    }

    // This method handles the form submission and shows the ticket details
    @PostMapping("/viewBooking")
    public ModelAndView viewBooking(@RequestParam("ticketNumber") Long ticketNumber) {
        Ticket ticket = ticketDao.findTicketByTicketNumber(ticketNumber);
        ModelAndView mv = new ModelAndView("viewTicket");
        mv.addObject("ticket", ticket);
        mv.addObject("passengers", passengerDao.findByTicketNumber(ticketNumber));
        return mv;
    }
    
    @PostMapping("/cancelBooking")
    public ModelAndView deleteAirport(@RequestParam("ticketNumber") Long ticketNumber) {
    	ticketDao.deleteByTicketNumber(ticketNumber);
    	return new ModelAndView("index"); 
    }
}
