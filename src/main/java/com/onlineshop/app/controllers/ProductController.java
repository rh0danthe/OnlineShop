package com.onlineshop.app.controllers;

import com.onlineshop.app.dto.customer.DbCustomerResponse;
import com.onlineshop.app.dto.product.DbProductResponse;
import com.onlineshop.app.dto.product.ProductRequest;
import com.onlineshop.app.entities.Product;
import com.onlineshop.app.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ApiOperation(value = "Create Product", notes = "Create a new product using the provided customer data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product created successfully", response = DbProductResponse.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<DbProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Product by ID", notes = "Retrieve product details by customer ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product found", response = DbProductResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<DbProductResponse> getById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @ApiOperation(value = "Get All Products", notes = "Retrieve a list of all products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of products retrieved successfully", response = DbProductResponse.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<DbProductResponse>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Product", notes = "Update product details by customer ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product details updated successfully", response = DbProductResponse.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<DbProductResponse> update(@PathVariable int id, @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Product", notes = "Delete product by product ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product deleted successfully", response = Boolean.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}

