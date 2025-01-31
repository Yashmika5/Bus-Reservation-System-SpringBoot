package com.example.BusMicroservice.service;

import com.example.BusMicroservice.data.Bus;
import com.example.BusMicroservice.data.BusStatus;
import com.example.BusMicroservice.data.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;

    public Bus createBus(Bus bus){
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses(){
        return  busRepository.findAll();
    }

    public Bus getBusById(int id){
        Optional<Bus> bus=busRepository.findById(id);
        return bus.orElse(null);
    }

    public  Bus updateBus(int id, Bus bus){
        return busRepository.save(bus);
    }

    public String deleteBus(int id){
        busRepository.deleteById(id);
        return "Successfully Deleted!";
    }

    public Bus updateBusStatus(int busId, BusStatus newStatus) {
        Optional<Bus> optionalBus = busRepository.findById(busId);
        if (optionalBus.isPresent()) {
            Bus bus = optionalBus.get();
            bus.setStatus(newStatus);
            return busRepository.save(bus);
        } else {
            throw new RuntimeException("Bus with ID " + busId + " not found.");
        }
    }
}
