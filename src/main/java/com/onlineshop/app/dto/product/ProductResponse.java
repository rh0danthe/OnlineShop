package com.onlineshop.app.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductResponse {
    private String name;

    private String category;

    private BigDecimal price;
}
