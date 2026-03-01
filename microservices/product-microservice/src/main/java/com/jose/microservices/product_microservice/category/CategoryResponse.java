package com.jose.microservices.product_microservice.category;

import com.jose.microservices.product_microservice.product.ProductResponse;

import java.util.List;

public record CategoryResponse(
        Integer id,
        String name,
        String description,
        List<ProductResponse> products
) {
}
