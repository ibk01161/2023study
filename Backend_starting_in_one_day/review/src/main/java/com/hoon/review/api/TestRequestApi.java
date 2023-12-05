package com.hoon.review.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestRequestApi {

    // Request Parameter 방식
    @GetMapping("/test/param")
    public String requestParam(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return "Hello, Request Param, I am " + name + ", " + age;
    }

    // PathVariable 방식
    @GetMapping("/test/path/{name}/{age}")
    public String requestPathVariable(@PathVariable("name") String name, @PathVariable("age") Integer age) {
        return "Hello, Request Path Variable, I am " + name + ", " + age;
    }

    // RequestBody 방식
    @PostMapping("/test/body")
    public String requestBody(@RequestBody TestRequestBody request) {
        return "Hello, Request Request Body, I am " + request.name + ", " + request.age;
    }

    public static class TestRequestBody {
        String name;
        Integer age;

        // requestBody를 사용할 때 생성자를 꼭 만들어야 함
        public TestRequestBody(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }
}
