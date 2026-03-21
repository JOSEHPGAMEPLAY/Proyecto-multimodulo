package com.jose.microservices.cart_microservice.cartItem;

public record CartItemResponse(
        Integer productId,
        Integer quantity
) {
}
