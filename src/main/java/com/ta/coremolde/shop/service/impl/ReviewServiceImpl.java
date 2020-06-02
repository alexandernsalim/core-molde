package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.entity.Review;
import com.ta.coremolde.shop.model.request.ReviewRequest;
import com.ta.coremolde.shop.model.response.ReviewResponse;
import com.ta.coremolde.shop.repository.ReviewRepository;
import com.ta.coremolde.shop.service.OrderItemService;
import com.ta.coremolde.shop.service.ProductService;
import com.ta.coremolde.shop.service.ReviewService;
import com.ta.coremolde.util.ResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public List<ReviewResponse> getAllReview() {
        return ResponseMapper.mapAsList(reviewRepository.findAll(), ReviewResponse.class);
    }

    @Override
    public List<ReviewResponse> getProductReviews(Integer productId) {
        return ResponseMapper.mapAsList(reviewRepository.findAllByProduct_Id(productId), ReviewResponse.class);
    }

    @Override
    public List<ReviewResponse> getUserReviews(String email) {
        ShopUser shopUser = shopUserService.getShopUser(email);

        return ResponseMapper.mapAsList(reviewRepository.findAllByShopUserIdEquals(shopUser.getId()), ReviewResponse.class);
    }

    @Override
    @Transactional("shopTransactionManager")
    public ReviewResponse createReview(String email, Integer orderItemId, ReviewRequest reviewRequest) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Product product = productService.getProduct(reviewRequest.getProductId());
        Review review = Review.builder()
                .title(reviewRequest.getTitle())
                .description(reviewRequest.getDescription())
                .rating(Float.parseFloat(reviewRequest.getRating()))
                .product(product)
                .shopUserId(shopUser.getId())
                .build();
        orderItemService.updateReviewedStatus(orderItemId);

        return ResponseMapper.map(reviewRepository.save(review), ReviewResponse.class);
    }

    @Override
    public String removeReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);

        return "Review removed successfully";
    }

}
