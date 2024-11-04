package com.shopino.customer_service.application.ports.input;

import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;

import java.util.Optional;
import java.util.UUID;

public interface FindCustomerByIdUseCase {
    Optional<CustomerResponseDto> findCustomerById(UUID id);

}
