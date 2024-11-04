package com.shopino.order_service.domain.service;

import com.shopino.order_service.application.ports.input.FindAllOrdersUseCase;
import com.shopino.order_service.application.ports.input.GetPaginatedOrdersUseCase;
import com.shopino.order_service.application.ports.input.MakeOrderUseCase;
import com.shopino.order_service.application.ports.output.OrderOutputPort;
import com.shopino.order_service.domain.openfeign.customer.CustomerClient;
import com.shopino.order_service.domain.openfeign.payment.PaymentClient;
import com.shopino.order_service.domain.openfeign.product.ProductClient;
import com.shopino.order_service.domain.dto.request.OrderRequest;
import com.shopino.order_service.domain.dto.response.OrderResponseDto;
import com.shopino.order_service.domain.exception.ProductAvailableQuantityInsufficientException;
import com.shopino.order_service.domain.mapper.IOrderMapper;
import com.shopino.order_service.domain.model.Order;
import com.shopino.order_service.domain.model.OrderLine;
import com.shopino.order_service.domain.utils.PageInfo;
import com.shopino.order_service.domain.utils.PageResponse;
import com.shopino.order_service.domain.utils.StringGenerator;
import com.shopino.order_service.infrastructure.adapters.output.event.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.shared.CustomerResponseDto;
import org.shared.OrderConfirmationNotification;
import org.shared.PaymentRequestDto;
import org.shared.ProductResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class OrderService implements
        MakeOrderUseCase,
        GetPaginatedOrdersUseCase,
        FindAllOrdersUseCase
{
    private final OrderOutputPort orderOutputPort;
    private final ProductClient productClient;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final IOrderMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Override
    public OrderResponseDto makeOrder(OrderRequest request) throws InterruptedException {

        // get the customer if exists, if not will throw an exception
        CustomerResponseDto customer = customerClient
                .findCustomerById(request.getCustomerId())
                .orElseThrow(
                        () -> {
                            LOGGER.error("customer with id {} does not exist!", request.getCustomerId());
                            return new RuntimeException(
                                    "Customer does not exist!");
                        }
                );
        // init the products cart
        var products = Collections.emptyList();
        // init the total amount
        AtomicReference<Double> totalAmount = new AtomicReference<>(0D);
        // init the order items list
        List<OrderLine> orderLines = new ArrayList<>();

        // List of products
        List<ProductResponseDto> listOfProducts = new ArrayList<>();


        request.getOrderItems()
                .forEach(
                        (productId, quantity) -> {
                            ProductResponseDto product = productClient
                                    .findProductById(productId)
                                    .orElseThrow(
                                    () -> {
                                        LOGGER.error("product with id {} does not exist!", productId);
                                        return new RuntimeException(
                                                "Product Not Found");
                                    }
                            );
                            listOfProducts.add(product);

                            // check whether tha available quantity is sufficient
                            if (quantity > product.getAvailableQuantity()) {
                                LOGGER.error(
                                        """
                                                Insufficient product {} available quantity\s
                                                Available : {}\s
                                                Wanted : {}\s
                                        """,
                                        product.getName(),
                                        product.getAvailableQuantity(),
                                        quantity
                                );
                                throw new ProductAvailableQuantityInsufficientException(
                                        "Product's available quantity is insufficient"
                                );
                            }

                            // decrement the available quantity
                            product.setAvailableQuantity(
                                    product.getAvailableQuantity() - quantity
                            );

                            // increment the total amount of the order
                            totalAmount.updateAndGet(v -> v + product.getPrice() * quantity);

                            // build the order item and save it
                            // the order is set after its saving
                            OrderLine orderLine = OrderLine
                                    .builder()
                                    .quantity(quantity)
                                    .productId(product.getId())
                                    .build();

                            orderLines.add(orderLine);

                        }
                );

        // build the order and save it
        Order order = Order
                .builder()
                .createdDate(
                        LocalDateTime.now().minusDays(
                                new Random().nextInt(1, 365)
                        )
                )
                .reference(StringGenerator.generate(9, 5, 14))
                .totalAmount(totalAmount.get())
                .customerId(customer.getId())
                .paymentMethod(request.getPaymentMethod())
                .orderLines(orderLines)
                .build();


        Order savedOrder = orderOutputPort.saveOrder(order);
        orderLines.forEach(ol -> {
            ol.setOrder(savedOrder);
            orderLineService.createOrderLine(ol);
        });

        if (savedOrder != null) {
            // Request Payment Order
            PaymentRequestDto paymentRequest = PaymentRequestDto.builder()
                    .orderId(savedOrder.getId())
                    .paymentMethod(request.getPaymentMethod())
                    .amount(totalAmount.get())
                    .orderReference(savedOrder.getReference())
                    .customer(customer)
                    .build();

            paymentClient.requestOrderPayment(paymentRequest);


            // publish order confirmation
            orderProducer.sendOrderConfirmation(
                    OrderConfirmationNotification
                            .builder()
                            .orderReference(savedOrder.getReference())
                            .customer(customer)
                            .totalAmount(totalAmount.get())
                            .paymentMethod(request.getPaymentMethod())
                            .products(listOfProducts)
                            .orderDate(LocalDateTime.now())
                            .build()
            );



            Thread.sleep(8000);
        }


        return mapper.toOrderResponse(savedOrder);
    }

    @Override
    public PageResponse<OrderResponseDto> getPaginatedCustomers(int page, int size) {
        Page<Order> orderPage = orderOutputPort.findAllOrders(page, size);

        List<OrderResponseDto> mappedOrdersList = mapper.toOrderResponses(
                orderPage.getContent()
        );


        return PageResponse.<OrderResponseDto>builder()
                .content(mappedOrdersList)
                .pageInfo(
                        PageInfo
                                .builder()
                                .totalPages(orderPage.getTotalPages())
                                .totalElements(orderPage.getTotalElements())
                                .currentPage(orderPage.getNumber() + 1)
                                .size(orderPage.getSize())
                                .hasNext(orderPage.hasNext())
                                .hasPrevious(orderPage.hasPrevious())
                                .build()
                )
                .build();
    }

    @Override
    public List<OrderResponseDto> findAllOrders() {
        return mapper.toOrderResponses(
                orderOutputPort.findAllOrders()
        );
    }
}
