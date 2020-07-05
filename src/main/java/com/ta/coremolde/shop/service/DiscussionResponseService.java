package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.entity.Discussion;
import com.ta.coremolde.shop.model.response.DiscussionResponseServiceResponse;

import java.util.List;

public interface DiscussionResponseService {

    DiscussionResponseServiceResponse getLastReply(Integer discussionId);
    DiscussionResponseServiceResponse createReply(Discussion discussion, String message, String email);
    List<DiscussionResponseServiceResponse> getDiscussionReplies(Integer discussionId);

}
