package com.onlineshop.app.dto.order;

import com.onlineshop.app.dto.customer.CustomerResponse;
import com.onlineshop.app.dto.product.ProductResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private CustomerResponse customer;
    private List<ProductResponse> products;
}
