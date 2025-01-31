# Highway Bus Reservation System

This is a microservices-based system for highway bus reservations. The system includes four microservices:

- **Passenger Microservice**
- **Bus Microservice**
- **Reservation Microservice**
- **Notification Microservice**

Each microservice has its own set of RESTful APIs for performing various operations such as creating passengers, managing buses, handling reservations, and sending notifications.

---

## Microservices Endpoints

### Passenger Microservice

#### Create a Passenger
- **Endpoint**: `POST http://localhost:8091/api/passengers`
- **Request Body**:
  ```json
  {
    "firstName": "Amal",
    "lastName": "Fernando",
    "email": "amal@gmail.com",
    "password": "1234Z",
    "phoneNumber": "07124235334",
    "gender": "Male",
    "address": "Galle",
    "role": "PASSENGER",
    "nic": "98938743878V"
  }
Login a Passenger
Endpoint: POST http://localhost:8091/api/passengers/login
Request Body:
json
Copy
Edit
{
  "email": "amal@gmail.com",
  "password": "1234Z"
}
Get All Passengers
Endpoint: GET http://localhost:8091/api/passengers
Get Passenger By ID
Endpoint: GET http://localhost:8091/api/passengers/1
Update a Passenger
Endpoint: PUT http://localhost:8091/api/passengers/1
Request Body:
json
Copy
Edit
{
  "userId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "secure123",
  "phoneNumber": "1234567890",
  "gender": "Male",
  "address": "Colombo",
  "role": "PASSENGER",
  "nic": null
}
Delete a Passenger
Endpoint: DELETE http://localhost:8091/api/passengers/1
Bus Microservice
Create a Bus
Endpoint: POST http://localhost:8092/api/buses/create
Request Body:
json
Copy
Edit
{
  "busNumber": "NB-2025",
  "seatingCapacity": 40,
  "operatorId": "OP123",
  "contactNumber": "0711234567",
  "source": "Colombo",
  "destination": "Kandy",
  "departureTime": "08:30:00",
  "arrivalTime": "12:30:00",
  "status": "AVAILABLE"
}
Get All Buses
Endpoint: GET http://localhost:8092/api/buses
Get Bus By ID
Endpoint: GET http://localhost:8092/api/buses/1
Update Bus Status
Endpoint: PUT http://localhost:8092/api/buses/1/status
Request Body:
json
Copy
Edit
{
  "status": "MAINTENANCE"
}
Delete a Bus
Endpoint: DELETE http://localhost:8091/api/buses/1
Reservation Microservice
Create a Reservation
Endpoint: POST http://localhost:8093/reservations
Request Body:
json
Copy
Edit
{
  "passengerId": 3,
  "busId": 5,
  "seatNumber": "A12",
  "travelDate": "2025-02-10",
  "boardingPoint": "Colombo",
  "dropOffPoint": "Kandy",
  "status": "CONFIRMED"
}
Cancel a Reservation
Endpoint: PUT http://localhost:8093/reservations/cancel/{reservationID}
Get Reservation By ID
Endpoint: GET http://localhost:8093/reservations/{reservationID}
Get Reservations By Passenger ID
Endpoint: GET http://localhost:8093/reservations/passenger/{passengerID}
Get Bus’s Available Seats
Endpoint: GET http://localhost:8093/reservations/bus/{busId}/seats
Get Bus’s Reservations for Specific Date
Endpoint: GET http://localhost:8093/reservations/bus/{busId}/date
Notification Microservice
Get All Notifications
Endpoint: GET http://localhost:8090/notifications
Post a Notification
Endpoint: POST http://localhost:8090/notifications/send
Request Body:
json
Copy
Edit
{
  "passengerId": 123,
  "message": "Your bus reservation has been confirmed.",
  "type": "SUCCESS",
  "timestamp": "2025-02-01T12:30:00",
  "isRead": false
}
Mark Notification as Read
Endpoint: PUT http://localhost:8090/notifications/2/read
Delete a Notification
Endpoint: DELETE http://localhost:8090/notifications/2
Setup
Clone this repository.
Make sure you have Docker, Spring Boot, Node.js, and MySQL installed on your machine.
Run each microservice on its respective port.
Use Postman or any API testing tool to interact with the APIs.
