package com.nor.flightManagementSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "airport")
public class Airport {
    @Size(min = 3, max = 3)
    private String airportCode;
    private String airportLocation;
    private String details;
    @Version
    private Long version;
}
