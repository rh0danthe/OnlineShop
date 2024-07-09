package com.onlineshop.app.controllers;

import com.onlineshop.app.dto.customer.CustomerRequest;
import com.onlineshop.app.dto.customer.DbCustomerResponse;
import com.onlineshop.app.services.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ApiOperation(value = "Create Customer", notes = "Create a new customer using the provided customer data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer created successfully", response = DbCustomerResponse.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<DbCustomerResponse> create(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Customer by ID", notes = "Retrieve customer details by customer ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer found", response = DbCustomerResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<DbCustomerResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get All Customers", notes = "Retrieve a list of all customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of customers retrieved successfully", response = DbCustomerResponse.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<DbCustomerResponse>> getAll() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Customer", notes = "Update customer details by customer ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer details updated successfully", response = DbCustomerResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<DbCustomerResponse> update(@PathVariable int id, @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Customer", notes = "Delete customer by customer ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer deleted successfully", response = Boolean.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
