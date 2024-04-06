package com.example.evolveservice.repo;

import com.example.evolveservice.entity.EmailList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailListRepo extends JpaRepository<EmailList, Long> {
}
