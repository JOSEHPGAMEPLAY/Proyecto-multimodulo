package com.jose.microservices.customer_microservice.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        String id,
        @NotBlank(message = "First Name is required")
        String firstName,
        @NotBlank(message = "Last Name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,
        String phone,
        String address,
        String city
) {
}
