package com.example.ReservationMicroservice.controller;

import com.example.ReservationMicroservice.data.Reservation;
import com.example.ReservationMicroservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    //Creating a reserve
    @PostMapping(path="/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation){
        return  reservationService.createReservation(reservation);
    }

    @PutMapping("/reservations/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Integer id) {
        try {
            reservationService.cancelReservation(id);
            return ResponseEntity.ok("Reservation with ID " + id + " has been cancelled.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //get by reservation id
    @GetMapping("/reservations/{id}")
    public Reservation getReservationById(@PathVariable Integer id) {
        return reservationService.getReservationById(id);
    }
    //get by passenger id
    @GetMapping(path="/reservations/passenger/{passengerId}")
    public List<Reservation> getReservationsByPassenger(@PathVariable Integer passengerId){
        return reservationService.getReservationsByPassenger(passengerId);
    }
    // bus's available seats
    @GetMapping("/reservations/bus/{busId}/seats")
    public List<String> checkSeatAvailability(@PathVariable Integer busId,  @RequestParam LocalDate travelDate) {
        return reservationService.checkAvailableSeats(busId, travelDate);
    }
    //get reservations for a specific bus on specific date
    @GetMapping("/reservations/bus/{busId}/date")
    public List<Reservation> getReservationsForBusOnDate(
            @PathVariable Integer busId,
            @RequestParam("travelDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate travelDate) {
        return reservationService.getReservationsForBusOnDate(busId, travelDate);
    }
}
