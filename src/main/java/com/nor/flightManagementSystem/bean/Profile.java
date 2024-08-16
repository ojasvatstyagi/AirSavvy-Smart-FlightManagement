package com.nor.flightManagementSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profiles")
public class Profile {

    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    @Id
    @Size(min = 12, max = 12)
    private String aadhareNumber;
    @DBRef
    private FlightUser user; // Use FlightUser reference
    private byte[] photo;
    @Version
    private Long version;
}