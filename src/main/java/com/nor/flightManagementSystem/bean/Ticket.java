package com.nor.flightManagementSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ticket")
public class Ticket {
	@Id
	private Long ticketNumber;
	private Long routeId;
	private Long flightNumber;
	private String flightName;
	@Positive
	private Double totalAmount;
	private String username;
	@Version
	private Long version;
}
