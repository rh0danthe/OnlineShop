package com.onlineshop.app.mappers;

import com.onlineshop.app.dto.order.OrderResponse;
import com.onlineshop.app.dto.product.ProductResponse;
import com.onlineshop.app.entities.Order;
import com.onlineshop.app.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "customer.name", target = "customer.name")
    @Mapping(source = "products", target = "products")
    OrderResponse orderToResponse(Order order);

    @Named("mapProductsToResponse")
    default List<ProductResponse> mapProductsToResponse(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(product -> new ProductResponse(product.getName(), product.getCategory(), product.getPrice()))
                .collect(Collectors.toList());
    }

}
