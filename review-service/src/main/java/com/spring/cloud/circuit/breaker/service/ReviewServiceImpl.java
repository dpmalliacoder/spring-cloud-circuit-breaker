package com.spring.cloud.circuit.breaker.service;

import com.spring.cloud.circuit.breaker.dto.ProductReviewDto;
import com.spring.cloud.circuit.breaker.dto.ReviewDto;
import com.spring.cloud.circuit.breaker.entity.Review;
import com.spring.cloud.circuit.breaker.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ProductReviewDto getRatingForProduct(int productId) {
        var reviews = reviewRepository.findByProductId(productId);

            // Convert Review -> ReviewDto
            var reviewDtos = reviews.stream()
                    .map(review -> new ReviewDto(
                            review.getProductId(),
                            review.getComment(),
                            review.getRating(),
                            review.getUserName()
                    ))
                    .toList();

            // Calculate average rating
            double averageRating = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0); // or throw exception if no reviews

            // Create final DTO
            return new ProductReviewDto(reviewDtos, averageRating);
        }
}
