package com.spring.cloud.circuit.breaker.dto;

import java.util.List;

public record ProductReviewDto(List<ReviewDto> reviews, double ratingInAverage) {
}
