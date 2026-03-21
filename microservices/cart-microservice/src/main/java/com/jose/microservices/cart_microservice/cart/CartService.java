package com.jose.microservices.cart_microservice.cart;

import com.jose.microservices.cart_microservice.customer.ICustomerClient;
import com.jose.microservices.cart_microservice.exceptions.CartException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ICustomerClient customerClient;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartResponse getCartByCustomerId(String customerId){
        customerClient.getCustomerById(customerId)
                .orElseThrow(
                        () -> new CartException("Customer with id %s does not exist".formatted(customerId))
                );

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(
                        () -> new CartException("Cart with customer id %s does not exist".formatted(customerId))
                );

        return cartMapper.toCartResponse(cart);
    }

    public void deleteCartByCustomerId(String customerId) {
        customerClient.getCustomerById(customerId)
                .orElseThrow(
                        () -> new CartException("Customer with id %s does not exist".formatted(customerId))
                );

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(
                        () -> new CartException("Cart with customer id %s does not exist".formatted(customerId))
                );

        cartRepository.delete(cart);
    }
}
