package com.shopino.customer_service.domain.mappers;

import com.shopino.customer_service.domain.dto.request.CustomerRequestDto;
import com.shopino.customer_service.domain.dto.response.CustomerResponseDto;
import com.shopino.customer_service.domain.model.Customer;

import java.util.List;

public interface ICustomerMapper {
    CustomerResponseDto toCustomerResponse(Customer customer);
    List<CustomerResponseDto> toCustomerResponses(List<Customer> customers);
    Customer toCustomer(CustomerRequestDto request);
    List<Customer> toCustomers(List<CustomerRequestDto> requests);

}
