package com.onlineshop.app.controllers;

import com.onlineshop.app.dto.order.OrderRequest;
import com.onlineshop.app.dto.order.OrderResponse;
import com.onlineshop.app.services.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    @ApiOperation(value = "Place a new order", notes = "Create a new order with the provided request data.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order placed successfully", response = OrderResponse.class),
            @ApiResponse(code = 400, message = "Invalid input data"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> placeOrder(@Valid @RequestBody OrderRequest request) throws Exception {
        OrderResponse order = orderService.create(request);
        return ResponseEntity.ok(order);
    }
}
