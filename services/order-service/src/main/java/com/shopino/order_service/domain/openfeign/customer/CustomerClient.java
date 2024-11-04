package com.shopino.order_service.domain.openfeign.customer;


import org.shared.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@FeignClient(
        name = "customer-service",
        url = "http://localhost:8000/api/v1/customers"
)
@Component
public interface CustomerClient {

    @GetMapping
    List<CustomerResponseDto> findAllCustomers();

    @GetMapping("/{customerId}")
    Optional<CustomerResponseDto> findCustomerById(@PathVariable("customerId") UUID customerId);

}
