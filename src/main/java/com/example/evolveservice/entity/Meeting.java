package com.example.evolveservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "meeting", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Attendee> attendees;

    @ManyToOne
    @JoinColumn(name = "analyst_id")
    private AppUser analyst;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated = OffsetDateTime.now();


}
