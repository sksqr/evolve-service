package com.example.evolveservice.entity;

import com.example.evolveservice.repo.ContactRepo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private String name;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Column
    private Boolean attended;

}
/*
 Error executing DDL
 "create table attendee (id bigint not null auto_increment, attended bit, account_id bigint, `contact`_id` bigint, meeting_id bigint, primary key (id)) engine=InnoDB" via JDBC [You have an error in your SQL syntax; check the manual that corresponds to your
 MySQL server version for the right syntax to use near '_id` bigint, meeting_id bigint, primary key (id)) engine=InnoDB' at line 1]
 */
