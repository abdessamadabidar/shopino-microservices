package com.shopino.payment_service.application.ports.input;

import com.shopino.payment_service.domain.dto.PaymentRequestDto;
import com.shopino.payment_service.domain.model.Payment;

public interface CreatePaymentRequestUseCase {
    Payment createPaymentRequest(PaymentRequestDto request);
}
