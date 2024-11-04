package com.shopino.order_service.domain.data;

import com.shopino.order_service.domain.openfeign.customer.CustomerClient;
import com.shopino.order_service.domain.openfeign.product.ProductClient;
import com.shopino.order_service.domain.dto.request.OrderRequest;
import com.shopino.order_service.domain.service.OrderService;
import net.datafaker.Faker;
import org.shared.CustomerResponseDto;
import org.shared.PaymentMethod;
import org.shared.ProductResponseDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    private final OrderService orderService;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final Faker faker;

    public DataLoader(OrderService orderService, CustomerClient customerClient, ProductClient productClient) {
        this.orderService = orderService;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {

        /*
         *   Generate data for order table
         */

        // Load all customers and products
        List<CustomerResponseDto> customers = customerClient.findAllCustomers();
        List<ProductResponseDto> products = productClient.findAllProducts();

        for (int i = 0; i < 10; ++i) {
            CustomerResponseDto randomCustomer = customers
                    .get(faker.random().nextInt(customers.size() - 1));

            // building shop cart
            Map<UUID, Integer> cart = new HashMap<>();
            // each customer may have between 1 and 5 order items
            for (int j = 0; j < faker.random().nextInt(1, 5); ++j) {
                cart.put(
                        products.get(faker.random().nextInt(products.size() - 1)).getId(),
                        faker.random().nextInt(1, 5)
                );

            }


            OrderRequest request = OrderRequest
                    .builder()
                    .paymentMethod(Arrays.stream(PaymentMethod.values()).toList().get(faker.random().nextInt(0, 4)))
                    .customerId(randomCustomer.getId())
                    .orderItems(cart)
                    .build();

            orderService.makeOrder(request);

        }
    }
}
