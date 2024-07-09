package com.onlineshop.app.mappers;

import com.onlineshop.app.dto.customer.CustomerRequest;
import com.onlineshop.app.dto.customer.DbCustomerResponse;
import com.onlineshop.app.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    DbCustomerResponse customerToDbResponse(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    Customer requestToCustomer(CustomerRequest request);
}
