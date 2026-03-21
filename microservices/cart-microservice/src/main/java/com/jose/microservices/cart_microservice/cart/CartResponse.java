package com.jose.microservices.cart_microservice.cart;

import com.jose.microservices.cart_microservice.cartItem.CartItemResponse;

import java.util.List;

public record CartResponse(
        String id,
        String customerId,
        List<CartItemResponse> items
) {
}
