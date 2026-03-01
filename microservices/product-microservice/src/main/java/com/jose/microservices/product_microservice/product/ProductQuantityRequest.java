package com.jose.microservices.product_microservice.product;

import jakarta.validation.constraints.NotNull;

public record ProductQuantityRequest(
        @NotNull(message = "Product Id cannot be null")
        Integer productId,
        @NotNull(message = "Quantity cannot be null")
        Integer quantity
) {
}
