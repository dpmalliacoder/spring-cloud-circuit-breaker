package com.spring.cloud.circuit.breaker.service;

import com.spring.cloud.circuit.breaker.dto.ProductDto;

import java.util.concurrent.CompletableFuture;

public interface ProductService {

    CompletableFuture<ProductDto> getProduct(int productId);
}
