package com.nor.flightManagementSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigInteger;

@Entity
public class Profile {
    private String fullName;
    private BigInteger phone;
    private String address;
    @Id
    private BigInteger adhareNumber;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private FlightUser flightUser;


    public Profile(String fullName, BigInteger phone, String address, BigInteger adhareNumber) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.adhareNumber = adhareNumber;
    }

    public Profile() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public BigInteger getAdhareNumber() {
        return adhareNumber;
    }

    public void setAdhareNumber(BigInteger adhareNumber) {
        this.adhareNumber = adhareNumber;
    }
}
