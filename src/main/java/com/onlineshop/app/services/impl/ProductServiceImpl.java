package com.onlineshop.app.services.impl;

import com.onlineshop.app.dto.product.DbProductResponse;
import com.onlineshop.app.dto.product.ProductRequest;
import com.onlineshop.app.entities.Product;
import com.onlineshop.app.exceptionUtils.exceptions.product.ProductNotFound;
import com.onlineshop.app.mappers.ProductMapper;
import com.onlineshop.app.repository.ProductRepository;
import com.onlineshop.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public DbProductResponse createProduct(ProductRequest request) {
        Product product = mapper.requestToProduct(request);

        return mapper.productToDbResponse(productRepository.save(product));
    }

    @Override
    public DbProductResponse getProductById(int id) {
        return mapper.productToDbResponse(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound(String.format("Продукта с айди %s не существует", id))));
    }

    @Override
    public List<DbProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFound("Продуктов нет в базе данных");
        }
        return products.stream()
                .map(mapper::productToDbResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DbProductResponse updateProduct(int id, ProductRequest request) {
        Optional<Product> dbProduct = productRepository.findById(id);
        if (dbProduct.isEmpty()) {
            throw new ProductNotFound(String.format("Продукта с айди %s не существует", id));
        }

        Product product = mapper.requestToProduct(request);
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        return mapper.productToDbResponse(updatedProduct);
    }

    @Override
    public boolean deleteProduct(int id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            try {
                productRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new ProductNotFound(String.format("Продукта с айди %s не существует", id));
        }
    }
}
