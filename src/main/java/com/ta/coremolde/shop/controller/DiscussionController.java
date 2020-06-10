package com.ta.coremolde.shop.controller;

import com.ta.coremolde.master.controller.GlobalController;
import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.shop.model.request.DiscussionRequest;
import com.ta.coremolde.shop.model.response.DiscussionDetailResponse;
import com.ta.coremolde.shop.model.response.DiscussionResponseServiceResponse;
import com.ta.coremolde.shop.model.response.DiscussionServiceResponse;
import com.ta.coremolde.shop.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(PathConstant.PREFIX + "discussions")
public class DiscussionController extends GlobalController {

    @Autowired
    private DiscussionService discussionService;

    @GetMapping
    public Response<List<DiscussionServiceResponse>> getDiscussions() {
        return toResponse(discussionService.getAllDiscussions());
    }

    @GetMapping("/{discussionId}")
    public Response<DiscussionDetailResponse> getDiscussion(@PathVariable Integer discussionId) {
        return toResponse(discussionService.getDiscussion(discussionId));
    }

    @PostMapping("/{productId}")
    public Response<DiscussionServiceResponse> createDiscussion(@PathVariable Integer productId, @ModelAttribute DiscussionRequest request, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(discussionService.createDiscussion(request, productId, email));
    }

    @PostMapping("/{discussionId}/reply")
    public Response<DiscussionResponseServiceResponse> replyDiscussion(@PathVariable Integer discussionId, @ModelAttribute DiscussionRequest request, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(discussionService.replyDiscussion(discussionId, request, email));
    }

}
