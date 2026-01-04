# Twafq - Web & Mobile Platform

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-green?style=for-the-badge&logo=spring-boot)
![MongoDB](https://img.shields.io/badge/MongoDB-Latest-green?style=for-the-badge&logo=mongodb)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue?style=for-the-badge&logo=docker)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

A comprehensive full-stack web and mobile platform built with Spring Boot, featuring secure authentication, content management, payment processing, and more.

[Features](#features) • [Tech Stack](#tech-stack) • [Quick Start](#quick-start) • [API Documentation](#api-documentation) • [Contributing](#contributing)

</div>

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Docker Deployment](#docker-deployment)
- [Contributing](#contributing)
- [Support](#support)
- [License](#license)

---

## 🎯 Overview

Twafq is a powerful, scalable backend platform designed to support both web and mobile applications. It provides a robust foundation for managing content, user authentication, payments, color schemes, and data relationships with enterprise-grade security and performance.

**Current Version:** 0.0.1-SNAPSHOT  
**Latest Release:** [GitHub Releases](https://github.com/mostafa2742002/twafq-web-and-mobile/releases)

---

## ✨ Features

### Core Features
- ✅ **User Management** - Complete user authentication and management system
- ✅ **JWT Authentication** - Secure token-based authentication
- ✅ **Content Management** - Blog and article management system
- ✅ **Email Service** - Integrated email notifications
- ✅ **Color Management** - Dynamic color scheme management
- ✅ **Data Relationships** - Color pair associations and complex data structures
- ✅ **Data Validation** - Comprehensive input validation

### Payment Integration
- 💳 **PayPal Integration** - Accept PayPal payments
- 💳 **Braintree Integration** - Payment processing via Braintree
- 💳 **Paymob Integration** - Support for Egyptian payment gateway
- 💳 **Wallet System** - Digital wallet integration

### Security Features
- 🔐 **Spring Security** - Enterprise-grade security framework
- 🔐 **JWT Authentication** - Token-based user authentication
- 🔐 **CORS Support** - Cross-Origin Resource Sharing configuration
- 🔐 **Input Validation** - Request/Response validation
- 🔐 **Exception Handling** - Global exception handler

### Scalability & Performance
- 🚀 **MongoDB** - NoSQL database for horizontal scalability
- 🚀 **WebFlux** - Reactive programming support
- 🚀 **Docker Support** - Container-based deployment
- 🚀 **Auditing** - Spring Security auditor for tracking changes

---

## 🛠 Tech Stack

### Backend
| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Core language |
| **Spring Boot** | 3.3.2 | Framework |
| **Spring Data MongoDB** | Latest | Database access |
| **Spring Security** | Latest | Authentication & Authorization |
| **Spring WebFlux** | Latest | Reactive web framework |
| **Spring Mail** | Latest | Email service |

### Database & Storage
| Technology | Purpose |
|------------|---------|
| **MongoDB** | Primary NoSQL database |
| **MongoDB Atlas** | Cloud database hosting |

### Payment & External Services
| Service | Purpose |
|---------|---------|
| **PayPal** | Payment processing |
| **Braintree** | Payment gateway |
| **Paymob** | Egyptian payment gateway |
| **Gmail SMTP** | Email delivery |

### Development & DevOps
| Tool | Purpose |
|------|---------|
| **Maven** | Build management |
| **Docker** | Containerization |
| **Lombok** | Code generation |
| **JUnit & Spring Test** | Testing |

### Libraries
- **JWT Libraries** - java-jwt 4.2.1, jjwt 0.11.2
- **jQuery & Bootstrap** - Frontend dependencies
- **Validation Framework** - Bean Validation

---

## 📦 Prerequisites

Before you start, ensure you have the following installed:

- **Java 17 or higher** - [Download JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **Maven 3.6+** - [Download Maven](https://maven.apache.org/download.cgi)
- **MongoDB 4.4+** - [Download MongoDB](https://www.mongodb.com/try/download/community) or use [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
- **Docker & Docker Compose** (optional, for containerized deployment)
- **Git** - For version control

### System Requirements
- **RAM**: Minimum 2GB (4GB recommended)
- **CPU**: Multi-core processor
- **Storage**: 5GB free space

---

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/mostafa2742002/twafq-web-and-mobile.git
cd twafq-web-and-mobile
```

### 2. Configure Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Application
spring.application.name=twafq

# MongoDB Configuration
spring.data.mongodb.database=twafq_app
spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/?retryWrites=true&w=majority

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Payment Configuration
braintree.environment=sandbox
braintree.merchantId=your-merchant-id
braintree.publicKey=your-public-key
braintree.privateKey=your-private-key

# Additional payment gateways...
```

### 3. Build the Project

```bash
mvn clean package
```

### 4. Run the Application

```bash
java -jar target/twafq-0.0.1-SNAPSHOT.jar
```

Or run directly with Maven:

```bash
mvn spring-boot:run
```

### 5. Access the Application

- **API Base URL**: `http://localhost:8080`
- **Health Check**: `http://localhost:8080/actuator/health`

---

## 📁 Project Structure

```
twafq-web-and-mobile/
├── src/
│   ├── main/
│   │   ├── java/com/nasr/twafq/
│   │   │   ├── TwafqApplication.java          # Main Spring Boot entry point
│   │   │   ├── blog/                          # Blog management module
│   │   │   │   ├── controller/                # REST endpoints
│   │   │   │   ├── service/                   # Business logic
│   │   │   │   ├── repository/                # Data access
│   │   │   │   ├── entity/                    # JPA entities
│   │   │   │   └── dto/                       # Data transfer objects
│   │   │   ├── color/                         # Color management module
│   │   │   ├── colorspairs/                   # Color pair associations
│   │   │   ├── samevalue/                     # Same value mapping
│   │   │   ├── user/                          # User management module
│   │   │   ├── payment*/                      # Payment integrations
│   │   │   │   ├── paypal/                    # PayPal integration
│   │   │   │   ├── braintree/                 # Braintree integration
│   │   │   │   └── paymob/                    # Paymob integration
│   │   │   ├── jwt/                           # JWT authentication
│   │   │   ├── email/                         # Email service
│   │   │   ├── exception/                     # Exception handling
│   │   │   ├── configuration/                 # Spring configurations
│   │   │   │   ├── AppConfig.java
│   │   │   │   ├── CorsConfig.java
│   │   │   │   ├── MongoConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── constants/                     # Application constants
│   │   │   └── dto/                           # Global DTOs
│   │   └── resources/
│   │       ├── application.properties         # Configuration file
│   │       ├── static/                        # Static files
│   │       └── templates/                     # HTML templates
│   └── test/
│       └── java/                              # Unit and integration tests
├── pom.xml                                    # Maven configuration
├── Dockerfile                                 # Docker image configuration
├── build_and_push.sh                          # Docker build script
└── README.md                                  # This file
```

---

## ⚙️ Configuration

### Environment Variables

Set these variables in your deployment environment:

```bash
# MongoDB
MONGODB_URI=mongodb+srv://user:pass@cluster.mongodb.net/twafq_app

# Email
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password

# JWT
JWT_SECRET=your-secret-key

# Payment Gateways
PAYPAL_CLIENT_ID=your-paypal-id
PAYPAL_CLIENT_SECRET=your-paypal-secret
BRAINTREE_MERCHANT_ID=your-merchant-id
```

### Key Configuration Classes

| Class | Purpose |
|-------|---------|
| [AppConfig.java](src/main/java/com/nasr/twafq/configuration/AppConfig.java) | General application configuration |
| [SecurityConfig.java](src/main/java/com/nasr/twafq/configuration/SecurityConfig.java) | Spring Security setup |
| [CorsConfig.java](src/main/java/com/nasr/twafq/configuration/CorsConfig.java) | CORS policy configuration |
| [MongoConfig.java](src/main/java/com/nasr/twafq/configuration/MongoConfig.java) | MongoDB configuration |

---

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication
All protected endpoints require a JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

### Main Endpoints

#### User Module
```
POST   /api/users/register          - Register new user
POST   /api/users/login             - User login
GET    /api/users/{id}              - Get user by ID
PUT    /api/users/{id}              - Update user
DELETE /api/users/{id}              - Delete user
```

#### Blog Module
```
GET    /api/blogs                   - Get all blogs
GET    /api/blogs/{id}              - Get blog by ID
POST   /api/blogs                   - Create blog
PUT    /api/blogs/{id}              - Update blog
DELETE /api/blogs/{id}              - Delete blog
```

#### Color Module
```
GET    /api/colors                  - Get all colors
GET    /api/colors/{id}             - Get color by ID
POST   /api/colors                  - Create color
PUT    /api/colors/{id}             - Update color
DELETE /api/colors/{id}             - Delete color
```

#### Payment Module
```
POST   /api/payments/paypal         - Process PayPal payment
POST   /api/payments/braintree      - Process Braintree payment
POST   /api/payments/paymob         - Process Paymob payment
GET    /api/payments/{id}           - Get payment status
```

### Response Format

All responses follow a standard format:

**Success Response:**
```json
{
  "status": 200,
  "message": "Success",
  "data": {
    // Response data
  }
}
```

**Error Response:**
```json
{
  "status": 400,
  "message": "Error message",
  "error": "Error details"
}
```

---

## 🗄️ Database Schema

### Key Collections

#### Users
```json
{
  "_id": ObjectId,
  "username": String,
  "email": String,
  "password": String (encrypted),
  "firstName": String,
  "lastName": String,
  "createdAt": Date,
  "updatedAt": Date,
  "createdBy": String,
  "lastModifiedBy": String
}
```

#### Blogs
```json
{
  "_id": ObjectId,
  "title": String,
  "content": String,
  "author": ObjectId (User),
  "sentences": [SentenceObject],
  "links": [LinkObject],
  "createdAt": Date,
  "updatedAt": Date
}
```

#### Colors
```json
{
  "_id": ObjectId,
  "name": String,
  "hexCode": String,
  "description": String,
  "createdAt": Date,
  "updatedAt": Date
}
```

#### ColorPairs
```json
{
  "_id": ObjectId,
  "color1": ObjectId,
  "color2": ObjectId,
  "pairing": String,
  "createdAt": Date
}
```

---

## 🐳 Docker Deployment

### Build Docker Image

```bash
./build_and_push.sh
```

Or manually:

```bash
docker build -t sasa274/twaafq:v1 .
```

### Run with Docker

```bash
docker run -d \
  -p 8080:8080 \
  -e MONGODB_URI="mongodb+srv://user:pass@cluster.mongodb.net/twafq_app" \
  -e MAIL_USERNAME="your-email@gmail.com" \
  -e MAIL_PASSWORD="your-password" \
  --name twafq \
  sasa274/twaafq:v1
```

### Docker Compose (Optional)

Create `docker-compose.yml`:

```yaml
version: '3.8'

services:
  twafq:
    image: sasa274/twaafq:v1
    ports:
      - "8080:8080"
    environment:
      MONGODB_URI: "mongodb+srv://user:pass@cluster.mongodb.net/twafq_app"
      MAIL_USERNAME: "your-email@gmail.com"
      MAIL_PASSWORD: "your-password"
    depends_on:
      - mongodb

  mongodb:
    image: mongo:5.0
    ports:
      - "27017:27017"
    volumes:
      - mongodb_data:/data/db

volumes:
  mongodb_data:
```

Run with: `docker-compose up -d`

---

## 🧪 Testing

### Run Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=TwafqApplicationTests
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
   ```bash
   git clone https://github.com/yourusername/twafq-web-and-mobile.git
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```

3. **Commit your changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```

4. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```

5. **Open a Pull Request**
   - Provide a clear description of your changes
   - Reference any related issues
   - Ensure all tests pass

### Coding Standards
- Follow Google Java Style Guide
- Write meaningful commit messages
- Add tests for new features
- Update documentation

---

## 📋 Development Roadmap

- [ ] Mobile app integration (Android/iOS)
- [ ] Advanced analytics dashboard
- [ ] Multi-language support (i18n)
- [ ] GraphQL API alternative
- [ ] Caching layer optimization
- [ ] Enhanced logging and monitoring
- [ ] Performance improvements

---

## 🐛 Known Issues & Troubleshooting

### MongoDB Connection Issues
```
Error: Unable to connect to MongoDB
Solution: Verify your connection string and network access in MongoDB Atlas
```

### Email Service Not Working
```
Error: Authentication failed
Solution: Enable "Less secure app access" or use App Password for Gmail
```

### JWT Token Expired
```
Error: Invalid or expired token
Solution: Generate a new token by logging in again
```

---

## 📞 Support

### Getting Help
- 📧 **Email**: [Support Email]
- 🐛 **Bug Reports**: [GitHub Issues](https://github.com/mostafa2742002/twafq-web-and-mobile/issues)
- 💬 **Discussions**: [GitHub Discussions](https://github.com/mostafa2742002/twafq-web-and-mobile/discussions)

### Documentation
- [API Documentation](docs/API.md)
- [Setup Guide](docs/SETUP.md)
- [Deployment Guide](docs/DEPLOYMENT.md)

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

```
MIT License

Copyright (c) 2024 Mostafa Mahmoud

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

---

## 👨‍💻 Author

**Mostafa Mahmoud**

<div align="center">

[![GitHub](https://img.shields.io/badge/GitHub-mostafa2742002-black?style=for-the-badge&logo=github)](https://github.com/mostafa2742002)
[![Email](https://img.shields.io/badge/Email-Contact-red?style=for-the-badge&logo=gmail)](mailto:mostafa19500mahmoud@gmail.com)

---

<p align="center">Made with ❤️ in Egypt</p>

**[⬆ back to top](#twafq---web--mobile-platform)**

</div>
