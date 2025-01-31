package com.example.NotificationMicroservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("SELECT n FROM Notification n WHERE n.passengerId = ?1")
    List<Notification> findByPassengerId(Integer passengerId);
}

