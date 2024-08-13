package com.nor.flightManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nor.flightManagementSystem.bean.Route;
import org.springframework.stereotype.Service;

@Service
public class RouteDaoImpl implements RouteDao {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void save(Route route) {
        routeRepository.save(route);
    }

    @Override
    public List<Route> findAllRoutes() {
        return routeRepository.findAll();  // Corrected to return all routes from the repository
    }

    @Override
    public Optional<Route> findRouteById(Long id) {
        return routeRepository.findById(id);
    }

    @Override
    public Route findRouteBySourceAndDestination(String source, String destination) {
        return routeRepository.findRouteBySourceAndDestination(source, destination);
    }

    @Override
    public Long generateRouteId() {
        List<Route> routes = routeRepository.findTop1ByOrderByRouteIdDesc();
        return routes.isEmpty() ? 101L : routes.get(0).getRouteId() + 1;
    }

    @Override
    public void deleteRouteById(Long routeId) {
        routeRepository.deleteById(routeId);
    }

    @Override
    public void updateRoute(Route route) {
        routeRepository.save(route);
    }
}
