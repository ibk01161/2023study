package com.hoon.review.repository;

import com.hoon.review.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, ReviewRepositoryCustom {

    // ReviewRepositoryCustom 인터페이스를 사용하기 위해 같이 상속을 받아줘야 함
}
