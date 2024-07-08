package com.nor.flightManagementSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flight {

    @Id
    private Long flightNumber;
    private String flightName;
    private Long routeId;
    private Integer seatCapacity;
    private String departure;
    private String arrival;
    private Integer seatsBooked = 0;

    public Flight() {
    	super();
    }


    public Flight(Long flightNumber, String flightName, Long routeId, Integer seatCapacity, String departure,
			String arrival) {
		super();
		this.flightNumber = flightNumber;
		this.flightName = flightName;
		this.routeId = routeId;
		this.seatCapacity = seatCapacity;
		this.departure = departure;
		this.arrival = arrival;
	}

    
    public Long getFlightNumber() {
    	return flightNumber;
    }

	public Long getRouteId() {
		return routeId;
	}


	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}


	public String getDeparture() {
		return departure;
	}


	public void setDeparture(String departure) {
		this.departure = departure;
	}


	public String getArrival() {
		return arrival;
	}


	public void setArrival(String arrival) {
		this.arrival = arrival;
	}


	public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    @Override
    public String toString() {
        return "Flight [flightNumber=" + flightNumber + ", flightName=" + flightName + ", seatCapacity=" + seatCapacity + "]";
    }


	public Integer getSeatsBooked() {
		return seatsBooked;
	}


	public void setSeatsBooked(Integer seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
}
