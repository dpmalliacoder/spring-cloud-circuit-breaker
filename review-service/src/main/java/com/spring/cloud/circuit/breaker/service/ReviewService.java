package com.spring.cloud.circuit.breaker.service;

import com.spring.cloud.circuit.breaker.dto.ProductReviewDto;

public interface ReviewService {

    ProductReviewDto getRatingForProduct(int productId);
}
