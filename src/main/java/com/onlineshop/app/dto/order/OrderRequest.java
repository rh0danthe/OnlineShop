package com.onlineshop.app.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    @NotNull(message = "Айди клиента не может быть пустым")
    private int customerId;

    @NotEmpty(message = "Список товаров не может быть пустым")
    private List<Integer> productIds;
}
