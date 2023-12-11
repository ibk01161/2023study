package com.hoon.review.repository;

import com.hoon.review.model.TestEntity;

import java.util.List;

public interface TestRepositoryCustom {

    // QueryDSL
    public List<TestEntity> findAllByNameByQueryDsl(String name);

}
