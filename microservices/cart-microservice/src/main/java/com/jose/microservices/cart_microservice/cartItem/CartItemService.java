package com.jose.microservices.cart_microservice.cartItem;

import com.jose.microservices.cart_microservice.cart.Cart;
import com.jose.microservices.cart_microservice.cart.CartRepository;
import com.jose.microservices.cart_microservice.customer.CustomerResponse;
import com.jose.microservices.cart_microservice.customer.ICustomerClient;
import com.jose.microservices.cart_microservice.exceptions.CartException;
import com.jose.microservices.cart_microservice.product.IProductClient;
import com.jose.microservices.cart_microservice.product.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final ICustomerClient customerClient;
    private final IProductClient productClient;

    private final CartRepository cartRepository;

    public String addItemToCart(String customerId, CartItemRequest cartItemRequest) {

        CustomerResponse customerResponse = customerClient.getCustomerById(customerId)
                .orElseThrow(
                        () -> new CartException("Customer with id %s does not exist".formatted(customerId))
                );

        ProductResponse productResponse = productClient.getProductById(cartItemRequest.productId())
                .orElseThrow(
                        () -> new CartException("Product with id %s does not exist".formatted(cartItemRequest.productId()))
                );

        if (productResponse.stock() < cartItemRequest.quantity()) {
            throw new CartException("Product whit id %s does not have enough stock".formatted(productResponse.name()));
        }

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElse(Cart.builder()
                        .customerId(customerId)
                        .items(new ArrayList<>())
                        .build());

        boolean productExists = cart.getItems().stream()
                .anyMatch(catItem -> catItem.getProductId().equals(cartItemRequest.productId()));

        if (productExists) {
            throw new CartException("Product with id %s already exists in the cart".formatted(cartItemRequest.productId()));
        }

        cart.getItems().add(
                CatItem.builder()
                        .productId(cartItemRequest.productId())
                        .quantity(cartItemRequest.quantity())
                        .build()
        );

        cartRepository.save(cart);

        return cart.getId();
    }


    public void updateItemFromCart(String customerId, @Valid CartItemRequest cartItemRequest) {
        CustomerResponse customerResponse = customerClient.getCustomerById(customerId)
                .orElseThrow(
                        () -> new CartException("Customer with id %s does not exist".formatted(customerId))
                );

        ProductResponse productResponse = productClient.getProductById(cartItemRequest.productId())
                .orElseThrow(
                        () -> new CartException("Product with id %s does not exist".formatted(cartItemRequest.productId()))
                );

        if (productResponse.stock() < cartItemRequest.quantity()) {
            throw new CartException("Product whit id %s does not have enough stock".formatted(productResponse.name()));
        }

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(
                    () -> new CartException("Cart with customer id %s does not exist".formatted(customerId))
                );

        cart.getItems().stream()
                .filter(catItem -> catItem.getProductId().equals(cartItemRequest.productId()))
                .findFirst()
                .ifPresent(catItem -> catItem.setQuantity(cartItemRequest.quantity()));

        cartRepository.save(cart);
    }

    public void deleteItemFromCart(String customerId, Integer productId) {
        CustomerResponse customerResponse = customerClient.getCustomerById(customerId)
                .orElseThrow(
                        () -> new CartException("Customer with id %s does not exist".formatted(customerId))
                );

        ProductResponse productResponse = productClient.getProductById(productId)
                .orElseThrow(
                        () -> new CartException("Product with id %s does not exist".formatted(productId))
                );

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(
                        () -> new CartException("Cart with customer id %s does not exist".formatted(customerId))
                );

        cart.getItems().removeIf(catItem -> catItem.getProductId().equals(productId));

        cartRepository.save(cart);
    }
}
