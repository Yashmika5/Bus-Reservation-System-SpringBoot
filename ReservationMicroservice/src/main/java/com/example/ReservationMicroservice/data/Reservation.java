package com.example.ReservationMicroservice.data;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @Column(name="passengerId")
    private Integer passengerId;

    @Column(name="busId")
    private Integer busId;

    @Column(name="seatNumber")
    private String seatNumber;

    @Column(name="travelDate")
    private LocalDate travelDate;

    @Column(name="boardingPoint")
    private String boardingPoint ;

    @Column(name="dropOffPoint")
    private String dropOffPoint ;

    @CreatedDate
    @Column(name="createdAt")
    private LocalDateTime createdAt ;

    @Column(name="status")
    private String status  ;

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }



    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public void setDropOffPoint(String dropOffPoint) {
        this.dropOffPoint = dropOffPoint;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    // created date, time saving
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public Integer getBusId() {
        return busId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }


    public String getBoardingPoint() {
        return boardingPoint;
    }

    public String getDropOffPoint() {
        return dropOffPoint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }
}
