package com.onlineshop.app.mappers;

import com.onlineshop.app.dto.customer.CustomerRequest;
import com.onlineshop.app.dto.customer.CustomerResponse;
import com.onlineshop.app.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    CustomerResponse customerToResponse(Customer customer);

    @Mapping(target = "id", ignore = true)
    Customer requestToCustomer(CustomerRequest request);
}
