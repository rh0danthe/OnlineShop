package com.onlineshop.app.mappers;

import com.onlineshop.app.dto.order.OrderRequest;
import com.onlineshop.app.dto.order.OrderResponse;
import com.onlineshop.app.dto.product.ProductResponse;
import com.onlineshop.app.entities.Customer;
import com.onlineshop.app.entities.Order;
import com.onlineshop.app.entities.Product;
import com.onlineshop.app.exceptionUtils.exceptions.customer.CustomerNotFound;
import com.onlineshop.app.exceptionUtils.exceptions.product.ProductNotFound;
import com.onlineshop.app.repository.CustomerRepository;
import com.onlineshop.app.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected ProductRepository productRepository;
    @Mapping(target = "customer", source = "customerId", qualifiedByName = "mapCustomerIdToCustomer")
    @Mapping(source = "productIds", target = "products", qualifiedByName = "mapProductIdsToProducts")
    public abstract Order requestToOrder(OrderRequest request);

    @Mapping(source = "customer.name", target = "customer.name")
    @Mapping(source = "products", target = "products", qualifiedByName = "mapProductsToResponse")
    public abstract OrderResponse orderToResponse(Order order);

    @Named("mapCustomerIdToCustomer")
    protected Customer mapCustomerIdToCustomer(int customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFound(String.format("Покупателя с айди %s не существует", customerId)));
    }

    @Named("mapProductIdsToProducts")
    protected List<Product> mapProductIdsToProducts(List<Integer> productIds) {
        return productIds.stream()
                .map(productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new ProductNotFound(String.format("Продукт с айди %s не существует", productId))))
                .collect(Collectors.toList());
    }

    @Named("mapProductsToResponse")
    protected List<ProductResponse> mapProductsToResponse(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(product -> new ProductResponse(product.getName(), product.getCategory(), product.getPrice()))
                .collect(Collectors.toList());
    }

}
