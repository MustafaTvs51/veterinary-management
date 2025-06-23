# 🐾 Veterinary Management System

This project is a **Spring Boot RESTful API** designed for veterinary clinics to manage daily operations such as registering doctors, managing pet owners and animals, recording vaccines, and scheduling appointments efficiently.

## 📌 Table of Contents

- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Architecture](#architecture)
- [API Endpoints](#api-endpoints)
- [How to Run](#how-to-run)
- [Postman Collection](#postman-collection)
- [UML Diagram](#uml-diagram)
- [Author](#author)

---

## ✅ Overview

Veterinary Management System allows clinic staff to perform the following:

- Manage **veterinarian doctors** and their availability.
- Register **customers (pet owners)** and their **animals**.
- Record and track **vaccines** applied to animals.
- Create and manage **appointments** between animals and doctors.
- Prevent **vaccine duplicates** and **appointment overlaps** using business rules.

---

## 🛠️ Technologies Used

| Technology      | Purpose                                |
|----------------|----------------------------------------|
| Java 21         | Programming Language                   |
| Spring Boot     | Backend Framework                      |
| Spring Data JPA | ORM and Database Operations            |
| PostgreSQL / MySQL | Relational Database                 |
| Lombok          | Reduce Boilerplate Code                |
| Swagger (OpenAPI) | API Documentation                    |
| Postman         | API Testing                            |
| MapStruct (optional) | DTO ↔ Entity Mapping             |

---

## ✨ Features

### Animal & Customer Management
- Create, update, list and delete animals and owners.
- Filter animals and owners by name.
- View all animals owned by a customer.

### Vaccine Management
- Record vaccines for animals.
- Prevent duplicate vaccines using name, code, and protection dates.
- List vaccines per animal.
- Query vaccines with protection expiry in a given date range.

### Appointment Scheduling
- Schedule appointments with doctors.
- Check doctor availability and time slot before scheduling.
- List appointments by doctor/date range or animal/date range.

### Doctor Availability
- Register doctors.
- Add available working days for doctors (date only, no time).
- Prevent appointments outside of working days or overlapping hours.

---

## 🏛️ Architecture

- **Controller Layer**: Handles HTTP requests.
- **Service Layer**: Contains business logic.
- **Repository Layer**: Handles database interactions.
- **DTOs & Mappers**: Used for clean data transfer.
- **Custom Exceptions**: Meaningful error messages and validations.
- **UML & Swagger**: For visualization and API documentation.

---

## 🔗 API Endpoints

> Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 👤 Customers
- `POST /customers`
- `GET /customers`
- `GET /customers/by-name?name=Ahmet`
- `GET /customers/{id}/animals`

### 🐶 Animals
- `POST /animals`
- `GET /animals`
- `GET /animals/by-name?name=Boncuk`

### 💉 Vaccines
- `POST /vaccines`
- `GET /vaccines`
- `GET /vaccines/animal/{animalId}`
- `GET /vaccines/expiring?start=2025-01-01&end=2025-12-31`

### 📅 Appointments
- `POST /appointments`
- `GET /appointments/doctor?doctorId=1&start=...&end=...`
- `GET /appointments/animal?animalId=1&start=...&end=...`

### 👨‍⚕️ Doctors & Availability
- `POST /doctors`
- `GET /doctors`
- `POST /available-dates`
- `GET /available-dates/doctor/{id}`

---

## ▶️ How to Run

1. Clone the repo:

```bash
git clone https://github.com/MustafaTvs51/veterinary-management.git
cd veterinary-management
