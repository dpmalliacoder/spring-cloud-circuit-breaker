package com.spring.cloud.circuit.breaker.service;

import com.spring.cloud.circuit.breaker.dto.ProductDto;
import com.spring.cloud.circuit.breaker.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ExecutorService executorService;
    private final ReviewRestServiceClient reviewRestServiceClient;

    public ProductServiceImpl(ProductRepository productRepository, ExecutorService executorService,
                              ReviewRestServiceClient reviewRestServiceClient) {
        this.productRepository = productRepository;
        this.executorService = executorService;
        this.reviewRestServiceClient = reviewRestServiceClient;
    }

    @Override
    public CompletableFuture<ProductDto> getProduct(int productId) {
        var product = CompletableFuture.supplyAsync(() ->
                productRepository.findById(productId), executorService);
        var rating = this.reviewRestServiceClient.getProductRatingDto(productId);
        return product.thenCombine(rating, (p, r) -> new ProductDto(productId, p.get().getDescription(), p.get().getPrice(), r));
    }
}
