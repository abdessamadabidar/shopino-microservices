package com.shopino.customer_service.application.ports.input;

import com.shopino.customer_service.domain.dto.request.CustomerRequestDto;
import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CreateCustomersUseCase {
    List<CustomerResponseDto> createCustomers(List<CustomerRequestDto> requests);
}
