package com.shopino.customer_service.application.ports.input;


import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;

import java.util.List;

public interface FindAllCustomersUseCase {
    List<CustomerResponseDto> findAllCustomers();
}
