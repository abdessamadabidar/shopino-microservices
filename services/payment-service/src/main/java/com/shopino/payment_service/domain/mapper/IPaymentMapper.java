package com.shopino.payment_service.domain.mapper;


import com.shopino.payment_service.domain.dto.PaymentRequestDto;
import com.shopino.payment_service.domain.model.Payment;


public interface IPaymentMapper {
    Payment toPayment(PaymentRequestDto request);
}
