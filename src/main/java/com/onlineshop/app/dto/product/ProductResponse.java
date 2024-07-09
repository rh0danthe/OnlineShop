package com.onlineshop.app.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private String name;

    private String category;

    private BigDecimal price;
}
