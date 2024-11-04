package com.shopino.customer_service.application.ports.output;

import com.shopino.customer_service.domain.model.Customer;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface CustomerOutputPort {
    Customer saveCustomer(Customer customer);
    List<Customer> saveAllCustomers(List<Customer> customers);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(UUID id);
    Page<Customer> findAllCustomers(int page, int size);
    List<Customer> findAllCustomers();
    void deleteCustomer(UUID id);

}
