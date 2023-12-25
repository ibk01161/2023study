package com.hoon.review.service;

import com.hoon.review.model.ReviewEntity;
import com.hoon.review.repository.RestaurantRepository;
import com.hoon.review.repository.ReviewRepository;
import com.hoon.review.service.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final RestaurantRepository restaurantRepository;

    private final ReviewRepository reviewRepository;

    // 리뷰 생성 API
    @Transactional
    public void createReview(Long restaurantId, String content, Double score) {
        // restaurantId 검증
        restaurantRepository.findById(restaurantId).orElseThrow();

        ReviewEntity review = ReviewEntity.builder()
                .restaurantId(restaurantId)
                .content(content)
                .score(score)
                .createAt(ZonedDateTime.now())
                .build();

        // DB에 저장
        reviewRepository.save(review);

    }

    // 리뷰 삭제 API
    @Transactional
    public void deleteReview(Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow();
        reviewRepository.delete(review);
    }

    // 맛집에 등록된 리뷰 가져오기 API
    public ReviewDto getRestaurantReview(Long restaurantId, Pageable page) {
        Double avgScore = reviewRepository.getAvgScoreByRestaurantId(restaurantId);
        Slice<ReviewEntity> reviews = reviewRepository.findSliceByRestaurantId(restaurantId, page);

        return ReviewDto.builder()
                .avgScore(avgScore)
                .reviews(reviews.getContent())
                .page(
                        ReviewDto.ReviewDtoPage.builder()
                                .offset(page.getPageNumber() * page.getPageSize())
                                .limit(page.getPageSize())
                                .build()
                )
                .build();
    }
}
