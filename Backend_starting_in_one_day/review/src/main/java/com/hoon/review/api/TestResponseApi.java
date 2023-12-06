package com.hoon.review.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResponseApi {

    @GetMapping("/test/response/string")
    public String stringResponse() {
        return "This is String";
    }

    /*
    @GetMapping("/test/response/json")
    public String jsonResponse() {
        // 응답 Header에 Content-Type 값이 test/plain임 (json이면 application/json 으로 와야 함)
        return  "{\"name\": \"this is Json\"}";
    }
    */

    @GetMapping("/test/response/json")
    public TestResponseBody jsonResponse() {
        // 응답 Header에 Content-Type 값이 application/json임
        return new  TestResponseBody("hoon", 30);
    }

    public static class TestResponseBody {
        String name;
        Integer age;

        public TestResponseBody(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
