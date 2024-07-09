package com.onlineshop.app.services;

import com.onlineshop.app.dto.order.OrderRequest;
import com.onlineshop.app.dto.order.OrderResponse;
import com.onlineshop.app.exceptionUtils.exceptions.order.OrderBadRequest;

public interface OrderService {
    OrderResponse create(OrderRequest request) throws OrderBadRequest;
}
