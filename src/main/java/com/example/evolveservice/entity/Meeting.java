package com.example.evolveservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "meeting" )
    private List<Attendee> attendees;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated = OffsetDateTime.now();


}
