package com.nor.flightManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlightManagementSystemApplication.class, args);
    }
}

 
//mysql -u root -p     
//ojas123@mysql
//use airlineDB;
//show tables;
//select * from airport;
//desc flight;