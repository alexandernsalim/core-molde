package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findReviewById(Integer id);

}
