package com.nor.flightManagementSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Positive;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "flight")
public class Flight {

	@Id
	private Long flightNumber;
	private String flightName;
	@Positive
	private Long routeId;
	@Positive
	private Integer seatCapacity;
	private String departure;
	private String arrival;
	@Positive
	private Integer seatsBooked = 0;
	@Version
	private Long version;

	public Flight(Long newId, String flightName, Long routeId, Integer seatCapacity, String dtime, String atime) {
		this.flightNumber = newId;
		this.flightName = flightName;
		this.routeId = routeId;
		this.seatCapacity = seatCapacity;
		this.arrival = atime;
		this.departure = dtime;
	}
}
