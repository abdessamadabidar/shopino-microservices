package com.shopino.customer_service.application.ports.input;

import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;
import com.shopino.customer_service.domain.model.Customer;
import com.shopino.customer_service.domain.utils.PageResponse;

import java.util.List;

public interface GetPaginatedCustomersUseCase {
    PageResponse<CustomerResponseDto> getPaginatedCustomers(int page, int size);
}
