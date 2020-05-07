package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Review;
import com.ta.coremolde.shop.model.request.ReviewRequest;

public interface ReviewService {

    Review createReview(String email, Integer productId, ReviewRequest reviewRequest);

    String removeReview(Integer reviewId);

}
