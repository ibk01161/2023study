package com.hoon.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CreateReviewRequest {
    private final Long restaurantId;
    private final String content;
    private final Double score;
}
