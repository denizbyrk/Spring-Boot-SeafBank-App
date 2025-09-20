# SeafBank – Spring Boot Banking Application

SeafBank is a backend banking application built with Java and Spring Boot, with secure authentication, account management, and common financial operations.

## Features

- **Authentication & Authorization**
  
  - JWT-based login/register system
  - Role-based access control (Admin / User)

- **User Management**
  
  - Create multiple accounts per user
  - Accounts linked with currencies (USD, EUR, TRY)

- **Account Operations**
  
  - Deposit & withdraw money
  - Transfer funds between accounts
  - Transaction history (with DTOs)

- **Interest System**

  - Scheduled interest calculation (configurable for seconds/minutes for testing)

- **Currency Conversion**

  - Hardcoded conversion rates between USD, EUR, TRY

  - API-ready structure for future live exchange integration

- **Transaction Logging**

  - Every action (deposit, withdraw, transfer, interest) stored as a transaction

- **Global Exception Handling**

  - Meaningful error responses for invalid actions or URLs

## Technologies

- Java
- Maven
- Spring Boot 3
- Spring Security (JWT Authentication)
- Spring Data JPA & Hibernate
- MySQL

## Installation

- **Clone Repository**
  
  ```
  git clone https://github.com/denizbyrk/Spring-Boot-SeafBank-App.git
  cd Spring-Boot-SeafBank-App
  ```
- **Configure Database**
  
  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/seafbank
  spring.datasource.username=root
  spring.datasource.password=yourpassword
  spring.jpa.hibernate.ddl-auto=update
  ```
- **Run**

  ```
  mvn spring-boot:run
  ```

## Test

Use Postman or cURL to interact with the API:

- **User**

  - GET /api/users → List users (Admin only)

  - GET /api/users/{id} → Get user (Admin only)
 
  - POST /api/users/register → Register new user

  - POST /api/auth/login → Login & receive JWT
 
- **Account**

  - GET /api/accounts → List all accounts (Admin only)

  - GET /api/accounts/myAccounts → List your accounts

  - GET /api/accounts/myTransactions → List your transaction history

  - POST /api/accounts/create?type={USD, EUR, TRY}&interestRate={rate} → Create account

  - POST /api/accounts/{accountId}/deposit?amount={amount} → Deposit money

  - POST /api/accounts/{accountId}/withdraw?amount={amount} → Withdraw money

- **Transaction**

  - GET /api/transactions → List all transactions (Admin only)
 
  - GET /api/transactions/myTransactions → List your transactions
  
  - POST /api/transactions/transfer → Transfer money

## Future Improvements

- Two-Factor Authentication (2FA)

- Password reset with email verification

- Live currency conversion API (right now they are hardcoded as of late september 2025)
