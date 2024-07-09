package com.onlineshop.app.services;

import com.onlineshop.app.dto.customer.CustomerRequest;
import com.onlineshop.app.dto.customer.DbCustomerResponse;
import com.onlineshop.app.entities.Customer;

import java.util.List;

public interface CustomerService {
    DbCustomerResponse createCustomer(CustomerRequest customer);

    DbCustomerResponse getCustomerById(int id);

    List<DbCustomerResponse> getAllCustomers();

    DbCustomerResponse updateCustomer(int id, CustomerRequest customerDetails);
    boolean deleteCustomer(int id);
}
