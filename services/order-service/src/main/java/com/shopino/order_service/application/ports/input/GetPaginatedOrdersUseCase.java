package com.shopino.order_service.application.ports.input;

import com.shopino.order_service.domain.dto.response.OrderResponseDto;
import com.shopino.order_service.domain.utils.PageResponse;

public interface GetPaginatedOrdersUseCase {
    PageResponse<OrderResponseDto> getPaginatedCustomers(int page, int size);
}
