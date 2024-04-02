package com.example.evolveservice.repo;

import com.example.evolveservice.entity.Account;
import com.example.evolveservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
    Contact findByEmail(String email);
    List<Contact> findByNameStartingWith(String name);
}
