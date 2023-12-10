package com.hoon.review.service;

import com.hoon.review.model.TestEntity;
import com.hoon.review.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TestService {

    // 생성자 주입
    private final TestRepository testRepository;

    public void create(String name, Integer age) {
        // 테스트 엔티티를 만들고 테스트 레퍼지토리를 통해 save 하는 과정
        TestEntity testEntity = new TestEntity(name, age);
        testRepository.save(testEntity);
    }

    public void update(Long id, String name, Integer age) {
        TestEntity testEntity = testRepository.findById(id).orElseThrow();          // 이전에 저장했던 데이터
        testEntity.changeNameAndAge(name, age);                                     // 이름과 나이를 수정
        testRepository.save(testEntity);                                            // 저장
    }

    public void delete(Long id) {
        TestEntity testEntity = testRepository.findById(id).get();
        testRepository.delete(testEntity);
    }

}
