package com.ta.coremolde.shop.service.impl;

import com.ta.coremolde.master.model.entity.ShopUser;
import com.ta.coremolde.master.service.ShopUserService;
import com.ta.coremolde.shop.model.entity.Discussion;
import com.ta.coremolde.shop.model.entity.Product;
import com.ta.coremolde.shop.model.request.DiscussionRequest;
import com.ta.coremolde.shop.model.response.DiscussionDetailResponse;
import com.ta.coremolde.shop.model.response.DiscussionResponseServiceResponse;
import com.ta.coremolde.shop.model.response.DiscussionServiceResponse;
import com.ta.coremolde.shop.repository.DiscussionRepository;
import com.ta.coremolde.shop.service.DiscussionResponseService;
import com.ta.coremolde.shop.service.DiscussionService;
import com.ta.coremolde.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private DiscussionResponseService discussionResponseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopUserService shopUserService;

    @Override
    public List<DiscussionServiceResponse> getAllDiscussions(Integer productId) {
        List<Discussion> discussions = discussionRepository.findAllByProduct_IdEquals(productId);
        List<DiscussionServiceResponse> discussionsResponse = new ArrayList<>();

        for (Discussion discussion : discussions) {
            String username = shopUserService.getShopUserById(discussion.getShopUserId()).getFirstName();
            DiscussionResponseServiceResponse lastReply = discussionResponseService.getLastReply(discussion.getId());

            discussionsResponse.add(DiscussionServiceResponse.builder()
                    .id(discussion.getId())
                    .detail(discussion.getDetail())
                    .questionMaker(username)
                    .lastReply(lastReply)
                    .product(discussion.getProduct())
                    .build());
        }

        return discussionsResponse;
    }

    @Override
    public List<DiscussionServiceResponse> getUserDiscussions(String email) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        String username = shopUser.getFirstName();
        List<Discussion> discussions = discussionRepository.findAllByShopUserIdEquals(shopUser.getId());
        List<DiscussionServiceResponse> discussionsResponse = new ArrayList<>();

        for (Discussion discussion : discussions) {
            DiscussionResponseServiceResponse lastReply = discussionResponseService.getLastReply(discussion.getId());
            discussionsResponse.add(DiscussionServiceResponse.builder()
                    .id(discussion.getId())
                    .detail(discussion.getDetail())
                    .questionMaker(username)
                    .lastReply(lastReply)
                    .product(discussion.getProduct())
                    .build());
        }

        return discussionsResponse;
    }

    @Override
    public DiscussionDetailResponse getDiscussion(Integer discussionId) {
        Discussion discussion = discussionRepository.findDiscussionById(discussionId);
        String owner = shopUserService.getShopUserById(discussion.getShopUserId()).getFirstName();
        List<DiscussionResponseServiceResponse> responses = discussionResponseService.getDiscussionReplies(discussionId);

        return DiscussionDetailResponse.builder()
                .id(discussionId)
                .question(discussion.getDetail())
                .questionOwner(owner)
                .responses(responses)
                .build();
    }

    @Override
    public Discussion createDiscussion(DiscussionRequest request, Integer productId, String email) {
        ShopUser shopUser = shopUserService.getShopUser(email);
        Product product = productService.getProduct(productId);

        return discussionRepository.save(Discussion.builder()
                .detail(request.getDetail())
                .product(product)
                .shopUserId(shopUser.getId())
                .build());
    }

    @Override
    public String removeDiscussion(Integer discussionId) {
        return null;
    }

    @Override
    public DiscussionResponseServiceResponse replyDiscussion(Integer discussionId, DiscussionRequest request, String email) {
        Discussion discussion = discussionRepository.findDiscussionById(discussionId);
        String message = request.getDetail();

        return discussionResponseService.createReply(discussion, message, email);
    }

}
