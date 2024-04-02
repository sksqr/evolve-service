package com.example.evolveservice.repo;

import com.example.evolveservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {

    Account findByClientId(String clientId);

    List<Account> findByClientNameStartingWith(String name);
}
