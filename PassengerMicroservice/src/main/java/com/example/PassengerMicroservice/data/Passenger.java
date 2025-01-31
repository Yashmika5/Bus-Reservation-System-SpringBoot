package com.example.PassengerMicroservice.data;

import jakarta.persistence.*;

@Entity
@Table(name="User")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="gender")
    private String gender;

    @Column(name="address")
    private String address;

    @Column(name="NIC", nullable = false)
    private String NIC;

    @Column(name="role")
    private String role;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getNIC() {
        return NIC;
    }

    public String getRole() {
        return role;
    }
}
