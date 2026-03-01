package com.jose.microservices.product_microservice.product;

import com.jose.microservices.product_microservice.category.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public static ProductResponse toProductResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImageUrl(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public static Product toProduct(ProductRequest request){
        return new Product(
                request.id(),
                request.name(),
                request.description(),
                request.price(),
                request.stock(),
                request.imageUrl(),
                Category.builder()
                        .id(request.categoryId())
                        .build()
        );
    }
}
