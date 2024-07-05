package com.onlineshop.app.dto.order;

import com.onlineshop.app.dto.customer.CustomerResponse;
import com.onlineshop.app.dto.product.ProductResponse;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private CustomerResponse customer;
    private List<ProductResponse> products;
}
