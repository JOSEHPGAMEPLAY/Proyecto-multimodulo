package com.jose.microservices.product_microservice.category;

import com.jose.microservices.product_microservice.exceptions.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public Integer createCategory(CategoryRequest request) {
        Category category = mapper.toCategory(request);
        return repository.save(category).getId();
    }

    public List<CategoryResponse> getAllCategories() {
        return repository.findAll()
                .stream()
                .map(mapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse getCategoryById(Integer id) {
        return repository.findById(id)
                .map(mapper::toCategoryResponse)
                .orElseThrow(
                        () -> new CategoryNotFoundException(String.format("Category with id %s not found", id))
                );
    }

    public void updateCategory(CategoryRequest request) {
        repository.findById(request.id()).orElseThrow(
                () -> new CategoryNotFoundException(String.format("Category with id %s not found", request.id()))
        );
        repository.save(mapper.toCategory(request));
    }

    public void deleteCategory(Integer id) {
        repository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(String.format("Category with id %s not found", id))
        );

        repository.deleteById(id);
    }
}
