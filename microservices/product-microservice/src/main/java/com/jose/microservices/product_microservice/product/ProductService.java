package com.jose.microservices.product_microservice.product;

import com.jose.microservices.product_microservice.category.CategoryRepository;
import com.jose.microservices.product_microservice.exceptions.CategoryNotFoundException;
import com.jose.microservices.product_microservice.exceptions.ProductException;
import com.jose.microservices.product_microservice.exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public Integer createProduct(ProductRequest request) {
        if (!categoryRepository.existsById(request.categoryId())) {
            throw new CategoryNotFoundException("Category whit id %s not found".formatted(request.categoryId()));
        }
        return repository.save(ProductMapper.toProduct(request)).getId();
    }

    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toProductResponse)
                .toList();
    }

    public ProductResponse getProductById(Integer id) {
        return repository.findById(id)
                .map(ProductMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product with id %s not found".formatted(id)));
    }

    public List<ProductResponse> getProductsByCategoryId(Integer id) {
        categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category with id %s not found".formatted(id))
        );

        return repository.findByCategoryId(id)
                .stream()
                .map(ProductMapper::toProductResponse)
                .toList();
    }

    public void updateProduct(ProductRequest request) {
        repository.findById(request.id()).orElseThrow(
                () -> new ProductNotFoundException("Product with id %s not found".formatted(request.id()))
        );
        repository.save(ProductMapper.toProduct(request));
    }

    public void deleteProduct(Integer id) {
        repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id %s not found".formatted(id))
        );

        repository.deleteById(id);
    }

    @Transactional
    public void purchaseProduct(List<ProductQuantityRequest> requests) {
        for (ProductQuantityRequest item : requests) {
            Product product = repository.findById(item.productId())
                    .orElseThrow(
                            () -> new ProductNotFoundException("Product with id %s not found".formatted(item.productId()))
                    );

            if (item.quantity() < 0) {
                throw new ProductException("Restock quantity cannot be negative for product ID %s".formatted(item.productId()));
            }

            if (product.getStock() < item.quantity()) {
                throw new ProductException("Insufficient stock for product with id %s".formatted(item.productId()));
            }

            product.setStock(product.getStock() - item.quantity());
            repository.save(product);
        }
    }

    @Transactional
    public void restockProduct(List<ProductQuantityRequest> requests) {
        for (ProductQuantityRequest item : requests) {
            Product product = repository.findById(item.productId())
                    .orElseThrow(
                            () -> new ProductNotFoundException("Product with id %s not found".formatted(item.productId()))
                    );

            if (item.quantity() < 0) {
                throw new ProductException("Restock quantity cannot be negative for product ID %s".formatted(item.productId()));
            }

            product.setStock(product.getStock() + item.quantity());
            repository.save(product);
        }
    }
}
