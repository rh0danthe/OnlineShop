package com.onlineshop.app.mappers;

import com.onlineshop.app.dto.product.ProductResponse;
import com.onlineshop.app.dto.product.ProductRequest;
import com.onlineshop.app.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse productToResponse(Product product);

    Product requestToProduct(ProductRequest request);
}
