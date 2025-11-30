# 🚌 Bus Reservation System - Spring Boot Microservices

A comprehensive, enterprise-grade bus reservation system built using Spring Boot microservices architecture. This system enables passengers to search for buses, make reservations, manage bookings, and receive notifications—all through a distributed, scalable backend infrastructure.

## 📋 Table of Contents

- [Overview](#overview)
- [System Architecture](#system-architecture)
- [Microservices](#microservices)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Database Configuration](#database-configuration)
- [API Endpoints](#api-endpoints)
- [Data Models](#data-models)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

The Bus Reservation System is a modern, microservices-based application designed to handle bus booking operations efficiently. Built with Spring Boot 3.4.2 and Java 17, the system demonstrates best practices in distributed systems development, including service isolation, RESTful API design, and database-per-service architecture.

### Key Highlights

- **Microservices Architecture**: Four independent, loosely-coupled services
- **RESTful APIs**: Clean, well-documented REST endpoints
- **JWT Authentication**: Secure user authentication and authorization
- **MySQL Databases**: Separate database for each microservice
- **Scalable Design**: Easy to scale individual services based on demand
- **Real-time Notifications**: Keep passengers informed about their bookings

---

## 🏗️ System Architecture

The application follows a **microservices architecture** pattern with the following components:

```
┌─────────────────────────────────────────────────────────────┐
│                        API Gateway                           │
│                   (Future Implementation)                    │
└─────────────────────────────────────────────────────────────┘
                              │
        ┌─────────────────────┼─────────────────────┐
        │                     │                     │
   ┌────▼────┐          ┌────▼────┐          ┌─────▼─────┐
   │  Bus    │          │Passenger│          │Reservation│
   │ Service │          │ Service │          │  Service  │
   │ :8092   │          │ :8091   │          │  :8093    │
   └────┬────┘          └────┬────┘          └─────┬─────┘
        │                    │                      │
        │                    └──────────┬───────────┘
        │                               │
   ┌────▼───────────────────────────────▼─────┐
   │     Notification Microservice            │
   │              :8090                        │
   └──────────────────────────────────────────┘
```

---

## 🔧 Microservices

### 1. **Passenger Microservice** (Port: 8091)
Manages passenger/user information, authentication, and authorization.

**Responsibilities:**
- User registration and profile management
- Authentication using JWT tokens
- Password management
- Role-based access control (RBAC)

**Database:** `passenger`

---

### 2. **Bus Microservice** (Port: 8092)
Handles bus information and availability management.

**Responsibilities:**
- Bus creation and management
- Bus schedule management
- Bus status tracking (AVAILABLE, MAINTENANCE, OUT_OF_SERVICE)
- Route management (source, destination, timing)
- Capacity tracking

**Database:** `Buses`

---

### 3. **Reservation Microservice** (Port: 8093)
Core booking engine that handles all reservation operations.

**Responsibilities:**
- Creating new reservations
- Seat availability checking
- Reservation cancellation
- Booking history retrieval
- Seat assignment management

**Database:** `Reservations`

---

### 4. **Notification Microservice** (Port: 8090)
Manages all communication with passengers.

**Responsibilities:**
- Sending booking confirmations
- Cancellation notifications
- Real-time alerts
- Notification history
- Read/unread status tracking

**Database:** `notifications`

---

## ✨ Features

### Passenger Features
- ✅ User registration and login
- ✅ JWT-based secure authentication
- ✅ Profile management (update/delete)
- ✅ View booking history
- ✅ Receive notifications

### Bus Management
- ✅ Add new buses to the system
- ✅ Update bus information
- ✅ Set bus status (Available/Maintenance/Out of Service)
- ✅ Configure routes and schedules
- ✅ Track seating capacity

### Reservation Features
- ✅ Search available buses
- ✅ Check seat availability
- ✅ Make new reservations
- ✅ Select specific seats
- ✅ Cancel bookings
- ✅ View reservation details
- ✅ Filter reservations by date/passenger/bus

### Notification System
- ✅ Automated booking confirmations
- ✅ Cancellation alerts
- ✅ Mark notifications as read
- ✅ Retrieve notification history
- ✅ Delete old notifications

---

## 🛠️ Technology Stack

### Backend Framework
- **Spring Boot**: 3.4.2
- **Java**: 17

### Dependencies
- **Spring Web**: RESTful web services
- **Spring Data JPA**: ORM and database operations
- **MySQL Connector**: Database connectivity
- **JWT (JSON Web Tokens)**: Authentication (jjwt 0.9.1)
- **Lombok**: Reduce boilerplate code
- **Jakarta Persistence API**: JPA implementation
- **Maven**: Build and dependency management

### Database
- **MySQL**: 8.x (running on port 3308)

### Tools & Utilities
- **Hibernate**: ORM with auto DDL generation
- **JAXB API**: XML binding support for JWT

---

## 📦 Prerequisites

Before running the application, ensure you have the following installed:

1. **Java Development Kit (JDK) 17+**
   ```bash
   java -version
   ```

2. **Apache Maven 3.8+**
   ```bash
   mvn -version
   ```

3. **MySQL 8.x**
   ```bash
   mysql --version
   ```

4. **Git** (for cloning the repository)
   ```bash
   git --version
   ```

5. **IDE** (Optional but recommended)
   - IntelliJ IDEA
   - Eclipse
   - VS Code with Java extensions

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd Bus-Reservation-System-SpringBoot-main
```

### 2. Configure MySQL Database

Start MySQL server and ensure it's running on port **3308** (or update the configuration accordingly).

```bash
# Login to MySQL
mysql -u root -p -P 3308

# The databases will be auto-created by Spring Boot
# But you can verify the connection
SHOW DATABASES;
```

### 3. Update Database Credentials

Update the database credentials in each microservice's `application.yml` file:

**Location:** `{MicroserviceName}/src/main/resources/application.yml`

```yaml
spring:
  datasource:
    username: your_username
    password: your_password
```

**⚠️ Security Note:** For production, use environment variables or Spring Cloud Config instead of hardcoding credentials.

### 4. Build All Microservices

Navigate to each microservice directory and build:

```bash
# Build Bus Microservice
cd BusMicroservice
mvn clean install

# Build Passenger Microservice
cd ../PassengerMicroservice
mvn clean install

# Build Reservation Microservice
cd ../ReservationMicroservice
mvn clean install

# Build Notification Microservice
cd ../NotificationMicroservice
mvn clean install
```

Or build all at once from the root directory:

```bash
# If using a parent POM (future implementation)
mvn clean install
```

---

## 🗄️ Database Configuration

Each microservice has its own dedicated MySQL database:

### Passenger Microservice
```yaml
Database Name: passenger
Port: 3308
Tables: User
```

### Bus Microservice
```yaml
Database Name: Buses
Port: 3308
Tables: buses
```

### Reservation Microservice
```yaml
Database Name: Reservations
Port: 3308
Tables: Reservations
```

### Notification Microservice
```yaml
Database Name: notifications
Port: 3308
Tables: notifications
```

### Common JPA Settings (All Microservices)
```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update  # Automatically updates schema
    show-sql: true      # Shows SQL queries in console
```

---

## 📡 API Endpoints

### 🧑 Passenger Microservice (Port: 8091)

#### Authentication & User Management

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/passengers/login` | User login | `LoginRequest` |
| POST | `/api/passengers` | Register new passenger | `Passenger` |
| GET | `/api/passengers` | Get all passengers | - |
| GET | `/api/passengers/{id}` | Get passenger by ID | - |
| PUT | `/api/passengers/{id}` | Update passenger details | `Passenger` |
| DELETE | `/api/passengers/{id}` | Delete passenger | - |

**Sample Login Request:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Sample Registration Request:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "securePassword",
  "phoneNumber": "+1234567890",
  "gender": "Male",
  "address": "123 Main St, City",
  "NIC": "123456789V",
  "role": "PASSENGER"
}
```

---

### 🚌 Bus Microservice (Port: 8092)

#### Bus Management

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/api/buses/create` | Create new bus | `Bus` |
| GET | `/api/buses` | Get all buses | - |
| GET | `/api/buses/{id}` | Get bus by ID | - |
| PUT | `/api/buses/{id}/status` | Update bus status | `{"status": "AVAILABLE"}` |
| DELETE | `/api/buses/{id}` | Delete bus | - |

**Sample Bus Creation Request:**
```json
{
  "busNumber": "BUS-101",
  "seatingCapacity": 40,
  "operatorId": "OP-001",
  "contactNumber": "+1234567890",
  "source": "New York",
  "destination": "Boston",
  "departureTime": "08:00:00",
  "arrivalTime": "12:00:00",
  "status": "AVAILABLE"
}
```

**Bus Status Values:**
- `AVAILABLE`
- `MAINTENANCE`
- `OUT_OF_SERVICE`

---

### 🎫 Reservation Microservice (Port: 8093)

#### Booking Management

| Method | Endpoint | Description | Query Params |
|--------|----------|-------------|--------------|
| POST | `/reservations` | Create new reservation | `Reservation` |
| GET | `/reservations/{id}` | Get reservation by ID | - |
| GET | `/reservations/passenger/{passengerId}` | Get passenger's reservations | - |
| GET | `/reservations/bus/{busId}/seats` | Check seat availability | `travelDate` |
| GET | `/reservations/bus/{busId}/date` | Get bus reservations by date | `travelDate` |
| PUT | `/reservations/cancel/{id}` | Cancel reservation | - |

**Sample Reservation Request:**
```json
{
  "passengerId": 1,
  "busId": 5,
  "seatNumber": "A12",
  "travelDate": "2025-12-15",
  "boardingPoint": "New York Terminal",
  "dropOffPoint": "Boston Station",
  "status": "CONFIRMED"
}
```

**Query Seat Availability:**
```
GET /reservations/bus/5/seats?travelDate=2025-12-15
```

---

### 🔔 Notification Microservice (Port: 8090)

#### Notification Management

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/notifications` | Get all notifications | - |
| GET | `/notifications/passenger/{id}` | Get passenger notifications | - |
| POST | `/notifications/send` | Send new notification | `Notification` |
| PUT | `/notifications/{id}/read` | Mark as read | - |
| DELETE | `/notifications/{id}` | Delete notification | - |

**Sample Notification Request:**
```json
{
  "passengerId": 1,
  "message": "Your booking for Bus BUS-101 on 2025-12-15 is confirmed!",
  "type": "BOOKING_CONFIRMATION",
  "timestamp": "2025-11-30T10:30:00"
}
```

---

## 📊 Data Models

### Passenger Entity
```java
{
  "userId": Integer (Primary Key, Auto-generated),
  "firstName": String,
  "lastName": String,
  "email": String (Unique),
  "password": String (Encrypted),
  "phoneNumber": String,
  "gender": String,
  "address": String,
  "NIC": String (National ID),
  "role": String (PASSENGER/ADMIN)
}
```

### Bus Entity
```java
{
  "busId": Integer (Primary Key, Auto-generated),
  "busNumber": String,
  "seatingCapacity": Integer,
  "operatorId": String,
  "contactNumber": String,
  "source": String,
  "destination": String,
  "departureTime": Time,
  "arrivalTime": Time,
  "status": Enum (AVAILABLE, MAINTENANCE, OUT_OF_SERVICE),
  "createdAt": LocalDateTime,
  "updatedAt": LocalDateTime
}
```

### Reservation Entity
```java
{
  "reservationId": Integer (Primary Key, Auto-generated),
  "passengerId": Integer (Foreign Key),
  "busId": Integer (Foreign Key),
  "seatNumber": String,
  "travelDate": LocalDate,
  "boardingPoint": String,
  "dropOffPoint": String,
  "createdAt": LocalDateTime,
  "status": String (CONFIRMED, CANCELLED)
}
```

### Notification Entity
```java
{
  "notifyId": Integer (Primary Key, Auto-generated),
  "passengerId": Integer (Foreign Key),
  "message": String,
  "type": String,
  "timestamp": LocalDateTime,
  "isRead": Boolean (Default: false)
}
```

---

## 🏃 Running the Application

### Option 1: Run Each Microservice Individually

Open separate terminal windows for each service:

**Terminal 1 - Notification Service:**
```bash
cd NotificationMicroservice
mvn spring-boot:run
# Service will start on http://localhost:8090
```

**Terminal 2 - Passenger Service:**
```bash
cd PassengerMicroservice
mvn spring-boot:run
# Service will start on http://localhost:8091
```

**Terminal 3 - Bus Service:**
```bash
cd BusMicroservice
mvn spring-boot:run
# Service will start on http://localhost:8092
```

**Terminal 4 - Reservation Service:**
```bash
cd ReservationMicroservice
mvn spring-boot:run
# Service will start on http://localhost:8093
```

### Option 2: Run Using JAR Files

First, package each service:
```bash
cd {MicroserviceName}
mvn clean package
```

Then run the JAR files:
```bash
java -jar NotificationMicroservice/target/NotificationMicroservice-0.0.1-SNAPSHOT.jar
java -jar PassengerMicroservice/target/PassengerMicroservice-0.0.1-SNAPSHOT.jar
java -jar BusMicroservice/target/BusMicroservice-0.0.1-SNAPSHOT.jar
java -jar ReservationMicroservice/target/ReservationMicroservice-0.0.1-SNAPSHOT.jar
```

### Verify Services are Running

Check each service health:
```bash
# Notification Service
curl http://localhost:8090/notifications

# Passenger Service
curl http://localhost:8091/api/passengers

# Bus Service
curl http://localhost:8092/api/buses

# Reservation Service
curl http://localhost:8093/reservations
```

---

## 🧪 Testing

### Using cURL

**1. Register a New Passenger:**
```bash
curl -X POST http://localhost:8091/api/passengers \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Alice",
    "lastName": "Smith",
    "email": "alice@example.com",
    "password": "password123",
    "phoneNumber": "+1234567890",
    "gender": "Female",
    "address": "456 Oak St",
    "NIC": "987654321V",
    "role": "PASSENGER"
  }'
```

**2. Login:**
```bash
curl -X POST http://localhost:8091/api/passengers/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "alice@example.com",
    "password": "password123"
  }'
```

**3. Create a Bus:**
```bash
curl -X POST http://localhost:8092/api/buses/create \
  -H "Content-Type: application/json" \
  -d '{
    "busNumber": "BUS-202",
    "seatingCapacity": 45,
    "operatorId": "OP-002",
    "contactNumber": "+9876543210",
    "source": "Los Angeles",
    "destination": "San Francisco",
    "departureTime": "09:00:00",
    "arrivalTime": "15:00:00",
    "status": "AVAILABLE"
  }'
```

**4. Make a Reservation:**
```bash
curl -X POST http://localhost:8093/reservations \
  -H "Content-Type: application/json" \
  -d '{
    "passengerId": 1,
    "busId": 1,
    "seatNumber": "B5",
    "travelDate": "2025-12-20",
    "boardingPoint": "LA Terminal",
    "dropOffPoint": "SF Station",
    "status": "CONFIRMED"
  }'
