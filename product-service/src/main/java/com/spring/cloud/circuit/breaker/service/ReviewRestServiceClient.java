package com.spring.cloud.circuit.breaker.service;

import com.spring.cloud.circuit.breaker.dto.ProductReviewDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;

@Service
public class ReviewRestServiceClient {

    private static final Logger log = LoggerFactory.getLogger(ReviewRestServiceClient.class);
    private final RestClient client;
    private final ExecutorService executorService;

    public ReviewRestServiceClient(RestClient client, ExecutorService executorService) {
        this.client = client;
        this.executorService = executorService;
    }

    @Retry(name = "review-service", fallbackMethod = "onFailure")
    @CircuitBreaker(name = "review-service", fallbackMethod = "onFailure")
    public CompletionStage<ProductReviewDto> getProductRatingDto(int productId) {
        return CompletableFuture.supplyAsync(() -> this.getRating(productId), executorService);
    }

    private ProductReviewDto getRating(int productId){
        return this.client.get()
                .uri("{productId}", productId)
                .headers( httpHeaders -> {
                    httpHeaders.add("Accept", "application/json");
                    httpHeaders.add("Content-Type", "application/json");
                    httpHeaders.add("X-Tenant", "mytenant");
                })
                .retrieve()
                .body(ProductReviewDto.class);
    }

    private CompletionStage<ProductReviewDto> onFailure(int productId, Throwable throwable) {
        log.error("error: {}", throwable.getMessage());
        return CompletableFuture.completedFuture(new ProductReviewDto(Collections.emptyList(), 0));
    }
}
