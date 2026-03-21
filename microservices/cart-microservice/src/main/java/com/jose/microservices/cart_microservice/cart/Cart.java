package com.jose.microservices.cart_microservice.cart;

import com.jose.microservices.cart_microservice.cartItem.CatItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Cart {
    @Id
    private String id;
    private String customerId;
    private List<CatItem> items;
}
