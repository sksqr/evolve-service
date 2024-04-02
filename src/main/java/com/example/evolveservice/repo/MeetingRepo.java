package com.example.evolveservice.repo;

import com.example.evolveservice.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepo extends JpaRepository<Meeting,Long> {
}
