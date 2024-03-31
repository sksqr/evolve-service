package com.example.evolveservice.entity;

import com.example.evolveservice.repo.ContactRepo;
import jakarta.persistence.*;

public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Contact contact;

    @ManyToOne
    private Account account;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

}
