package com.onlineshop.app.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(max = 60, message = "Слишком длинное имя")
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "customer")
    @Valid
    private List<Order> orders = new ArrayList<>();

    public Customer(String name, List<Order> orders) {

        this.name = name;
        this.orders = orders;
    }

}
