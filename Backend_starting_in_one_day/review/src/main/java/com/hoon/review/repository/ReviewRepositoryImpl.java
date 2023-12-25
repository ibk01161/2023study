package com.hoon.review.repository;

import com.hoon.review.model.QReviewEntity;
import com.hoon.review.model.ReviewEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

// 맛집에 등록된 리뷰 가져오기 API
@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    
    // 쿼리가 복잡하기 때문에 QueryDsl 사용
    private final JPAQueryFactory queryFactory;

    @Override
    public Double getAvgScoreByRestaurantId(Long restaurantId) {
        // Q클래스에서 점수를 가져와 평균을 냄
        return queryFactory.select(QReviewEntity.reviewEntity.score.avg())
                .from(QReviewEntity.reviewEntity)
                .where(QReviewEntity.reviewEntity.restaurantId.eq(restaurantId))
                .fetchFirst();
    }

    @Override
    public Slice<ReviewEntity> findSliceByRestaurantId(Long restaurantId, Pageable page) {
        // offset : 맛집 a에 리뷰 100개가 저장되어 있을때
        // 0번부터 10개 가져와라 하면 0번 부터 9번까지 가져옴
        List<ReviewEntity> reviews = queryFactory.select(QReviewEntity.reviewEntity)
                .from(QReviewEntity.reviewEntity)
                .where(QReviewEntity.reviewEntity.restaurantId.eq(restaurantId))
                .offset((long) page.getPageNumber() * page.getPageSize())
                .limit(page.getPageSize() + 1)                                      // Slice 인터페이스에 리턴할때 limit에 +1을 해줌
                .fetch();

        // 맛집에 등록된 리뷰를 QueryDsl로 가져옴
        // offset과 limit을 추가함 (4-7 강의 9~10분)

        return new SliceImpl<>(reviews.stream().limit(page.getPageSize()).toList(), page, reviews.size() > page.getPageSize());
    }

}
