package com.hoon.review.repository;

import com.hoon.review.model.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<TestEntity, Long>, TestRepositoryCustom {
    // JpaRepository<TestEntity, Long>에서 Long 은 TestEntity의 @Id 필드의 데이터 타입을 작성

    public List<TestEntity> findAllByName(String name);

}
