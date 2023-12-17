package com.hoon.review.service;

import com.hoon.review.api.request.CreateAndEditRestaurantRequest;
import com.hoon.review.model.MenuEntity;
import com.hoon.review.model.RestaurantEntity;
import com.hoon.review.repository.MenuRepository;
import com.hoon.review.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public RestaurantEntity createRestaurant(CreateAndEditRestaurantRequest request) {
        // RestaurantEntity restaurant = new RestaurantEntity();
        // RestaurantEntity에 @Builder를 생성해주면 밑에 처럼 선언가능
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .build();

        // restaurant table에 insert
        restaurantRepository.save(restaurant);

        // menu 테이블에 insert
        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createAt(ZonedDateTime.now())
                    .updateAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });

        return restaurant;
    }

    public void editRestaurant() {

    }

    public void deleteRestaurant() {

    }
}
