package com.example.BusMicroservice.controller;


import com.example.BusMicroservice.data.Bus;
import com.example.BusMicroservice.data.BusStatus;
import com.example.BusMicroservice.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/buses")
public class BusController {
    @Autowired
    private BusService busService;


    //create bus
    @PostMapping("/create")
    public Bus createBus(@RequestBody Bus bus) {
        return busService.createBus(bus);
    }

    //get all buses
    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }
    //get Bus by id
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable int id) {
        Bus bus = busService.getBusById(id);
        return (bus != null) ? ResponseEntity.ok(bus) : ResponseEntity.notFound().build();
    }

    //deleting bus status
    @DeleteMapping(path="/{id}")
    public String deleteBus(@PathVariable int id){
        return busService.deleteBus(id);
    }
    // update bus status
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateBusStatus(@PathVariable int id, @RequestBody Map<String, String> request) {
        try {
            String statusStr = request.get("status");
            BusStatus newStatus = BusStatus.valueOf(statusStr.toUpperCase());
            Bus updatedBus = busService.updateBusStatus(id, newStatus);
            return ResponseEntity.ok(updatedBus);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status. Allowed values: AVAILABLE, MAINTENANCE, OUT_OF_SERVICE.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
