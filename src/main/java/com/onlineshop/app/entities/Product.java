package com.onlineshop.app.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Название товара не должно быть пустым")
    @Size(max = 160, message = "Слишком длинное название")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Категория товара не должна быть пустой")
    @Size(max = 160, message = "Слишком длинное название категории")
    private String category;

    @Column(nullable = false)
    @NotNull(message = "Цена не должна быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше нуля")
    private BigDecimal price;

    @ManyToMany(
            mappedBy = "products",
            fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Valid
    private List<Order> orders = new ArrayList<>();

    public Product(String name, String category, BigDecimal price, List<Order> orders) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.orders = orders;
    }
}
