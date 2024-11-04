package com.shopino.customer_service.application.ports.input;

import com.shopino.customer_service.domain.model.Customer;

import java.util.UUID;

public interface DeleteCustomerUseCase {
    String deleteCustomer(UUID id);
}
