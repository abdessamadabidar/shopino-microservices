package com.shopino.payment_service.application.ports.input;

import com.shopino.payment_service.domain.model.Payment;
import org.shared.PageResponse;

public interface GetPaginatedPaymentsUseCase {
    PageResponse<Payment> getPaginatedCustomers(int page, int size);
}
