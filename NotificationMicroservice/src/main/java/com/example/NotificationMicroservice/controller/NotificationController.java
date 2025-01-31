package com.example.NotificationMicroservice.controller;

import com.example.NotificationMicroservice.data.Notification;
import com.example.NotificationMicroservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
    @GetMapping("/passenger/{id}")
    public List<Notification> getNotificationsByPassengerId(@PathVariable int id) {
        return notificationService.getNotificationsByPassengerId(id);
    }

    @PostMapping("/send")
    public Notification sendNotification(@RequestBody Notification notification) {
        return notificationService.sendNotification(notification);
    }

    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable int id) {
        return notificationService.markAsRead(id);
    }

    @DeleteMapping(path="/{id}")
    public String deleteNotification(@PathVariable int id){
        return notificationService.deleteNotification(id);
    }
}
