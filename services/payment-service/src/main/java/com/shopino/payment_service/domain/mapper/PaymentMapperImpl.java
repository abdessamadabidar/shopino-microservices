package com.shopino.payment_service.domain.mapper;

import com.shopino.payment_service.domain.dto.PaymentRequestDto;
import com.shopino.payment_service.domain.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperImpl implements IPaymentMapper {

    @Override
    public Payment toPayment(PaymentRequestDto request) {
        if ( request == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.amount( request.getAmount() );
        payment.paymentMethod( request.getPaymentMethod() );
        payment.orderId( request.getOrderId() );

        return payment.build();
    }
}
