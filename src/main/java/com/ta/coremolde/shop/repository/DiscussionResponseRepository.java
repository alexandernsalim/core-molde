package com.ta.coremolde.shop.repository;

import com.ta.coremolde.shop.model.entity.DiscussionResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionResponseRepository extends JpaRepository<DiscussionResponse, Integer> {

    DiscussionResponse findTopByDiscussion_IdOrderByIdDesc(Integer discussionId);
    List<DiscussionResponse> findAllByDiscussion_Id(Integer discussionId);

}
