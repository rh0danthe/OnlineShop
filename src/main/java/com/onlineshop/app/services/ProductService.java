package com.onlineshop.app.services;

import com.onlineshop.app.dto.product.ProductResponse;
import com.onlineshop.app.dto.product.ProductRequest;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(int id);

    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(int id, ProductRequest request);

    boolean deleteProduct(int id);
}
