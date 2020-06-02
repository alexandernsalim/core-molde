package com.ta.coremolde.shop.controller;

import com.ta.coremolde.master.controller.GlobalController;
import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.shop.model.request.ReviewRequest;
import com.ta.coremolde.shop.model.response.ReviewResponse;
import com.ta.coremolde.shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(PathConstant.PREFIX + "reviews")
public class ReviewController extends GlobalController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public Response<List<ReviewResponse>> getAllReviews() {
        return toResponse(reviewService.getAllReview());
    }

    @PostMapping("/{orderItemId}")
    public Response<ReviewResponse> createProductReview(@PathVariable Integer orderItemId, @ModelAttribute ReviewRequest reviewRequest, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(reviewService.createReview(email, orderItemId, reviewRequest));
    }

}
