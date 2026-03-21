package com.jose.microservices.product_microservice.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok().body(productService.createProduct(request));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(productService.getProductsByCategoryId(id));
    }

    @PutMapping()
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductRequest request) {
        productService.updateProduct(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/purchase")
    public ResponseEntity<Void> purchaseProduct(@Valid @RequestBody List<ProductQuantityRequest> requests){
        productService.purchaseProduct(requests);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/restock")
    public ResponseEntity<Void> updateProductStock(@Valid @RequestBody List<ProductQuantityRequest> requests){
        productService.restockProduct(requests);
        return ResponseEntity.noContent().build();
    }


}
