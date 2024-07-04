package com.nor.flightManagementSystem.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "source_airport_code", nullable = false)
    private String sourceAirportCode;

    @Column(name = "destination_airport_code", nullable = false)
    private String destinationAirportCode;
    
    @Column(name = "price")
    private Double price;
	
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}
	public String getSourceAirportCode() {
		return sourceAirportCode;
	}
	public void setSourceAirportCode(String sourceAirportCode) {
		this.sourceAirportCode = sourceAirportCode;
	}
	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}
	public void setDestinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Route(Long routeId, String sourceAirportCode, String destinationAirportCode, Double price) {
		super();
		this.routeId = routeId;
		this.sourceAirportCode = sourceAirportCode;
		this.destinationAirportCode = destinationAirportCode;
		this.price = price;
	}
	public Route() {
		super();
	}
}
