package com.hoon.review.api;

import com.hoon.review.api.request.CreateReviewRequest;
import com.hoon.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewApi {

    private final ReviewService reviewService;

    /*
     * 리뷰 작성 API
     */
    @PostMapping("/review")
    public void createReview(@RequestBody CreateReviewRequest request) {
        reviewService.createReview(request.getRestaurantId(), request.getContent(), request.getScore());
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
