package com.nor.flightManagementSystem.repository;

import com.nor.flightManagementSystem.bean.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {

}
