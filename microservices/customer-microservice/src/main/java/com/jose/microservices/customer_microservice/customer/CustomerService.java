package com.jose.microservices.customer_microservice.customer;

import com.jose.microservices.customer_microservice.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String saveCustomer(CustomerRequest request) {
        if (!request.id().isBlank()){
            repository.findById(request.id())
                    .orElseThrow(
                            () -> new CustomerNotFoundException(
                                    String.format("Customer with id %s not found", request.id())
                            )
                    );
        }

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
                        () -> new CustomerNotFoundException(
                                String.format("Customer with id %s not found", id)
                        )
                );
    }

    public void deleteCustomerById(String id) {
        repository.findById(id)
                .orElseThrow(
                        () -> new CustomerNotFoundException(
                                String.format("Customer with id %s not found", id)
                        )
                );
        repository.deleteById(id);
    }
}
