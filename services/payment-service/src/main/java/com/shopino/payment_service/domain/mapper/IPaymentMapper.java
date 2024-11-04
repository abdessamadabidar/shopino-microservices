package com.shopino.payment_service.domain.mapper;


import com.shopino.payment_service.domain.dto.PaymentRequestDto;
import com.shopino.payment_service.domain.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPaymentMapper {
    Payment toPayment(PaymentRequestDto request);
}
