package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {

    List<Discussion> findAllByProduct_IdEquals(Integer productId);

    List<Discussion> findAllByShopUserIdEquals(Integer shopUserId);

    Discussion findDiscussionByIdEquals(Integer discussionId);

}
