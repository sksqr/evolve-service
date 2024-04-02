package com.example.evolveservice.repo;

import com.example.evolveservice.entity.Attendee;
import com.example.evolveservice.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AttendeeRepo extends JpaRepository<Attendee,Long> {
    List<Attendee> findByNameStartingWithAndMeeting(String name, Meeting meeting);
}
