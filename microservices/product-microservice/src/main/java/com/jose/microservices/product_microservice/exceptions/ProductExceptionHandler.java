package com.jose.microservices.product_microservice.exceptions;

import com.jose.microservices.common_exceptions.ErrorResponse;
import com.jose.microservices.common_exceptions.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice(basePackages = "com.jose.microservices.product_microservice")
@Primary
@Slf4j
public class ProductExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e) {

        var errors = new HashMap<String, String>();
        var fieldName = "product-service";
        errors.put(fieldName, e.getMessage());

        log.warn("Category error: {}", e.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errors));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {

        var errors = new HashMap<String, String>();
        var fieldName = "product-service";
        errors.put(fieldName, e.getMessage());

        log.warn("Product error: {}", e.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errors));
    }


    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorResponse> handleProductException(ProductException e) {

        var errors = new HashMap<String, String>();
        var fieldName = "product-service";
        errors.put(fieldName, e.getMessage());

        log.warn("Product error: {}", e.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

}
