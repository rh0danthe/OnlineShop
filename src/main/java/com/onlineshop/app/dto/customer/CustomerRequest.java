package com.onlineshop.app.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(max = 60, message = "Слишком длинное имя")
    private String name;
}
