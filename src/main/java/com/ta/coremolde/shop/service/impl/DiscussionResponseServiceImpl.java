package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.entity.Shop;
import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.service.ShopService;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.entity.Discussion;
import com.ta.coremolde.shop.model.entity.DiscussionResponse;
import com.ta.coremolde.shop.model.response.DiscussionResponseServiceResponse;
import com.ta.coremolde.shop.repository.DiscussionResponseRepository;
import com.ta.coremolde.shop.service.DiscussionResponseService;
import com.ta.coremolde.shop.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscussionResponseServiceImpl implements DiscussionResponseService {

    @Autowired
    private DiscussionResponseRepository discussionResponseRepository;

    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Override
    public DiscussionResponseServiceResponse getLastReply(Integer discussionId) {
        DiscussionResponse discussionResponse = discussionResponseRepository.findTopByDiscussion_IdOrderByIdDesc(discussionId);
        if (discussionResponse != null) {
            return buildDiscussionResponse(discussionResponse);
        }
        return null;
    }

    @Override
    public DiscussionResponseServiceResponse createReply(Discussion discussion, String message, String email) {
        Shop shop = shopService.getShopByAccountEmail(email);
        ShopUser shopUser = shopUserService.getShopUser(email);
        DiscussionResponse discussionResponse = DiscussionResponse.builder()
                .message(message)
                .discussion(discussion)
                .shopId(shop == null ? null : shop.getId())
                .shopUserId(shopUser == null ? null : shopUser.getId())
                .build();
        discussionResponseRepository.save(discussionResponse);

        DiscussionResponseServiceResponse response = DiscussionResponseServiceResponse.builder()
                .id(discussion.getId())
                .message(discussionResponse.getMessage())
                .shopReplyUsername(shop == null ? "" : shop.getName())
                .shopUserReplyUsername(shopUser == null ? "" : shopUser.getFirstName())
                .build();
        pushNotificationService.pushDiscussionReplyNotification(response);

        return response;
    }

    @Override
    public List<DiscussionResponseServiceResponse> getDiscussionReplies(Integer discussionId) {
        List<DiscussionResponse> responses = discussionResponseRepository.findAllByDiscussion_Id(discussionId);
        List<DiscussionResponseServiceResponse> discussionReplies = new ArrayList<>();

        for (DiscussionResponse response : responses) {
            discussionReplies.add(buildDiscussionResponse(response));
        }

        return discussionReplies;
    }

    private DiscussionResponseServiceResponse buildDiscussionResponse(DiscussionResponse discussionResponse) {
        Integer shopUserId = discussionResponse.getShopUserId();
        Integer shopId = discussionResponse.getShopId();
        String shopUsername = null;
        String shopUserUsername = null;

        if (shopUserId != null) {
            ShopUser shopUser = shopUserService.getShopUserById(shopUserId);
            shopUserUsername = shopUser.getFirstName();
        }
        if (shopId != null) {
            Shop shop = shopService.getShop(shopId);
            shopUsername = shop.getName();
        }

        return DiscussionResponseServiceResponse.builder()
                .id(discussionResponse.getId())
                .message(discussionResponse.getMessage())
                .shopReplyUsername(shopUsername)
                .shopUserReplyUsername(shopUserUsername)
                .build();
    }

}
