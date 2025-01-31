package com.example.PassengerMicroservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

        Optional<Passenger> findByEmail(String email);


}