```

### Using Postman

1. Import the API endpoints into Postman
2. Create a collection for each microservice
3. Set up environment variables for base URLs
4. Test each endpoint systematically

### Unit Testing

Run tests for each microservice:
```bash
cd {MicroserviceName}
mvn test
```

---

## 📁 Project Structure

```
Bus-Reservation-System-SpringBoot-main/
│
├── BusMicroservice/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/BusMicroservice/
│   │   │   │   ├── controller/          # REST Controllers
│   │   │   │   │   └── BusController.java
│   │   │   │   ├── data/                # Entity Models
│   │   │   │   │   ├── Bus.java
│   │   │   │   │   └── BusStatus.java
│   │   │   │   ├── service/             # Business Logic
│   │   │   │   │   ├── BusService.java
│   │   │   │   │   └── BusRepository.java
│   │   │   │   └── BusMicroserviceApplication.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── application.yml
│   │   └── test/                        # Unit Tests
│   ├── pom.xml
│   └── mvnw / mvnw.cmd                 # Maven Wrapper
│
├── PassengerMicroservice/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/PassengerMicroservice/
│   │   │   │   ├── controller/
│   │   │   │   │   └── PassengerController.java
│   │   │   │   ├── data/
│   │   │   │   │   └── Passenger.java
│   │   │   │   ├── dto/
│   │   │   │   │   └── LoginRequest.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── PassengerService.java
│   │   │   │   │   └── PassengerRepository.java
│   │   │   │   └── PassengerMicroserviceApplication.java
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── ReservationMicroservice/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/ReservationMicroservice/
│   │   │   │   ├── controller/
│   │   │   │   │   └── ReservationController.java
│   │   │   │   ├── data/
│   │   │   │   │   └── Reservation.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── ReservationService.java
│   │   │   │   │   └── ReservationRepository.java
│   │   │   │   └── ReservationMicroserviceApplication.java
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── NotificationMicroservice/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/NotificationMicroservice/
│   │   │   │   ├── controller/
│   │   │   │   │   └── NotificationController.java
│   │   │   │   ├── data/
│   │   │   │   │   └── Notification.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── NotificationService.java
│   │   │   │   │   └── NotificationRepository.java
│   │   │   │   └── NotificationMicroserviceApplication.java
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
└── README.md                           # This file
```

---

## 🔐 Security Considerations

### Current Implementation

1. **JWT Authentication**: Passenger service uses JWT tokens for authentication
2. **Password Storage**: Passwords should be encrypted (recommended: BCrypt)
3. **CORS Configuration**: Passenger service has CORS enabled for `http://localhost:3001`

