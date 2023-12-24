package com.hoon.review.api;

import com.hoon.review.api.request.CreateAndEditRestaurantRequest;
import com.hoon.review.api.response.RestaurantDetailView;
import com.hoon.review.api.response.RestaurantView;
import com.hoon.review.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<RestaurantView> getRestaurants() {
        // 테스트
        /*
        return List.of(RestaurantView.builder()
                .id(0L)
                .name("test name")
                .address("test addreses")
                .createAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .build());
         */
        return restaurantService.getAllRestaurants();
    }

    /*
     * 맛집 정보 가져오기 API
     */
    @GetMapping("/restaurant/{restaurantId}")
    public RestaurantDetailView getRestaurant(@PathVariable Long restaurantId) {
        // 테스트
        /*
        return RestaurantDetailView.builder()
                .id(0L)
                .name("test name")
                .address("test addreses")
                .createAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .menus(List.of(
                        RestaurantDetailView.Menu.builder()
                                .id(0L)
                                .name("test menu name")
                                .price(500)
                                .createAt(ZonedDateTime.now())
                                .updateAt(ZonedDateTime.now())
                                .build()
                ))
                .build();
         */
        return restaurantService.getRestaurantDetail(restaurantId);
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
