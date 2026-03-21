package com.jose.microservices.product_microservice.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PostMapping()
    public ResponseEntity<Integer> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(service.createCategory(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @PutMapping()
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryRequest request) {
        service.updateCategory(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
