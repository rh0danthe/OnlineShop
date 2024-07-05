package com.onlineshop.app.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class OrderRequest {
    @NotNull(message = "Айди клиента не может быть пустым")
    private int customerId;

    @NotEmpty(message = "Список товаров не может быть пустым")
    private Integer[] productIds;
}
