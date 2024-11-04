package com.shopino.customer_service.domain.data;

import com.shopino.customer_service.application.ports.input.CreateCustomersUseCase;
import com.shopino.customer_service.domain.dto.request.CustomerRequestDto;
import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;
import com.shopino.customer_service.domain.model.Address;
import com.shopino.customer_service.domain.service.CustomerServiceImpl;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CreateCustomersUseCase useCase;
    private final Faker faker;

    public DataLoader(CreateCustomersUseCase useCase) {
        this.useCase = useCase;
        this.faker = new Faker();
    }


    @Override
    public void run(String... args) throws Exception {

        /*
         *   Generate data for customer table
         */
        List<CustomerRequestDto> customerRequestList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CustomerRequestDto request = CustomerRequestDto
                    .builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .phone(faker.phoneNumber().cellPhone())
                    .email(faker.internet().emailAddress())
                    .address(
                            Address
                                    .builder()
                                    .zipCode(Long.valueOf(faker.address().zipCode()))
                                    .houseNumber(Integer.valueOf(faker.address().buildingNumber()))
                                    .street(faker.address().streetAddress())
                                    .city(faker.address().cityName())
                                    .build()
                    )
                    .build();

            customerRequestList.add(request);
        }
        useCase.createCustomers(customerRequestList);
    }
}
