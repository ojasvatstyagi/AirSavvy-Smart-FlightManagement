package com.nor.flightManagementSystem.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    public double calculatePassengerPrice(double basePrice, String dob) {
        int age = calculateAge(dob);
        double discountedPrice;

        if (age > 60) {
            discountedPrice = basePrice * 0.7;
        } else if (age < 16) {
            discountedPrice = basePrice * 0.5;
        } else {
            discountedPrice = basePrice;
        }

        return roundToTwoDecimalPlaces(discountedPrice);
    }

    private int calculateAge(String dob) {
        LocalDate birthDate = LocalDate.parse(dob);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
