package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.entity.Review;
import com.ta.coremolde.shop.model.request.ReviewRequest;
import com.ta.coremolde.shop.repository.ReviewRepository;
import com.ta.coremolde.shop.service.ProductService;
import com.ta.coremolde.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ProductService productService;

    @Override
    public Review createReview(String email, Integer productId, ReviewRequest reviewRequest) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Product product = productService.getProduct(productId);

        return reviewRepository.save(Review.builder()
                .title(reviewRequest.getTitle())
                .description(reviewRequest.getDescription())
                .rating(reviewRequest.getRating())
                .product(product)
                .shopUserId(shopUser.getId())
                .build());
    }

    @Override
    public String removeReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);

        return "Review removed successfully";
    }

}
