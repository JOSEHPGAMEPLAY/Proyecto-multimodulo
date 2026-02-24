package com.jose.microservices.customer_microservice.exceptions;

import com.jose.microservices.common_exceptions.ErrorResponse;
import com.jose.microservices.common_exceptions.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice(basePackages = "com.jose.microservices.customer_microservice")
public class CustomerExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
        var errors = new HashMap<String, String>();
        var fieldName = "customerId";
        errors.put(fieldName, e.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(errors));
    }

}
