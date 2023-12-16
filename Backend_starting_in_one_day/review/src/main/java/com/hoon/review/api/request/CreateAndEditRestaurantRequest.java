package com.hoon.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CreateAndEditRestaurantRequest {

    private final String name;
    private final String address;
    private final List<CreateAndEditRestaurantRequestMenu> menus;

}
