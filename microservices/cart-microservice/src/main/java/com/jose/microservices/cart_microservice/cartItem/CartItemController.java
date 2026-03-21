package com.jose.microservices.cart_microservice.cartItem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/{customerId}/cart/items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping()
    public ResponseEntity<String> addItemToCart(@PathVariable("customerId") String customerId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        return ResponseEntity.ok().body(cartItemService.addItemToCart(customerId, cartItemRequest));
    }

    @PutMapping()
    public ResponseEntity<Void> updateItemFromCart(@PathVariable("customerId") String customerId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        cartItemService.updateItemFromCart(customerId, cartItemRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteItemFromCart(@PathVariable("customerId") String customerId, @PathVariable("productId") Integer productId) {
        cartItemService.deleteItemFromCart(customerId, productId);
        return ResponseEntity.noContent().build();
    }

}
