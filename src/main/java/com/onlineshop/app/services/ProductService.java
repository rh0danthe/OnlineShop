package com.onlineshop.app.services;

import com.onlineshop.app.dto.product.DbProductResponse;
import com.onlineshop.app.dto.product.ProductRequest;
import com.onlineshop.app.entities.Product;

import java.util.List;

public interface ProductService {
    DbProductResponse createProduct(ProductRequest request);

    DbProductResponse getProductById(int id);

    List<DbProductResponse> getAllProducts();
    DbProductResponse updateProduct(int id, ProductRequest request);

    boolean deleteProduct(int id);
}
