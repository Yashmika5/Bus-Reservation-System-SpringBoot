package com.example.NotificationMicroservice.service;

import com.example.NotificationMicroservice.data.Notification;
import com.example.NotificationMicroservice.data.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    //get all Notifications
    public List<Notification> getAllNotifications(){
        return  notificationRepository.findAll();
    }

    //get Notifications by notify ID
    public Notification getNotificationById(int id){
        Optional<Notification> notification=notificationRepository.findById(id);
        return notification.orElse(null);
    }

    //get Notifications by passenger id
    public List<Notification> getNotificationsByPassengerId(int PassengerId){
        return notificationRepository.findByPassengerId(PassengerId);
    }

    //send notification
    public Notification sendNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    //mark as read
    public Notification markAsRead(int id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setIsRead(true);
            return notificationRepository.save(notification);
        }
        return null;
    }

    public String deleteNotification(int id){
        notificationRepository.deleteById(id);
        return "Notification Successfully Deleted!";
    }

}
