package com.example.ReservationMicroservice.service;

import com.example.ReservationMicroservice.data.Reservation;
import com.example.ReservationMicroservice.data.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    //Create a reservation
    public Reservation createReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public String cancelReservation(Integer reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setStatus("CANCELLED");
            reservationRepository.save(reservation);
            return "Reservation cancelled successfully!";
        } else {
            return "Reservation not found!";
        }
    }

    public List<Reservation> getReservationsByPassenger(int passengerId){
        return reservationRepository.findByPassengerId(passengerId);
    }

    //check available seats
    public List<String> checkAvailableSeats(Integer busId, LocalDate travelDate) {
        List<String> reservedSeats = reservationRepository.findReservedSeatsByBusId(busId,travelDate);
        // bus have 40 seats, 1 - 40
        List<String> allSeats = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            allSeats.add(String.valueOf(i));
        }
        // removing reserved seats from all
        allSeats.removeAll(reservedSeats);

        return allSeats; // available seats
    }

    public Reservation getReservationById(int id){
        Optional<Reservation> reservation=reservationRepository.findById(id);
        return reservation.orElse(null);

    }

    public List<Reservation> getReservationsForBusOnDate(Integer busId, LocalDate travelDate) {
        return reservationRepository.findByBusIdAndTravelDate(busId, travelDate);
    }
}

