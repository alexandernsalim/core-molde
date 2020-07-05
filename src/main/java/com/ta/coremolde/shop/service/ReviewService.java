package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.request.ReviewRequest;
import com.ta.coremolde.shop.model.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

    List<ReviewResponse> getAllReview();

    List<ReviewResponse> getProductReviews(Integer productId);

    List<ReviewResponse> getUserReviews(String email);

    ReviewResponse createReview(String email, Integer productId, ReviewRequest reviewRequest);

    String removeReview(Integer reviewId);

}
