package com.onlineshop.app.services;

import com.onlineshop.app.dto.order.OrderRequest;
import com.onlineshop.app.dto.order.OrderResponse;
import com.onlineshop.app.entities.Order;
import com.onlineshop.app.exceptionUtils.exceptions.order.OrderBadRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest request) throws OrderBadRequest;
}
