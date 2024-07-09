package com.onlineshop.app.mappers;

import com.onlineshop.app.dto.product.DbProductResponse;
import com.onlineshop.app.dto.product.ProductRequest;
import com.onlineshop.app.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "price", target = "price")
    DbProductResponse productToDbResponse(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "price", target = "price")
    Product requestToProduct(ProductRequest request);
}
