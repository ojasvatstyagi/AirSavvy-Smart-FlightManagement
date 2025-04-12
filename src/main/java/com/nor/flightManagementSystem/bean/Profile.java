package com.nor.flightManagementSystem.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

    @Field("aadhareNumber")
    private Long aadhareNumber;

    @DBRef
    private FlightUser user; // Reference to the associated FlightUser

    private byte[] photo;

    @Version
    private Long version;
}