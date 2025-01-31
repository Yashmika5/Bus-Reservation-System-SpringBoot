package com.example.PassengerMicroservice.service;

import com.example.PassengerMicroservice.data.Passenger;
import com.example.PassengerMicroservice.data.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
   private PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers(){
        return  passengerRepository.findAll();
    }

    public Passenger getPassengerById(int id){
        Optional<Passenger> product=passengerRepository.findById(id);
        return product.orElse(null);
    }

    public Optional<Passenger> getPassengerByEmail(String email){
        return passengerRepository.findByEmail(email);
    }

    public Passenger createPassenger(Passenger passenger){
        return passengerRepository.save(passenger);
    }

    public  Passenger updatePassenger(int id, Passenger passenger){
        return passengerRepository.save(passenger);
    }

    public String deletePassenger(int id){
        passengerRepository.deleteById(id);
        return "Successfully Deleted!";
    }

    public boolean authenticate(String email, String password) {
        Optional<Passenger> passenger = passengerRepository.findByEmail(email);
        return passenger.isPresent() && passenger.get().getPassword().equals(password);
    }
}