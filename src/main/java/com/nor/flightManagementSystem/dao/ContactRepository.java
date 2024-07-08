package com.nor.flightManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nor.flightManagementSystem.bean.Contact;


public interface ContactRepository extends JpaRepository<Contact, String>{

}
