package com.jose.microservices.cart_microservice.cart;

import com.jose.microservices.cart_microservice.cartItem.CartItemResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartMapper {

    public CartResponse toCartResponse(Cart cart) {

        List<CartItemResponse> cartItemsResponse = cart.getItems().stream()
                .map(item -> new CartItemResponse(
                        item.getProductId(),
                        item.getQuantity()
                )).toList();

        return new CartResponse(
                cart.getId(),
                cart.getCustomerId(),
                cartItemsResponse
        );
    }
}
