package com.onlineshop.app.services.impl;

import com.onlineshop.app.dto.order.OrderRequest;
import com.onlineshop.app.dto.order.OrderResponse;
import com.onlineshop.app.entities.Customer;
import com.onlineshop.app.entities.Order;
import com.onlineshop.app.entities.Product;
import com.onlineshop.app.mappers.OrderMapper;
import com.onlineshop.app.repository.CustomerRepository;
import com.onlineshop.app.repository.OrderRepository;
import com.onlineshop.app.repository.ProductRepository;
import com.onlineshop.app.services.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponse create(OrderRequest request) throws IOException {
        Optional<Customer> customerOpt = customerRepository.findById(request.getCustomerId());

        if (customerOpt.isEmpty()) {
            String errorMessage = String.format("Покупателя с айди %s не существует", request.getCustomerId());
            logger.error("Ошибка при создании заказа: {}", errorMessage);
            throw new IOException(errorMessage);
        }

        List<Product> products = new ArrayList<>();

        for (int productId : request.getProductIds()){
            Optional<Product> productOpt = productRepository.findById(productId);
            if (productOpt.isEmpty()) {
                String errorMessage = String.format("Продукта с айди %s не существует", productId);
                logger.error("Ошибка при создании заказа: {}", errorMessage);
                throw new IOException(errorMessage);
            }
            else products.add(productOpt.get());
        }

        Order order = new Order(customerOpt.get(), products);

        Order savedOrder = orderRepository.save(order);

        logger.info("Заказ успешно создан, айди: {}", order.getId());

        return OrderMapper.INSTANCE.orderToResponse(savedOrder);
    }
}
