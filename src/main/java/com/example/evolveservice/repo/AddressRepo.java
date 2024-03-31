package com.example.evolveservice.repo;

import com.example.evolveservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
