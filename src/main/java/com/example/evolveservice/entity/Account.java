package com.example.evolveservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String clientId;

    @Column(nullable = false, unique = true)
    private String clientName;

    @OneToOne
    private Contact contact;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated = OffsetDateTime.now();

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated = OffsetDateTime.now();

}
/*
ravi
 */