### Recommended Enhancements

- ✅ Implement password hashing with BCrypt
- ✅ Add API Gateway for centralized authentication
- ✅ Implement rate limiting
- ✅ Add HTTPS/TLS encryption
- ✅ Use environment variables for sensitive data
- ✅ Implement OAuth2/OpenID Connect
- ✅ Add role-based access control (RBAC)
- ✅ Implement API key validation
- ✅ Add request validation and sanitization

---

## 🚀 Future Enhancements

### Phase 1: Infrastructure
- [ ] API Gateway implementation (Spring Cloud Gateway)
- [ ] Service Discovery (Eureka Server)
- [ ] Centralized Configuration (Spring Cloud Config)
- [ ] Load Balancing
- [ ] Circuit Breaker (Resilience4j)

### Phase 2: Features
- [ ] Payment gateway integration
- [ ] Email/SMS notifications
- [ ] Bus tracking (real-time location)
- [ ] Ratings and reviews
- [ ] Promotional offers and discounts
- [ ] Multi-language support
- [ ] Mobile app integration

### Phase 3: DevOps
- [ ] Docker containerization
- [ ] Kubernetes orchestration
- [ ] CI/CD pipeline (Jenkins/GitHub Actions)
- [ ] Monitoring (Prometheus, Grafana)
- [ ] Centralized logging (ELK Stack)
- [ ] API documentation (Swagger/OpenAPI)

