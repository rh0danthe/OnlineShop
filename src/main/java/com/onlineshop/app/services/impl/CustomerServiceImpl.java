package com.onlineshop.app.services.impl;

import com.onlineshop.app.dto.customer.CustomerRequest;
import com.onlineshop.app.dto.customer.CustomerResponse;
import com.onlineshop.app.entities.Customer;
import com.onlineshop.app.exceptionUtils.exceptions.customer.CustomerNotFound;
import com.onlineshop.app.mappers.CustomerMapper;
import com.onlineshop.app.repository.CustomerRepository;
import com.onlineshop.app.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = mapper.requestToCustomer(request);

        return mapper.customerToResponse(customerRepository.save(customer));
    }

    @Override
    public CustomerResponse getCustomerById(int id) throws CustomerNotFound {
        return mapper.customerToResponse(customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFound(String.format("Пользователя с айди %s не существует", id))));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new CustomerNotFound("Покупателей нет в базе данных");
        }
        return customers.stream()
                .map(mapper::customerToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(int id, CustomerRequest request) {
        Optional<Customer> dbCustomer = customerRepository.findById(id);
        if (dbCustomer.isEmpty()) {
            throw new CustomerNotFound(String.format("Пользователя с айди %s не существует", id));
        }

        Customer customer = mapper.requestToCustomer(request);
        customer.setId(id);
        Customer updatedCustomer = customerRepository.save(customer);
        return mapper.customerToResponse(updatedCustomer);
    }

    @Override
    public boolean deleteCustomer(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            try {
                customerRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new CustomerNotFound(String.format("Пользователя с айди %s не существует", id));
        }
    }
}
