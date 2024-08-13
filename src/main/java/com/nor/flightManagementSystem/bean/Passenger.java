package com.nor.flightManagementSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Past;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "passenger")
public class Passenger {

	private TicketPassengerEmbed embeddedId;
	private String passengerName;
	@Past
	private String passengerDob;
	private Double price;
	@Version
	private Long version;
}
