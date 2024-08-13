package com.nor.flightManagementSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Version;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketPassengerEmbed implements Serializable {
	@NotNull
	private Long ticketNumber;
	@NotNull
	private Integer serialNumber;
	@Version
	private Long version;

	public TicketPassengerEmbed(Long ticketNumber,Integer serialNumber) {
		super();
		this.ticketNumber = ticketNumber;
		this.serialNumber = serialNumber;
	}
}
