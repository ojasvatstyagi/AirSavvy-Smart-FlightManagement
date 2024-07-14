package com.nor.flightManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nor.flightManagementSystem.bean.Airport;
import com.nor.flightManagementSystem.bean.Contact;
import com.nor.flightManagementSystem.dao.AirportDao;
import com.nor.flightManagementSystem.dao.ContactRepository;

@ControllerAdvice
@RestController
public class AirportController {

    @Autowired
    private AirportDao airportDao;
    
    @Autowired
    private ContactRepository repo;
    
    @GetMapping("/index")
    public ModelAndView showIndexPage() {
        return new ModelAndView("index");
    }

    @GetMapping("/addAirport")
    public ModelAndView showAddAirportPage() {
        ModelAndView mv = new ModelAndView("addAirport");
        mv.addObject("airportDetails", new Airport());
        return mv;
    }

    @PostMapping("/addAirport")
    public ModelAndView saveAirport(@ModelAttribute("airportDetails") Airport airport) {
        airport.setAirportCode(airport.getAirportCode().toUpperCase());
        airport.setAirportLocation(airport.getAirportLocation().toUpperCase());
        airport.setDetails(airport.getDetails());
        airportDao.addAirport(airport);
        return new ModelAndView("index");
    }

    @GetMapping("/viewAirports")
    public ModelAndView showAirportReportPage() {
        List<Airport> airportList = airportDao.findAllAirports();
        ModelAndView mv = new ModelAndView("viewAirportPage");
        mv.addObject("airportList", airportList);
        return mv;
    }
    
    @GetMapping("/viewAirports/{id}")
    public ModelAndView showSingleAirportPage(@PathVariable("id") String id) {
    	Airport airport = airportDao.findAirportById(id);
    	ModelAndView mv = new ModelAndView("checkSingleAirport");
        mv.addObject("airport", airport);
        return mv;
    }

    @GetMapping("/viewAirportCode")
    public ModelAndView showAirportSelectPage() {
        List<String> codeList = airportDao.findAllAirportCodes();
        ModelAndView mv = new ModelAndView("checkSingleAirport");
        mv.addObject("codeList", codeList);
        return mv;
    }


    @GetMapping("/modifyAirport")
    public ModelAndView deleteAirport() {
        List<Airport> airports = airportDao.findAllAirports();
        ModelAndView mv = new ModelAndView("modifyAirport");
        mv.addObject("airports", airports);
        return mv;
    }
    
     
    @PostMapping("/deleteAirport")
    public ModelAndView deleteAirport(@RequestParam("airportCode") String airportCode) {
    	airportDao.deleteAirportByCode(airportCode);
        return new ModelAndView("index"); 
    }
    
    @PostMapping("/updateAirport")
    public ModelAndView updateFlight(@RequestParam("airportCode") String airportCode,
                                     @RequestParam("airportLocation") String airportLocation,
                                     @RequestParam("details") String details) {
        Airport airport = new Airport(airportCode, airportLocation, details);
        airportDao.updateAirport(airport);
        return new ModelAndView("index");
    }

    @GetMapping("/about")
    public ModelAndView showAboutPage() {
        ModelAndView mv = new ModelAndView("about");
        mv.addObject("contacts", new Contact());
        return mv;
    }
    
    @PostMapping("/about")
    public ModelAndView saveContactPage(@ModelAttribute("contacts") Contact contact) {
        repo.save(contact);
        return new ModelAndView("about");
    }
}
