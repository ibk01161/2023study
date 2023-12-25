package com.hoon.review.repository;

import com.hoon.review.model.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReviewRepositoryCustom {

    // 1. 평균 별점을 가져오는 메소드
    Double getAvgScoreByRestaurantId(Long restaurantId);
    // 2. 리뷰를 가져오는 쿼리
    // Slice는 페이징 기법에 사용되는 인터페이스, Pageable이라는 파라미터를 받아야 함
    Slice<ReviewEntity> findSliceByRestaurantId(Long restaurantId, Pageable page);
}
