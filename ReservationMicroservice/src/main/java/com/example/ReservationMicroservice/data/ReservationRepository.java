package com.example.ReservationMicroservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    Optional<Reservation> findById(Integer reservationId);

    @Query("SELECT r FROM Reservation r WHERE r.passengerId = ?1")
    List<Reservation> findByPassengerId(int passengerId);

    @Query("SELECT r.seatNumber FROM Reservation r WHERE r.busId = ?1 AND r.travelDate = ?2 AND r.status != 'CANCELLED'")
    List<String> findReservedSeatsByBusId(Integer busId, LocalDate travelDate);

    @Query("SELECT r FROM Reservation r WHERE r.busId = ?1 AND r.travelDate = ?2")
    List<Reservation> findByBusIdAndTravelDate(Integer busId, LocalDate travelDate);
}
