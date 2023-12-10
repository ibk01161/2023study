package com.hoon.review.api;

import com.hoon.review.service.TestService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class TestEntityApi {

    // 생성자 주입
    private final TestService testService;

    @GetMapping("/test/entity/create")
    public void createTestEntity() {
        testService.create("joon", 21);
    }

    @PostMapping("/test/entity/create")
    public void createTestEntity_post(@RequestBody CreateTestEntityRequest request) {
        testService.create(request.getName(), request.getAge());
    }

    @PutMapping("/test/entity/{id}")
    public void putTestEntity(@PathVariable Long id, @RequestBody CreateTestEntityRequest request) {
        testService.update(id, request.getName(), request.getAge());
    }

    @DeleteMapping("test/entity/{id}")
    public void deleteTestEntity(@PathVariable Long id) {
        testService.delete(id);
    }

    //@AllArgsConstructor
    //@NoArgsConstructor
    @Getter
    public static class CreateTestEntityRequest {
        private String name;
        private Integer age;

        public CreateTestEntityRequest() {
        }

        /*
        public CreateTestEntityRequest(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        */
    }
}
