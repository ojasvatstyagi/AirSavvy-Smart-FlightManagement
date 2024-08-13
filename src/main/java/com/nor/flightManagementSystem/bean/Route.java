package com.nor.flightManagementSystem.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "route")
public class Route {

	@Id
	private Long routeId;
	@Indexed
	@Size(min = 3, max = 3)
	private String sourceAirportCode;
	@Indexed
	@Size(min = 3, max = 3)
	private String destinationAirportCode;
	@Positive
	private Double price;
	@Version
	private Long version;
}
