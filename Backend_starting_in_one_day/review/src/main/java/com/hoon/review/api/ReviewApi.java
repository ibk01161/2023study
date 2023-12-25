package com.hoon.review.api;

import com.hoon.review.api.request.CreateReviewRequest;
import com.hoon.review.service.ReviewService;
import com.hoon.review.service.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    /*
     * 리뷰 삭제 API
     */
    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    /*
     * 맛집에 등록된 리뷰 가져오기 API
     */
    @GetMapping("/restaurant/{restaurantId}/reviews")
    public ReviewDto getRestaurantReviews(@PathVariable("restaurantId") Long RestaurantId, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return reviewService.getRestaurantReview(RestaurantId, PageRequest.of(offset / limit, limit));
    }
}
