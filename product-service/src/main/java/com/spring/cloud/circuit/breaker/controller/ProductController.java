package com.spring.cloud.circuit.breaker.controller;

import com.spring.cloud.circuit.breaker.dto.ProductDto;
import com.spring.cloud.circuit.breaker.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{productId}")
    public CompletableFuture<ProductDto> getProduct(@PathVariable int productId){
        return this.productService.getProduct(productId);
    }
}
