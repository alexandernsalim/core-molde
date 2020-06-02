package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findReviewById(Integer id);

    List<Review> findAllByProduct_Id(Integer productId);

    List<Review> findAllByShopUserIdEquals(Integer shopUserId);

}
