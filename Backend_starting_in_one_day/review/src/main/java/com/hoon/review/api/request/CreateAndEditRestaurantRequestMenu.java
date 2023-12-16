package com.hoon.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CreateAndEditRestaurantRequestMenu {

    private final String name;
    private final Integer price;

}
