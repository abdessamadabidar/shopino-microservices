package com.shopino.payment_service.domain.service;

import com.shopino.payment_service.application.ports.input.CreatePaymentRequestUseCase;
import com.shopino.payment_service.application.ports.input.GetPaginatedPaymentsUseCase;
import com.shopino.payment_service.application.ports.output.PaymentOutputPort;
import com.shopino.payment_service.domain.dto.PaymentRequestDto;
import com.shopino.payment_service.domain.mapper.IPaymentMapper;
import com.shopino.payment_service.domain.model.Payment;
import com.shopino.payment_service.infrastructure.adapters.output.event.kafka.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.shared.PageInfo;
import org.shared.PageResponse;
import org.shared.PaymentRequestNotification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements
        CreatePaymentRequestUseCase,
        GetPaginatedPaymentsUseCase
{

    private final PaymentOutputPort paymentOutputPort;
    private final IPaymentMapper mapper;
    private final PaymentProducer paymentProducer;
    @Override
    public Payment createPaymentRequest(PaymentRequestDto request) {
        Payment payment = mapper.toPayment(request);
        payment.setCreatedDate(LocalDateTime.now());
        Payment savedPayment = paymentOutputPort.savePaymentRequest(payment);

        /*
         * Send Payment Request Notification
         */
        paymentProducer.sendPaymentRequestNotification(
                PaymentRequestNotification
                        .builder()
                        .paymentMethod(request.getPaymentMethod())
                        .customer(request.getCustomer())
                        .totalAmount(request.getAmount())
                        .orderReference(request.getOrderReference())
                        .requestDate(LocalDateTime.now())
                        .build()
        );

        return savedPayment;
    }

    @Override
    public PageResponse<Payment> getPaginatedCustomers(int page, int size) {

        Page<Payment> paymentPage = paymentOutputPort.findAllCustomers(page, size);


        return PageResponse.<Payment>builder()
                .content(paymentPage.getContent())
                .pageInfo(
                        PageInfo
                                .builder()
                                .totalPages(paymentPage.getTotalPages())
                                .totalElements(paymentPage.getTotalElements())
                                .currentPage(paymentPage.getNumber() + 1)
                                .size(paymentPage.getSize())
                                .hasNext(paymentPage.hasNext())
                                .hasPrevious(paymentPage.hasPrevious())
                                .build()
                )
                .build();
    }
}
