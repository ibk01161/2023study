package com.hoon.review.repository;

import com.hoon.review.model.QTestEntity;
import com.hoon.review.model.TestEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TestRepositoryImpl implements TestRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TestEntity> findAllByNameByQueryDsl(String name) {
        // Q클래스를 이용해 쿼리 작성 가능
        return queryFactory.selectFrom(QTestEntity.testEntity)
                .fetch();
    }
}
