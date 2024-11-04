package com.shopino.customer_service.infrastructure.adapters.output.database;

import com.shopino.customer_service.application.ports.output.CustomerOutputPort;
import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;
import com.shopino.customer_service.domain.model.Customer;
import com.shopino.customer_service.infrastructure.adapters.output.database.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerOutputPort {

    private final CustomerJpaRepository repository;
    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public List<Customer> saveAllCustomers(List<Customer> customers) {
        return repository.saveAll(customers);
    }


    @Override
    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Page<Customer> findAllCustomers(int page, int size) {
        Pageable pr = PageRequest.of(page, size);
        Page<Customer> customerPage = repository.findAll(pr);

        return new PageImpl<>(customerPage.getContent(), pr, customerPage.getTotalElements());
    }

    @Override
    public List<Customer> findAllCustomers() {
        return repository.findAll();
    }

    @Override
    public void deleteCustomer(UUID id) {
        repository.deleteById(id);
    }
}
