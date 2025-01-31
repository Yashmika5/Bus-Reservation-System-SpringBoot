package com.example.PassengerMicroservice.controller;

import com.example.PassengerMicroservice.data.Passenger;
import com.example.PassengerMicroservice.dto.LoginRequest;
import com.example.PassengerMicroservice.service.PassengerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/passengers")
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*", allowCredentials = "true")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    //List all passengers
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        List<Passenger> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    //ger psg by id
    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable int id) {
        Passenger passenger = passengerService.getPassengerById(id);

        if (passenger != null) {
            return ResponseEntity.ok(passenger);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 with no body
        }
    }

    //create new passenger
    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {
        System.out.println("Received Passenger: " + passenger); // Debugging
        Passenger savedPassenger = passengerService.createPassenger(passenger);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPassenger);
    }

    //update passenger details
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassenger(@PathVariable int id, @RequestBody Passenger passenger) {
        try {
            Passenger updatedPassenger = passengerService.updatePassenger(id, passenger);
            return ResponseEntity.ok(updatedPassenger);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error updating: Passenger not found with ID " + id);
        }
    }

    //delete passenger
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable int id) {
        try {
            return ResponseEntity.ok(passengerService.deletePassenger(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Passenger not found with ID: " + id);
        }
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Passenger> passenger = passengerService.getPassengerByEmail(loginRequest.getEmail());

        if (passenger.isPresent() && passengerService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(passenger.get().getEmail()) // Use email as the subject
                    .claim("userId", passenger.get().getUserId()) // Add custom claims
                    .setIssuedAt(new Date()) // Set the token's issue date
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                    .signWith(SignatureAlgorithm.HS256, "your_secret_key") // Sign the token with a secret key
                    .compact(); // Generate the token as a compact string

            // Return token and user details
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("user", passenger.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
        }
    }
}
