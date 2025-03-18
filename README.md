# Payment Gateway with Spring Boot and Stripe

## Description
This project implements a payment gateway using Spring Boot as the backend and Stripe as the payment service provider. The application allows processing credit card payments, managing subscriptions, and generating transaction reports.

## Technologies Used
- Java 17
- Spring Boot 3.2.x
- Spring Security
- Spring Data JPA
- Stripe API
- PostgreSQL
- Maven

## Features
- Credit card payment processing
- Support for one-time payments and subscriptions
- Webhook for Stripe notifications

## Prerequisites
- JDK 17 or higher
- Maven 3.8 or higher
- PostgreSQL 14 or higher
- Stripe account (required for API keys)

## Project Structure
```
aitorortega03-stripe-payments/
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── stripe/
    │   │   │           └── stripe_payments/
    │   │   │               ├── StripePaymentsApplication.java
    │   │   │               ├── common/
    │   │   │               │   ├── constants/
    │   │   │               │   │   └── ApiPathConstants.java
    │   │   │               │   ├── dto/
    │   │   │               │   │   ├── AuthResponseDTO.java
    │   │   │               │   │   ├── CheckoutRequest.java
    │   │   │               │   │   ├── CheckoutResponse.java
    │   │   │               │   │   └── UserRequest.java
    │   │   │               │   ├── entity/
    │   │   │               │   │   ├── Payment.java
    │   │   │               │   │   └── User.java
    │   │   │               │   └── enums/
    │   │   │               │       └── StripeEvent.java
    │   │   │               ├── controller/
    │   │   │               │   ├── AuthApi.java
    │   │   │               │   ├── StripeApi.java
    │   │   │               │   └── impl/
    │   │   │               │       ├── AuthController.java
    │   │   │               │       └── StripeController.java
    │   │   │               ├── repository/
    │   │   │               │   ├── PaymentRepository.java
    │   │   │               │   └── UserRepository.java
    │   │   │               ├── service/
    │   │   │               │   ├── AuthService.java
    │   │   │               │   ├── StripeService.java
    │   │   │               │   └── impl/
    │   │   │               │       ├── AuthServiceImpl.java
    │   │   │               │       └── StripeServiceImpl.java
    │   │   │               └── strategy/
    │   │   │                   ├── StripeStrategy.java
    │   │   │                   └── impl/
    │   │   │                       ├── StripeStrategyCheckoutSessionCompleted.java
    │   │   │                       └── StripeStrategyPaymentIntentSucceed.java
    │   │   └── resources/
    │   │       └── application.yml
    │   └── test/
    │       └── java/
    │           └── com/
    │               └── stripe/
    │                   └── stripe_payments/
    │                       └── StripePaymentsApplicationTests.java
    └── .mvn/
        └── wrapper/
            └── maven-wrapper.properties

```

## API Endpoints

### Payments
- `POST /v1/auth` - Create user
- `POST /v1/stripe/checkout` - Checkout payment

### Webhooks
- `POST /v1/stripe/webhook` - Endpoint for Stripe webhooks

## Payment Flow
1. Customer selects products and proceeds to checkout
2. The application creates a payment intent in Stripe
3. The frontend uses Stripe Elements to collect card information
4. Payment is confirmed and the transaction is processed
5. Stripe sends a webhook with the transaction result
6. The application updates the order status based on the result

## Contributions
Contributions are welcome. Please follow these steps:
1. Fork the repository
2. Create a branch (`git checkout -b feature/new-feature`)
3. Make your changes and commit (`git commit -m 'Add new feature'`)
4. Push your changes (`git push origin feature/new-feature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.