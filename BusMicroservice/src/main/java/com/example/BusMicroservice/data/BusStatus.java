package com.example.BusMicroservice.data;

public enum BusStatus {
    AVAILABLE,      // Bus is available for booking
    ON_TRIP,        // Bus is currently on a trip
    MAINTENANCE,    // Bus is under maintenance
    OUT_OF_SERVICE  // Bus is not in operation
}
