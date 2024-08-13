package com.nor.flightManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import com.nor.flightManagementSystem.bean.Route;

public interface RouteDao {
	 void save(Route route);
	 List<Route> findAllRoutes();
	 Optional<Route> findRouteById(Long id);
	 Route findRouteBySourceAndDestination(String source, String destination);
	 Long generateRouteId();
	 void deleteRouteById(Long routeId);
	 void updateRoute(Route route);
}
