package com.jose.microservices.product_microservice.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        Integer id,
        @NotNull(message = "Category name is required")
        @NotBlank(message = "Category name cannot be blank")
        String name,
        String description
) {
}
