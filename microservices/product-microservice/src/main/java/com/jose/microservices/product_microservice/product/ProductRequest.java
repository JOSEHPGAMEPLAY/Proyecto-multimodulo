package com.jose.microservices.product_microservice.product;

public record ProductRequest(
        Integer id,
        String name,
        String description,
        Double price,
        Integer stock,
        String imageUrl,
        Integer categoryId
) {
}
