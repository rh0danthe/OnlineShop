package com.onlineshop.app.services;

import com.onlineshop.app.dto.customer.CustomerRequest;
import com.onlineshop.app.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customer);

    CustomerResponse getCustomerById(int id);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(int id, CustomerRequest customerDetails);
    boolean deleteCustomer(int id);
}
