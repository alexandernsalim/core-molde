package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Discussion;
import com.ta.coremolde.shop.model.request.DiscussionRequest;
import com.ta.coremolde.shop.model.response.DiscussionDetailResponse;
import com.ta.coremolde.shop.model.response.DiscussionResponseServiceResponse;
import com.ta.coremolde.shop.model.response.DiscussionServiceResponse;

import java.util.List;

public interface DiscussionService {

    List<DiscussionServiceResponse> getAllDiscussions(Integer productId);

    List<DiscussionServiceResponse> getUserDiscussions(String email);

    DiscussionDetailResponse getDiscussion(Integer discussionId);

    Discussion createDiscussion(DiscussionRequest request, Integer productId, String email);

    String removeDiscussion(Integer discussionId);

    DiscussionResponseServiceResponse replyDiscussion(Integer discussionId, DiscussionRequest request, String email);

}
