package com.onlineshop.app.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Название товара не должно быть пустым")
    @Size(max = 160, message = "Слишком длинное название")
    private String name;

    @NotBlank(message = "Категория товара не должна быть пустой")
    @Size(max = 160, message = "Слишком длинное название категории")
    private String category;

    @NotNull(message = "Цена не должна быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше нуля")
    private BigDecimal price;
}
