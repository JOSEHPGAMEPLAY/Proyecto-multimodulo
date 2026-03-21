package com.jose.microservices.cart_microservice.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/{customerId}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping()
    public ResponseEntity<CartResponse> getCartByCustomerId(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok().body(cartService.getCartByCustomerId(customerId));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteCartByCustomerId(@PathVariable("customerId") String customerId){
        cartService.deleteCartByCustomerId(customerId);
        return ResponseEntity.ok().body("Cart deleted successfully");
    }
}
