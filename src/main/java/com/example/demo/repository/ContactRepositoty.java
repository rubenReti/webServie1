package com.example.demo.repository;

import com.example.demo.entity.Campaign;
import com.example.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContactRepositoty extends JpaRepository<Contact, Integer> {



    @Query(value = "SELECT * FROM contact ct WHERE ct.email =  = ?1", nativeQuery = true)
    Optional<Contact> findByemail(String email);

}
