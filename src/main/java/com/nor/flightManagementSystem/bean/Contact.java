package com.nor.flightManagementSystem.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contact")
public class Contact {

    @Id
    @NotBlank
    private String name;
    @Email
    private String email;
    private String comment;
    @Version
    private Long version;
}