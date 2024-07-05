package com.onlineshop.app.services;

import com.onlineshop.app.dto.order.OrderRequest;
import com.onlineshop.app.dto.order.OrderResponse;

import java.io.IOException;

public interface OrderService {
    OrderResponse create(OrderRequest request) throws IOException;
}
