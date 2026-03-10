Food Ordering Backend System

Overview

Food Ordering Backend System is a RESTful backend application built using Spring Boot. It provides secure APIs for managing users, restaurants, carts, and orders in an online food ordering platform.

The application implements authentication and authorization using JSON Web Tokens to ensure secure communication between clients and the server. The system follows a layered architecture to maintain clean separation between controllers, services, and database operations.

This project demonstrates backend development skills including REST API design, authentication and authorization, database integration, and scalable backend architecture.

Technologies Used

Java
Spring Boot
Spring Security
JWT Authentication
Spring Data JPA
Hibernate
MySQL
Maven
RESTful APIs

Key Features

User Registration and Login
JWT Based Authentication and Authorization
Restaurant Management APIs
Cart Management APIs
Order Management System
Secure REST Endpoints using Spring Security
Layered Backend Architecture

System Architecture

The application follows a layered architecture.

Controller Layer
Handles incoming HTTP requests and returns responses.

Service Layer
Contains business logic and application processing.

Repository Layer
Handles database operations using Spring Data JPA.

Entity Layer
Represents database tables and relationships.

Security Layer
Handles authentication and authorization using Spring Security and JWT.

Project Structure

src/main/java

controller
Handles API requests and responses.

service
Contains business logic.

repository
Handles database interaction.

entity
Defines database entities.

config
Contains security configuration and JWT authentication filter.

dto
Used for transferring data between layers.

Modules

Authentication Module
Handles user registration and login. Generates JWT tokens for authenticated users.

Restaurant Module
Allows administrators to manage restaurants and users to view available restaurants.

Cart Module
Allows users to add items to the cart, update item quantities, and remove items.

Order Module
Allows users to place orders from the cart, view order details, and manage order history.

Security Implementation

Authentication is implemented using JSON Web Tokens.

When a user logs in successfully:

1. The system generates a JWT token.
2. The client stores the token.
3. The token must be sent in the Authorization header for protected requests.

Spring Security validates the token through a custom JWT authentication filter.

API Endpoints

Authentication APIs

POST /api/auth/register
POST /api/auth/login

Restaurant APIs

GET /api/restaurants
POST /api/restaurants
PUT /api/restaurants/{id}
DELETE /api/restaurants/{id}

Cart APIs

POST /api/cart/add
PUT /api/cart/update
DELETE /api/cart/remove
GET /api/cart

Order APIs

POST /api/orders
GET /api/orders
GET /api/orders/{id}

Database

The system uses MySQL as the relational database. Hibernate ORM is used for object relational mapping between Java entities and database tables.

How to Run the Project

Step 1
Clone the repository

git clone https://github.com/harishchilukani/food-ordering-backend.git

Step 2
Open the project in IntelliJ IDEA or Eclipse.

Step 3
Configure database credentials in application.properties.

spring.datasource.url
spring.datasource.username
spring.datasource.password

Step 4
Build the project using Maven.

mvn clean install

Step 5
Run the Spring Boot application.

Step 6
Use Postman or any REST client to test APIs.

Future Improvements

Payment Gateway Integration
Restaurant Ratings and Reviews
Order Tracking System
Email Notifications
Admin Dashboard
Microservices Architecture

Learning Outcomes

REST API development
Spring Boot backend architecture
JWT authentication and authorization
Spring Security configuration
Database integration with Spring Data JPA
Backend module design and layered architecture

Author

Harish
Java Full Stack Developer
BTech Computer Science and Engineering
