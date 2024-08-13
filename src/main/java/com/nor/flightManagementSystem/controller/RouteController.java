package com.nor.flightManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import com.nor.flightManagementSystem.exception.DatabaseException;
import com.nor.flightManagementSystem.exception.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.nor.flightManagementSystem.bean.Airport;
import com.nor.flightManagementSystem.bean.Route;
import com.nor.flightManagementSystem.repository.AirportDao;
import com.nor.flightManagementSystem.repository.RouteDao;
import com.nor.flightManagementSystem.service.RouteService;

@Controller
public class RouteController {

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private RouteService routeService;

    @Autowired
    private AirportDao airportDao;

    @GetMapping("/route")
    public ModelAndView showRouteEntryPage() {
        try {
            Long newRouteId = routeDao.generateRouteId();
            List<Airport> airports = airportDao.findAllAirports();
            Route route = new Route();
            route.setRouteId(newRouteId);

            return new ModelAndView("addRoute")
                    .addObject("routeRecord", route)
                    .addObject("codeList", airports);
        } catch (Exception e) {
            throw new DatabaseException("Problem generating new route ID or fetching airports", e);
        }
    }

    @PostMapping("/route")
    public ModelAndView saveRoute(@ModelAttribute("routeRecord") Route route) {
        try {
            Route returnRoute = routeService.createReturnRoute(route);
            routeDao.save(route);
            routeDao.save(returnRoute);

            return new ModelAndView("redirect:/route?message=Route details added successfully");
        } catch (Exception e) {
            throw new DatabaseException("Problem saving route", e);
        }
    }

    @GetMapping("/viewRoutes")
    public ModelAndView showAllRoutes() {
        try {
            List<Route> routes = routeDao.findAllRoutes();

            return new ModelAndView("viewRoutesPage")
                    .addObject("routes", routes);
        } catch (Exception e) {
            throw new DatabaseException("Problem fetching routes", e);
        }
    }

    @GetMapping("/modifyRoute")
    public ModelAndView showModifyRoutePage() {
        try {
            List<Airport> airports = airportDao.findAllAirports();
            List<Route> routes = routeDao.findAllRoutes();

            return new ModelAndView("modifyRoute")
                    .addObject("routes", routes)
                    .addObject("airports", airports);
        } catch (Exception e) {
            throw new DatabaseException("Problem retrieving routes or airports from the database", e);
        }
    }

    @PostMapping("/deleteRoute")
    public ModelAndView deleteRoute(@RequestParam("routeId") Long routeId) {
        try {
            Optional<Route> route = routeDao.findRouteById(routeId);
            if (route.isEmpty()) {
                throw new RouteNotFoundException("Route with ID " + routeId + " not found.");
            }
            routeDao.deleteRouteById(routeId);

            return new ModelAndView("redirect:/modifyRoute?message=Route deleted successfully");
        } catch (RouteNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Problem deleting route from the database", e);
        }
    }

    @PostMapping("/updateRoute")
    public ModelAndView updateRoute(@RequestParam Long routeId,
                                    @RequestParam String destinationAirportCode,
                                    @RequestParam String sourceAirportCode,
                                    @RequestParam Double price) {
        try {
            Optional<Route> routeOpt = routeDao.findRouteById(routeId);
            if (routeOpt.isEmpty()) {
                throw new RouteNotFoundException("Route with ID " + routeId + " not found.");
            }
            Route route = routeOpt.get();
            route.setSourceAirportCode(sourceAirportCode);
            route.setDestinationAirportCode(destinationAirportCode);
            route.setPrice(price);
            routeDao.updateRoute(route);

            return new ModelAndView("redirect:/modifyRoute?message=Route updated successfully");
        } catch (RouteNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new DatabaseException("Problem updating route in the database", e);
        }
    }

    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleDatabaseException(DatabaseException e) {
        return new ModelAndView("errorPage")
                .addObject("message", e.getMessage());
    }

    @ExceptionHandler(RouteNotFoundException.class)
    public ModelAndView handleRouteNotFoundException(RouteNotFoundException e) {
        return new ModelAndView("viewRoutesPage")
                .addObject("error", e.getMessage());
    }
}
