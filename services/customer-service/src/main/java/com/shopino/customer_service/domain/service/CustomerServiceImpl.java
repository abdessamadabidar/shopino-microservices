package com.shopino.customer_service.domain.service;

import com.shopino.customer_service.application.ports.input.*;
import com.shopino.customer_service.application.ports.output.CustomerOutputPort;
import com.shopino.customer_service.domain.dto.request.CustomerRequestDto;
import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;
import com.shopino.customer_service.domain.exception.CustomerAlreadyRegisteredException;
import com.shopino.customer_service.domain.exception.CustomerNotFoundException;
import com.shopino.customer_service.domain.mappers.ICustomerMapper;
import com.shopino.customer_service.domain.model.Customer;
import com.shopino.customer_service.domain.utils.PageInfo;
import com.shopino.customer_service.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements
        CreateCustomersUseCase,
        GetPaginatedCustomersUseCase,
        DeleteCustomerUseCase,
        FindCustomerByIdUseCase,
        FindAllCustomersUseCase
{
    private final CustomerOutputPort customerOutputPort;
    private final ICustomerMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);


    @Override
    public List<CustomerResponseDto> createCustomers(List<CustomerRequestDto> requests) {
        requests
                .forEach(cr -> {
                    Optional<Customer> optionalCustomer = customerOutputPort.findByEmail(cr.getEmail());
                    if (optionalCustomer.isPresent()) {
                        LOGGER.error("customer with email {} already registered!", cr.getEmail());
                        throw new CustomerAlreadyRegisteredException(
                                "Customer already registered!");
                    }
                });

        List<Customer> savedCustomersList = customerOutputPort
                .saveAllCustomers(
                        mapper.toCustomers(requests)
                );

        return mapper.toCustomerResponses(savedCustomersList);
    }

    @Override
    public PageResponse<CustomerResponseDto> getPaginatedCustomers(int page, int size) {
        Page<Customer> customersPage = customerOutputPort.findAllCustomers(page, size);

        List<CustomerResponseDto> mappedCustomersList = mapper
                .toCustomerResponses(
                        customersPage.getContent()
                );



        return PageResponse.<CustomerResponseDto>builder()
                .content(mappedCustomersList)
                .pageInfo(
                        PageInfo
                                .builder()
                                .totalPages(customersPage.getTotalPages())
                                .totalElements(customersPage.getTotalElements())
                                .currentPage(customersPage.getNumber() + 1)
                                .size(customersPage.getSize())
                                .hasNext(customersPage.hasNext())
                                .hasPrevious(customersPage.hasPrevious())
                                .build()
                )
                .build();
    }

    @Override
    public String deleteCustomer(UUID id) {
        Customer customer = customerOutputPort
                .findById(id)
                .orElseThrow(
                        () -> {
                            LOGGER.error("customer that you're trying to delete ID : {} does not exist!", id);
                            return new CustomerNotFoundException(
                                    "Customer not found!");
                        }
                );

        customerOutputPort.deleteCustomer(customer.getId());

        return String.format(
                "Customer %s removed successfully",
                customer.getFirstName() + customer.getLastName()
        );
    }

    @Override
    public Optional<CustomerResponseDto> findCustomerById(UUID id) {
        return customerOutputPort.findById(id).map(mapper::toCustomerResponse);
    }

    @Override
    public List<CustomerResponseDto> findAllCustomers() {
        return mapper.toCustomerResponses(customerOutputPort.findAllCustomers());
    }
}
