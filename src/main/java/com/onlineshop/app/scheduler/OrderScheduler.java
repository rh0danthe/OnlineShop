package com.onlineshop.app.scheduler;

import com.onlineshop.app.dto.order.OrderRequest;
import com.onlineshop.app.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class OrderScheduler {
    private final OrderService orderService;

    @Autowired
    public OrderScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 300000)
    public void generateRandomOrder() throws IOException {
        try {
        Random random = new Random();
        int customerId = random.nextInt(5);
        List<Integer> productIds = Arrays.asList(random.nextInt(6), random.nextInt(6));

        OrderRequest request = new OrderRequest();
        request.setCustomerId(customerId);
        request.setProductIds(productIds);

        orderService.create(request);
        }
        catch(Exception e){
            System.err.println("Ошибка при генерации случайного заказа: " + e.getMessage());
        }
    }
}
