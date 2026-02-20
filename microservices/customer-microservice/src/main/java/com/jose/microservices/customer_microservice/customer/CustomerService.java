package com.jose.microservices.customer_microservice.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String saveCustomer(CustomerRequest request) {
        var customer = mapper.toCustomer(request);
        var savedCustomer = repository.save(customer);
        return savedCustomer.getId();
    }

    public List<CustomerResponse> getAllCustomers() {
        var customers = repository.findAll();
        return customers.stream()
                .map(mapper::toCustomerResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(String id) {
        return repository.findById(id)
                .map(mapper::toCustomerResponse)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Cliente no encontrado : " + id)
                );
    }

    public void deleteCustomerById(String id) {
        repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Cliente no encontrado : " + id)
                );
        repository.deleteById(id);
    }
}
