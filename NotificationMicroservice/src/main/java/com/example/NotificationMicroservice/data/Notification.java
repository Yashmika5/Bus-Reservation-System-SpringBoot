package com.example.NotificationMicroservice.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notify_id;

    @Column(name="passenger_id", nullable = false)
    private Integer passengerId;

    @Column(name="message", nullable = false)
    private String message;

    @Column(name="type", nullable = false)
    private String type;

    @Column(name="timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "is_read")
    private Boolean isRead = false;

    // Default constructor (required by JPA)
    public Notification() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor with fields
    public Notification(Integer passengerId, String message, String type, LocalDateTime timestamp) {
        this.passengerId = passengerId;
        this.message = message;
        this.type = type;
        this.timestamp = timestamp;
        this.isRead = false;
    }

    // Getters and Setters
    public int getNotifyId() {
        return notify_id;
    }

    public void setNotifyId(int notifyId) {
        this.notify_id = notifyId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
}
