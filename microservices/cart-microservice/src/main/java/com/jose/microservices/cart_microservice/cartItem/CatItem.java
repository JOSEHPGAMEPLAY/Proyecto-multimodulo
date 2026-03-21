package com.jose.microservices.cart_microservice.cartItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CatItem {
    private Integer productId;
    private Integer quantity;
}