### Phase 4: Advanced Features
- [ ] AI-based route optimization
- [ ] Dynamic pricing
- [ ] Predictive maintenance
- [ ] Analytics dashboard
- [ ] Multi-tenancy support

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

### Coding Standards
- Follow Java naming conventions
- Write unit tests for new features
- Document API endpoints
- Keep commits atomic and meaningful
- Update README for significant changes

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

- **Your Name** - *Initial work* - [GitHub Profile](https://github.com/Yashmika5)

---

## 📞 Support

For support and queries:
- **Email**: gajinduyashmika5@gmail.com
- **Issues**: [GitHub Issues](https://github.com/Yashmika5/Bus-Reservation-System-SpringBoot/issues)
- **Documentation**: [Wiki](https://github.com/Yashmika5/Bus-Reservation-System-SpringBoot/wiki)

---

## 🙏 Acknowledgments

- Spring Boot Documentation
- MySQL Documentation
- JWT.io for JWT implementation guides
- Stack Overflow community
- All contributors to this project

---

## 📊 Project Statistics

- **Total Lines of Code**: ~5000+
- **Microservices**: 4
- **API Endpoints**: 25+
- **Database Tables**: 4
- **Dependencies**: 15+

---

## 🔄 Version History

- **v0.0.1-SNAPSHOT** (Current)
  - Initial release
  - Core microservices implementation
  - Basic CRUD operations
  - JWT authentication
  - MySQL database integration

---

## 📸 Screenshots

*Add screenshots of your application here*

---

## 🎓 Learning Resources

- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Microservices Pattern](https://microservices.io/)
- [RESTful API Design](https://restfulapi.net/)
- [JWT Introduction](https://jwt.io/introduction)
- [MySQL Documentation](https://dev.mysql.com/doc/)

---

**⭐ If you find this project helpful, please give it a star!**

---

*Last Updated: November 30, 2025*
