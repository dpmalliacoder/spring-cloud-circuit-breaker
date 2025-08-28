package com.spring.cloud.circuit.breaker.controller;

import com.spring.cloud.circuit.breaker.dto.ProductReviewDto;
import com.spring.cloud.circuit.breaker.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductReviewDto> getRating(@PathVariable int productId){
        return
                ResponseEntity.ok(this.reviewService.getRatingForProduct(productId));
    }
}
