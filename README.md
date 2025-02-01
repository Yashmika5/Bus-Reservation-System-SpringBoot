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

#### Login user
- **Endpoint**: `POST http://localhost:8091/api/passengers/login`
- **Request Body**:
  ```json
  {
  "email": "amal@gmail.com",
  "password": "1234Z"
  }


#### Get all passengers
- **Endpoint**: `GET http://localhost:8091/api/passengers`

#### Get Passenger By ID
- **Endpoint**: `GET http://localhost:8091/api/passengers/1`

#### Update a passenger
- **Endpoint**: `PUT http://localhost:8091/api/passengers/1`
- **Request Body**:
  ```json
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

#### Delete passenger By ID
- **Endpoint**: `DELETE http://localhost:8091/api/passengers/1`


  
### Bus Microservice


  #### Create a Bus
- **Endpoint**: `POST http://localhost:8092/api/buses/create`
- **Request Body**:
  ```json
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

#### Get all buses
- **Endpoint**: `GET http://localhost:8092/api/buses`

