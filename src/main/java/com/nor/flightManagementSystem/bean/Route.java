package com.nor.flightManagementSystem.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Route {

    @Id
    private Long routeId;
    @Column(nullable = false)
    private String sourceAirportCode;
    @Column(nullable = false)
    private String destinationAirportCode;
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
