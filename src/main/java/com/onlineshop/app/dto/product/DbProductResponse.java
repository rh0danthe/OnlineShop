package com.onlineshop.app.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DbProductResponse {
    private int id;
    private String name;

    private String category;

    private BigDecimal price;
}
