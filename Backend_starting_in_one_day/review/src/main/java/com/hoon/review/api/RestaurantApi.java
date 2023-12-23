package com.hoon.review.api;

import com.hoon.review.api.request.CreateAndEditRestaurantRequest;
import com.hoon.review.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {

    private final RestaurantService restaurantService;

    // 1. API path 생성
    // 2. 맛집 생성, 맛집 수정에 request body가 있음 => request 정의

    /*
     * 맛집 리스트 가져오기 API
     */
    @GetMapping("/restaurants")
    public String getRestaurants() {
        return "This is getRestaurants";
    }

    /*
     * 맛집 정보 가져오기 API
     */
    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(@PathVariable Long restaurantId) {
        return "This is getRestaurant, " + restaurantId;
    }

    /*
     * 맛집 생성 API
     */
    @PostMapping("/restaurant")
    public void createRestaurant(@RequestBody CreateAndEditRestaurantRequest request) {
        restaurantService.createRestaurant(request);
    }

    /*
     * 맛집 수정 API
     */
    @PutMapping("/restaurant/{restaurantId}")
    public void editRestaurant(@PathVariable Long restaurantId, @RequestBody CreateAndEditRestaurantRequest request) {
        restaurantService.editRestaurant(restaurantId, request);
    }

    /*
     * 맛집 삭제 API
     */
    @DeleteMapping("/restaurant/{restaurantId}")
    public void deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }

}
