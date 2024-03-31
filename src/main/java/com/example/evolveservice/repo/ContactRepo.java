package com.example.evolveservice.repo;

import com.example.evolveservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

    Contact findByEmail(String email);
}
