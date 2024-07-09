package com.onlineshop.app.controllers;

import com.onlineshop.app.dto.order.OrderRequest;
import com.onlineshop.app.dto.order.OrderResponse;
import com.onlineshop.app.entities.Order;
import com.onlineshop.app.helpers.ExportService;
import com.onlineshop.app.services.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ExportService exportService;
    @Autowired
    public OrderController(OrderService orderService, ExportService exportService) {
        this.orderService = orderService;
        this.exportService = exportService;
    }

    @PostMapping
    @ApiOperation(value = "Create Order", notes = "Create an order using the provided order data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order created successfully", response = OrderResponse.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request){
        OrderResponse order = orderService.create(request);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    @ApiOperation(value = "Export Order Statistics", notes = "Export order statistics as CSV")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Statistics exported successfully", response = OrderResponse.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public void exportCSV(HttpServletResponse response) throws IOException {
        exportService.exportOrdersToCsv(response);
    }
}
