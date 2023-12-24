package com.hoon.review.service;

import com.hoon.review.api.request.CreateAndEditRestaurantRequest;
import com.hoon.review.api.response.RestaurantDetailView;
import com.hoon.review.api.response.RestaurantView;
import com.hoon.review.model.MenuEntity;
import com.hoon.review.model.RestaurantEntity;
import com.hoon.review.repository.MenuRepository;
import com.hoon.review.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

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

    @Transactional
    public void editRestaurant(Long restaurantId, CreateAndEditRestaurantRequest request) {
        // 레스토랑 수정
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("없는 레스토랑입니다."));
        // 레스토랑의 이름과 주소를 변경
        restaurant.changeNameAndAddress(request.getName(), request.getAddress());
        // 수정한 레스토랑을 DB에 save
        restaurantRepository.save(restaurant);
        
        // 메뉴수정 : 레스토랑 id와 연결되어 있는 메뉴들을 모두 가져와서 삭제 후 다시 메뉴 생성
        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurantId)
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createAt(ZonedDateTime.now())
                    .updateAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });
    }

    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        // 레스토랑 삭제
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
        restaurantRepository.delete(restaurant);

        // 맛집과 연결된 메뉴들도 삭제
        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);
    }
    
    // 맛집 리스트 가져오기
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<RestaurantView> getAllRestaurants() {
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();

        // RestaurantView로 리턴해줘야 함
        return restaurants.stream().map((restaurant) -> RestaurantView.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .createAt(restaurant.getCreateAt())
                .updateAt(restaurant.getUpdateAt())
                .build()
        ).toList();
        
        // 읽기만하는 메소드(select)에서는 Transactional을 붙히지 않아도 됨 or readOnly 설정
    }

    // 맛집 정보 가져오기
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public RestaurantDetailView getRestaurantDetail(Long restaurnatId) {
        // DB에서 맛집 조회
        RestaurantEntity restaurant = restaurantRepository.findById(restaurnatId).orElseThrow();
        
        // DB에서 메뉴 조회
        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurnatId);

        return RestaurantDetailView.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .createAt(restaurant.getCreateAt())
                .updateAt(restaurant.getUpdateAt())
                .menus(
                        menus.stream().map((menu) -> RestaurantDetailView.Menu.builder()
                                .id(menu.getId())
                                .name(menu.getName())
                                .price(menu.getPrice())
                                .createAt(menu.getCreateAt())
                                .updateAt(menu.getUpdateAt())
                                .build()
                        ).toList()
                ).build();
    }
}
