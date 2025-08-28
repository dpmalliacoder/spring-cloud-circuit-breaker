package com.spring.cloud.circuit.breaker.dto;

public record ProductDto(int id, String description, double price, ProductReviewDto productReview) {
}
