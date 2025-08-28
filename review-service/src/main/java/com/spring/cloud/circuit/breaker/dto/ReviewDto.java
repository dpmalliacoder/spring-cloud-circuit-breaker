package com.spring.cloud.circuit.breaker.dto;

public record ReviewDto(Integer productId, String comment, int rating, String userName) {
}
